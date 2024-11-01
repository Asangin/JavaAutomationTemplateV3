package com.skryl.model.book;

public enum BookCategory {
    TECHNICAL_BOOK(1), MAGAZINE(2), NOVEL(3);

    private final Integer categoryId;

    BookCategory(final Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }
}

