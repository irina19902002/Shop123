package com.example.shop.api;


import com.example.shop.models.ShopDto;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;


@DisplayName("API тесты")
class ShopApiTest extends BaseApiTest {


    @Test
    @Feature("Создание магазина")
    @Story("Магазины")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание одного магазина")
    void shouldAddShopTest() {
        RequestSpecification request = given();

        final ShopDto shopDto = new ShopDto(shopId, shopName, shopPublic);
        JSONObject object = new JSONObject();
        object.put(String.valueOf(shopId), shopDto.getShopId());
        object.put(shopName, shopDto.getShopName());
        object.put(String.valueOf(shopPublic), shopDto.isShopPublic());

        request.body(object.toString());
        request.header("content-type", "application/json");

        System.out.println(object);

        given()
                .body(shopDto)
                .when()
                .post("http://localhost:4000/shops/add")
                .then();

    }
    @Test
    @Feature("Онлайн Магазин")
    @DisplayName("Удаление магазина")
    public void ShouldDeleteOneShop() {
        Response response = given().delete("http://localhost:4000/shops/8752");
        Assertions.assertThat(response)
                .extracting(
                        Response::getStatusCode,
                        Response::getStatusLine
                );

    }
    @Test
    @Feature("Онлайн Магазин")
    @DisplayName("Получение всех магазинов")
    public void ShouldGetAllShops() {
        Response response = given().get("http://localhost:4000/shops/all");
        Assertions.assertThat(response)
                .extracting(
                        Response::getStatusCode,
                        Response::getStatusLine
                )
                .contains(
                        200,
                        "HTTP/1.1 200 "
                );
    }




}
