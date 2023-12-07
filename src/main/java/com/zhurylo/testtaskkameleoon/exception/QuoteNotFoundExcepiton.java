package com.zhurylo.testtaskkameleoon.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QuoteNotFoundExcepiton extends Exception{

    private String message;

    public String getMessage(){
        return message;

    }
}
