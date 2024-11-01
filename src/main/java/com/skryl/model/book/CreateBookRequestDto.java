package com.skryl.model.book;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true, chain = true)
public class CreateBookRequestDto {
    @SerializedName("isbn")
    @Expose
    private String isbn;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("categoryId")
    @Expose
    private Integer categoryId;
    @SerializedName("formatId")
    @Expose
    private Integer formatId;
}
