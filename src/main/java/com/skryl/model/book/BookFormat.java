package com.skryl.model.book;

public enum BookFormat {
    PAPER_BOOK(1), E_BOOK(2);

    Integer formatId;

    BookFormat(Integer formatId) {
        this.formatId = formatId;
    }

    public Integer getFormatId() {
        return formatId;
    }
}
