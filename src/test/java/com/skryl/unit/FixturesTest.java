package com.skryl.unit;

import com.skryl.configuration.Fixtures;
import org.testng.annotations.Test;

public class FixturesTest {
    @Test
    void getFixturesTest() {
        var fixture = new Fixtures();
        fixture.getFixture();
    }
}
