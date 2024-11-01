package com.skryl.api.book;

import com.google.gson.JsonObject;
import com.skryl.model.book.*;
import io.restassured.http.Cookies;

import java.util.List;

import static org.apache.http.HttpStatus.SC_OK;

public final class BookApiStep {
    private final BookApi bookAppApi;
    private Cookies cookies;

    public BookApiStep(final BookApi bookAppApi) {
        this.bookAppApi = bookAppApi;
    }

    public LoggedInUserResponse login(final String username, final String password) {
        var body = new JsonObject();
        body.addProperty("password", password);
        body.addProperty("username", username);
        var response = bookAppApi.login(body);
        response.then().statusCode(SC_OK);
        cookies = response.getDetailedCookies();
        return response.as(LoggedInUserResponse.class);
    }

    public Boolean loginAccount() {
        var response = bookAppApi.loginAccount(cookies);
        response.then().statusCode(SC_OK);
        return response.getBody().as(Boolean.class);
    }

    public Boolean loginStatus() {
        var response = bookAppApi.loginStatus(cookies);
        response.then().statusCode(SC_OK);
        return response.getBody().as(Boolean.class);
    }

    public void logout() {
        var response = bookAppApi.logout(cookies);
        response.then().statusCode(SC_OK);
    }

    public Book createNewBook(
            String title,
            String isbn,
            BookCategory categoryId,
            BookFormat formatId
    ) {
        var body = new CreateBookRequestDto()
                .title(title)
                .isbn(isbn)
                .categoryId(categoryId.getCategoryId())
                .formatId(formatId.getFormatId());
        var response = bookAppApi.postBook(body, cookies);
        response.then().statusCode(SC_OK);
        return response.as(Book.class);
    }

}
