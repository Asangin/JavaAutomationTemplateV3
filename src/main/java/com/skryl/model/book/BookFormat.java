package com.skryl.model.book;

public enum BookFormat {
    PAPER_BOOK(1), E_BOOK(2);

    private final Integer formatId;

    BookFormat(final Integer formatId) {
        this.formatId = formatId;
    }

    public Integer getFormatId() {
        return formatId;
    }
}

