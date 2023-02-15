package com.example.jachiplus_back.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DuplicatedEmailExeption extends Exception{

    public DuplicatedEmailExeption(String message) {
        super(message);
    }
}
