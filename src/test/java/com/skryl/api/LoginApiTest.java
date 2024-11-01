package com.skryl.api;

import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import com.skryl.api.book.BookApi;
import com.skryl.api.book.BookApiStep;
import com.skryl.configuration.ApplicationConfig;
import lombok.extern.slf4j.Slf4j;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class LoginApiTest {
    private BookApiStep bookApiStep;

    @BeforeClass(groups = {"smoke"})
    void restAssuredSetup() {
        var config = ConfigFactory.create(ApplicationConfig.class);
        var baseUrl = config.uiUrl();
        bookApiStep = new BookApiStep(new BookApi(baseUrl));
    }

    @Test(testName = "[API] User Login To BookApp", groups = {"smoke"})
    @Attributes(attributes = {@Attribute(key = "key", value = "value")})
    void userLoginToBookApp() {
        log.info("Test started");
        var user = bookApiStep.login("test", "test");
        assertThat(user.getId()).isEqualTo(1);

        var actualStatus = bookApiStep.loginStatus();
        assertThat(actualStatus)
                .as("User should be logged in")
                .isTrue();
    }

    @Test(testName = "[API] User 2 Login To BookApp", groups = {"smoke"})
    @Attributes(attributes = {@Attribute(key = "key", value = "value")})
    void user2LoginToBookApp() {
        var user = bookApiStep.login("test2", "test2");
        assertThat(user.getId()).isEqualTo(2);

        var actualStatus = bookApiStep.loginAccount();
        assertThat(actualStatus)
                .as("User should be logged in")
                .isTrue();
    }
}
