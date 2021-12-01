package com.company;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TimeStampGenerator {

    public static void main(String[] a) {

        System.out.println(Instant.now().toEpochMilli());
       System.out.println(Instant.now().toEpochMilli()/1000);

    }
}
