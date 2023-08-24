package com.example.shop.ui;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
public class WebTest {
    @BeforeAll
    static void setUp() {
            System.setProperty("webdriver.http.factory", "jdk-http-client");
       ///Configuration.remote = "http://localhost:4444/wd/hub";
            Configuration.browser = "Chrome";
    }
}