package com.skryl.unit;

import com.skryl.configuration.ApplicationConfig;
import org.aeonbits.owner.ConfigFactory;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class EnvironmentPropertiesTest {

    @Test
    void defaultConfiguration() {
        var config = ConfigFactory.create(ApplicationConfig.class);
        var baseUrl = config.uiUrl();
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(config.env()).isEqualTo("local");
        softAssertions.assertThat(baseUrl).isEqualTo("http://localhost:8081");
        softAssertions.assertAll();
    }

    @Test
    void systemPropertiesConfiguration() {
        System.setProperty("env", "docker");
        var config = ConfigFactory.create(ApplicationConfig.class);
        var baseUrl = config.uiUrl();
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(config.env()).isEqualTo("docker");
        softAssertions.assertThat(baseUrl).isEqualTo("http://host.docker.internal:8081");
        softAssertions.assertAll();
    }


}
