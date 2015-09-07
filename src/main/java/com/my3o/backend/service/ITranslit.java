package com.my3o.backend.service;


import java.io.IOException;

public interface ITranslit {

    public String toTranslit(String text) throws IOException;

}
