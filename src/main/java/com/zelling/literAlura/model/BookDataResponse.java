package com.zelling.literAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDataResponse (
        Integer count,
        @JsonAlias("results") ArrayList<BookData> books
){
}
