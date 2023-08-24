package com.example.shop.ui;


import com.codeborne.selenide.Selenide;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;


import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static io.qameta.allure.Allure.step;

@DisplayName("UI тесты")
public class ShopTestsUI extends WebTest{


   ////// @BeforeAll
    //static void setUp() {
      //  System.setProperty("webdriver.http.factory", "jdk-http-client");
      //  Configuration.browser = "Chrome";
   // }

    @AfterEach
    void addAttachments() {

        Selenide.closeWebDriver();
    }

    MainPage mainPage = new MainPage();

    @BeforeEach
    public void setSelenide() {
        System.setProperty("chromeoptions.args", "--remote-allow-origins=*");
        //Configuration.browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");
       open("http://localhost:63342/shop/src/main/java/com/example/shop/ui/main.html?_ijt=n88fnoh7kl99vdqij96g182bvb&_ij_reload=RELOAD_ON_SAVE");

        Selenide.webdriver().driver().getWebDriver().manage().addCookie(new Cookie("Idea-5b06d01f", "d6f43fe8-6655-4eb7-abdc-16d011c527cb"));
        Thread.onSpinWait();
        refresh();
    }
    @Test
    @Feature("Меню")
    @Story("Главная страница")
    @DisplayName("Проверка отображения главного меню. Основные кнопки")
    void shouldMenuTest() {
        step("Проверить отображение главного меню", () -> {
            mainPage.shouldCreateShopButton();
            mainPage.shouldAllShopsButton();
            mainPage.shouldDeleteShopButton();
        });
    }
    @Step("Проверить переход на форму ввода имени магазина")
    @Test
    @Feature("Создание магазина")
    @Story("Магазины")
    @DisplayName("Проверка кнопки CreateShop")
    void shouldCreateShopButtonTest() {
                mainPage.clickCreateShopButton();
                mainPage.shouldCreateShopTitle("Create a shop");
    }

    @Test
    @Feature("Создание магазинов")
    @Story("Магазины")
    @DisplayName("Проверка создания магазина")
    void shouldValidCreateShopTest() {
        step("Нажать на кнопку Create shop", () ->
                mainPage.clickCreateShopButton());
        step("Ввод названия магазина", () ->
                mainPage.createShopInput("Name777"));
        step("Нажать кнопку Create shop", () ->
                mainPage.clickCreateShop());
        step("Нажать Enter", () ->
                actions().keyDown(Keys.ENTER).perform());
        step("Проверить,что магазин создан", () ->
                mainPage.shouldShop("Name777"));
    }
    @Step("Проверить переход к каталогу магазинов")
    @Test
    @Feature("Каталог магазинов")
    @Story("Магазины")
    @DisplayName("Проверка кнопки AllShops")
    void shouldAllShopButtonTest() {
                mainPage.clickAllShopsButton();
                mainPage.shouldAllShopTitle("Already created shops");
    }

    @Step("Проверить переход к форме удаления")
    @Test
    @Feature("Удаление магазинов")
    @Story("Магазины")
    @DisplayName("Проверка кнопки Удалить магазин")
    void shouldDeleteShopButtonTest() {
        mainPage.clickDeleteShopButton();
        mainPage.shouldDeleteShopTitle("Delete a shop");
    }
    @Test
    @Feature("Удаление магазинов")
    @Story("Магазины")
    @DisplayName("Проверка Удаления магазина.Позитивный")
    void shouldDeleteShopTest() {
        step("Нажать на кнопку Delete shop", () ->
                mainPage.clickDeleteShopButton());
        step("Ввести id магазина", () ->
                mainPage.deleteShopInput("123"));
        step("Нажать кнопку Delete shop", () ->
                mainPage.clickDeleteShop());
        step("Нажать кнопку Enter", () ->
                actions().keyDown(Keys.ENTER).perform());
        step("Проверить,что магазин удален", () ->
                mainPage.shouldShop("123"));
    }
    @Test
    @Feature("Удаление магазинов")
    @Story("Магазины")
    @DisplayName("Проверка Удаления магазина без ID.Негативный")
    void shouldDeleteShopTestNegative() {
        step("Нажать на кнопку Delete shop", () ->
                mainPage.clickDeleteShopButton());
        step("Нажать кнопку Delete shop", () ->
                mainPage.clickDeleteShop());
        step("Нажать кнопку Enter", () ->
                actions().keyDown(Keys.ENTER).perform());
        step("Проверить отображение ошибки", () ->
                mainPage.shouldDeleteEmptyError());
    }
    @Test
    @Feature("Refresh")
    @Story("Магазины")
    @DisplayName("Проверка обновления каталога магазинов")
    void shouldRefreshShopTest() {
        step("Нажать на кнопку All shops", () ->
                mainPage.clickAllShopsButton());
        step("Нажать refresh", () ->
                mainPage.clickRefresh());
        step("Проверить отображение заголовка", () -> {
            mainPage.shouldMainTitle("Welcome to our shop constructor!");
        });
    }

    @Test
    @Feature("Вконтакте")
    @Story("Соц сети")
    @DisplayName("Проверка кнопки VK")
    void shouldVKButtonTest() {
        step("Нажать на кнопку VK", () ->
                mainPage.clickVk());
        step("Проверить переход по ссылке", () -> {
            mainPage.title.shouldHave(attribute("text", "ВКонтакте | Добро пожаловать"));
        });
    }
    @Test
    @Feature("Телеграм")
    @Story("Соц сети")
    @DisplayName("Проверка кнопки Telegram")
    void shouldTelegramButtonTest() {
        step("Нажать на кнопку Telegram", () ->
                mainPage.clickTelegram());
        step("Проверить переход по ссылке", () -> {
            url().equals("https://web.telegram.org/a/");
        });
    }

}

