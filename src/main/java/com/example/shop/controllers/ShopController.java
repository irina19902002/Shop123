package com.example.shop.controllers;

import com.example.shop.models.ShopDto;
import com.example.shop.models.ShopPojo;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.shop.Configuration.buildFactory;
import static com.example.shop.Configuration.createNewSession;
import static com.example.shop.ShopHandler.checkFirstLetter;
import static com.example.shop.ShopHandler.checkLength;

@CrossOrigin(
        origins = "http://localhost:63342",
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.DELETE},
        allowedHeaders = "Content-type"
)
@RestController
@RequestMapping(path = "/shops")
@RequiredArgsConstructor

public class ShopController extends Configuration {

    private static SessionFactory factory = buildFactory();

    @PostMapping("/add")
    public @ResponseBody ResponseEntity<String> addShop(@RequestBody ShopDto dto) {
        Session session = createNewSession(factory);
        JSONObject object = new JSONObject(dto);
        Transaction transaction = session.beginTransaction();
        final ShopPojo pojo = new ShopPojo();
        pojo.setShopName(object.get("shopName").toString());
        pojo.setShopPublic(Boolean.parseBoolean(object.get("shopPublic").toString()));

        if (!checkLength(pojo.getShopName(), 7)) {
            session.close();
            return ResponseEntity.status(400).body("Name should be more than 6 letters");
        }

        if (!checkFirstLetter(pojo.getShopName())) {
            session.close();
            return ResponseEntity.status(400).body("Name should begin with a capital letter");
        }

        session.persist(pojo);
        transaction.commit();
        session.close();

        HttpHeaders responseHeaders = new HttpHeaders();

        return ResponseEntity.status(200).headers(responseHeaders).build();
    }


    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<ShopPojo>> getShops() {
        Session session = createNewSession(factory);
        Transaction transaction = session.beginTransaction();

        var res = session.createNativeQuery("select * from shops", ShopPojo.class).list();
        transaction.commit();
        session.close();

        HttpHeaders responseHeaders = new HttpHeaders();

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(res);
    }

    @GetMapping("/{shopId}")
    public @ResponseBody ResponseEntity<ShopPojo> getShop(@PathVariable long shopId) {
        Session session = createNewSession(factory);
        Transaction transaction = session.beginTransaction();

        var res = session.createNativeQuery("select * from shops where shop_id = " + shopId,
                ShopPojo.class).uniqueResult();
        transaction.commit();
        session.close();

        HttpHeaders responseHeaders = new HttpHeaders();

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(res);
    }


    @DeleteMapping("/delete/{shopId}")
    public @ResponseBody ResponseEntity<String> deleteShop(@PathVariable Long shopId) {
        ShopPojo rm_shop = new ShopPojo();
        rm_shop.setShopId(shopId);

        Session session = createNewSession(factory);
        Transaction transaction = session.beginTransaction();

        session.createNativeQuery("delete from shops where shop_id = :id", ShopPojo.class)
                .setParameter("id", rm_shop.getShopId())
                .executeUpdate();
        transaction.commit();
        session.close();

        HttpHeaders responseHeaders = new HttpHeaders();

        return ResponseEntity
                .status(204)
                .headers(responseHeaders)
                .build();
    }
}