package com.skryl.model.book;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Category {

@SerializedName("id")
@Expose
public Integer id;
@SerializedName("name")
@Expose
public String name;

}