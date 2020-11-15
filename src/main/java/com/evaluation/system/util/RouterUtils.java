package com.evaluation.system.util;

import java.util.UUID;

public class RouterUtils {

    public static String generateRandomString(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static void main(String[] args) {
        System.out.println(generateRandomString());
    }

}
