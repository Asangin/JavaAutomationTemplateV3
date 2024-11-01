package com.skryl.api.book;

import com.epam.reportportal.restassured.ReportPortalRestAssuredLoggingFilter;
import com.google.gson.JsonElement;
import com.epam.reportportal.listeners.LogLevel;
import com.skryl.model.book.CreateBookRequestDto;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.mapper.ObjectMapperType.GSON;

public final class BookApi {
    public static final int FILTER_ORDER = 42;
    private final RequestSpecification requestSpec;

    public BookApi(final String baseUrl) {
        requestSpec = RestAssured.given()
                .baseUri(baseUrl)
                .basePath("/api")
                .config(new RestAssuredConfig().objectMapperConfig(new ObjectMapperConfig(GSON)))
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .filter(new AllureRestAssured())
                .filter(new ReportPortalRestAssuredLoggingFilter(FILTER_ORDER, LogLevel.INFO));
    }

    public Response login(final JsonElement body) {
        return RestAssured.given()
                .spec(requestSpec)
                .contentType(ContentType.JSON)
                .body(body)
                .post("/auth/login");
    }

    public Response loginAccount(final Cookies cookies) {
        return RestAssured.given()
                .spec(requestSpec)
                .cookies(cookies)
                .get("/auth/loginAccount");
    }

    public Response loginStatus(final Cookies cookies) {
        return RestAssured.given()
                .spec(requestSpec)
                .cookies(cookies)
                .get("/auth/loginStatus");
    }

    public Response logout(final Cookies cookies) {
        return RestAssured.given()
                .spec(requestSpec)
                .cookies(cookies)
                .post("/auth/logout");
    }

    public Response postBook(final CreateBookRequestDto body, final Cookies cookies) {
        return RestAssured.given()
                .spec(requestSpec)
                .cookies(cookies)
                .body(body)
                .post("/books");
    }
}
