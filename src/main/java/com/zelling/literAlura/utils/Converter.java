package com.zelling.literAlura.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zelling.literAlura.model.IConverter;

public class Converter implements IConverter {
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T convertData(String json, Class<T> tClass) {
        try{
           return mapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T convertDataToList(String json, Class<T> tClass){
        try{
            return mapper.readerForListOf(tClass).readValue(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert json, error: " + e.getLocalizedMessage());
        }
    }
}
