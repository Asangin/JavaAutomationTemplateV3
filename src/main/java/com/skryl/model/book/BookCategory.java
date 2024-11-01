package com.skryl.model.book;

public enum BookCategory {
    TECHNICAL_BOOK(1), MAGAZINE(2), NOVEL(3);

    Integer categoryId;

    BookCategory(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }
}
