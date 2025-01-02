package com.zelling.literAlura.model;

public interface IConverter {
    <T> T convertData(String json, Class<T> tClass);
}
