package mobile.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import mobile.drivers.BrowserstackDriver;
import mobile.helpers.Attach;
import mobile.helpers.CustomApiListener;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.sessionId;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = BrowserstackDriver.class.getName();
        Configuration.browserSize = null;
        RestAssured.filters(CustomApiListener.withCustomTemplates());
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
       Selenide.open();
    }

    @AfterEach
    void afterEach() {
        String sessionId = sessionId().toString();
        System.out.println(sessionId);

//        Attach.screenshotAs("Last screenshot"); // todo
        Attach.pageSource();

        closeWebDriver();

        Attach.addVideo(sessionId);
    }
}
