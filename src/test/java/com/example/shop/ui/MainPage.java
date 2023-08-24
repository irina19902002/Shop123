package com.example.shop.ui;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    SelenideElement

            title = $x("/html/head/title"),
            createShopButton = $x("//a[@href='#create_shop']"),
            allShopsButton = $x("//a[@href='#all_shops']"),
            deleteShopButton = $x("//a[@href='#delete_shop']"),
            createShopTitle = $x("//h2[text()='Create a shop']"),
            allShopsTitle = $x("//h2[text()='Already created shops']"),
            deleteShopTitle = $x("//h2[text()='Delete a shop']"),
            shopInput = $x("//input[@placeholder='Enter shop name']"),
            createShop = $x("//button[text()='Create shop']"),
            shopsList = $("#shops_div"),
            deleteShopId = $x("//input[@placeholder='Enter shop id']"),
            deleteShop = $x("//button[text()='Delete shop']"),
            deleteError = $("#id_validation"),
            refreshButton = $x("//button[text()='Refresh']"),
            mainTittle = $x("//h1[text()='Welcome to our shop constructor!']"),
            telegramButton = $x("//a[@href='https://web.telegram.org/']"),
            vkButton = $x("//a[@href='https://m.vk.com/']");

    public void clickCreateShopButton() {
        createShopButton.click();
    }

    public void clickAllShopsButton() {
        allShopsButton.click();
    }

    public void clickDeleteShopButton() {
        deleteShopButton.click();
    }

    public void shouldCreateShopButton() {
        createShopButton.shouldBe(visible);
    }

    public void shouldAllShopsButton() {
        allShopsButton.shouldBe(visible);
    }

    public void shouldDeleteShopButton() {
        deleteShopButton.shouldBe(visible);
    }

    public void shouldCreateShopTitle(String value) {

        createShopTitle.shouldHave(text(value));
    }

    public void shouldAllShopTitle(String value) {

        allShopsTitle.shouldHave(text(value));
    }

    public void shouldDeleteShopTitle(String value) {

        deleteShopTitle.shouldHave(text(value));
    }

    public void createShopInput(String value) {
        shopInput.setValue(value);
    }

    public void clickCreateShop() {
        createShop.click();
    }

    public void shouldShop(String value) {
        shopsList.shouldHave(text(value));
    }

    public void deleteShopInput(String value) {
        deleteShopId.setValue(value);
    }

    public void clickDeleteShop() {
        deleteShop.click();
    }

    public void shouldDeleteEmptyError() {
        deleteError.shouldBe(visible);
    }

    public void clickRefresh() {
        refreshButton.click();
    }
    public void shouldMainTitle(String value) {
        mainTittle.shouldHave(text(value));
    }
    public void clickTelegram() {
        telegramButton.click();
    }

    public void clickVk() {
        vkButton.click();
    }



}
