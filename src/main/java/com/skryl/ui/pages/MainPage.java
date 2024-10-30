package com.skryl.ui.pages;

import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Skryl D.V. on 2022-04-16
 */
@Slf4j
public final class MainPage {
    private final Page page;

    public MainPage(final Page page) {
        this.page = page;
    }

    public enum BookCategory {
        TechnicalBook("Technical Book"), Magazine("Magazine"), Novel("Novel");
        private final String category;

        public String getCategoryName() {
            return category;
        }

        BookCategory(final String category) {
            this.category = category;
        }
    }

    public enum BookFormat {
        PaperBook("Paper Book"), eBook("e-Book");
        private final String format;

        public String getFormatName() {
            return format;
        }

        BookFormat(final String format) {
            this.format = format;
        }
    }

    public MainPage addNewBook() {
        log.info("Add new book");
        page.click("button:has-text(\"add\")");
        return this;
    }

    public MainPage enterBookName(final String bookName) {
        String selector = ".md-field:has-text(\"Title\") > input";
        page.locator(selector).click();
        page.locator(selector).fill(bookName);
        return this;
    }

    public MainPage enterISBNnumber(final String number) {
        String selector = ".md-field:has-text(\"ISBN\") > input";
        page.click(selector);
        page.fill(selector, number);
        return this;
    }

    public MainPage chooseCategory(final BookCategory bookCategory) {
        page.click("text=Category Technical Book Magazine Novel >> input[type=\"text\"]");
        page.click("button:has-text(\"" + bookCategory.getCategoryName() + "\")");
        return this;
    }

    public MainPage chooseFormat(final BookFormat bookFormat) {
        page.click("text=Format Paper Book e-Book >> input[type=\"text\"]");
        page.click("button:has-text(\"" + bookFormat.getFormatName() + "\")");
        return this;
    }

    public MainPage createBook() {
        page.click("button:has-text(\"Create\")");
        page.click(":nth-match(button:has-text(\"OK\"), 4)");
        return this;
    }

    public MainPage startEdit() {
        page.click("button:has-text(\"Edit\")");
        return this;
    }

    public MainPage stopEdit() {
        page.click("button:has-text(\"Edit\")");
        page.click(":nth-match(button:has-text(\"OK\"), 4)");
        return this;
    }

    public MainPage searchBook(final String bookName) {
        page.click("input[type=\"text\"]");
        page.fill("input[type=\"text\"]", bookName);
        page.press("input[type=\"text\"]", "Enter");
        return this;
    }

}
