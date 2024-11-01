package com.skryl.api;

import com.github.javafaker.Faker;
import com.skryl.api.book.BookApi;
import com.skryl.api.book.BookApiStep;
import com.skryl.configuration.ApplicationConfig;
import com.skryl.model.book.Book;
import com.skryl.model.book.BookCategory;
import com.skryl.model.book.BookFormat;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.*;

import static org.assertj.core.api.Assertions.assertThat;


public class BookApiTest {
    private BookApiStep bookApiStep;
    private Faker faker = new Faker();

    @BeforeSuite(groups = {"smoke"})
    public void apiSetup() {
        var config = ConfigFactory.create(ApplicationConfig.class);
        var baseUrl = config.uiUrl();
        bookApiStep = new BookApiStep(new BookApi(baseUrl));
    }

    @BeforeTest(groups = {"smoke"})
    public void login() {
        bookApiStep.login("test", "test");
    }

    @AfterTest(groups = {"smoke"})
    public void logout() {
        bookApiStep.logout();
    }

    @Test(testName = "[API] Create new book", groups = {"smoke"})
    public void createBook() {
        bookApiStep.loginStatus();
        var books = bookApiStep.createNewBook(
                "My new epic book",
                faker.code().isbn10(),
                BookCategory.TECHNICAL_BOOK,
                BookFormat.E_BOOK
        );
        assertThat(books.getTitle())
                .isEqualTo("My new epic book");
    }
}
