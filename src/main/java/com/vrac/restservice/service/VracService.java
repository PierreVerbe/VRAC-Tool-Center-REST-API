package com.vrac.restservice.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class VracService {

    public int generateInt(int min, int max){
        Random random = new Random();
        return min+random.nextInt((max+1)-min);
    }

}
