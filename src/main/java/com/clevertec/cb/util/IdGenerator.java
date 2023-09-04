package com.clevertec.cb.util;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class IdGenerator {
    public static long getId() {
        return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }
}
