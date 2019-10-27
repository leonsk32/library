package com.example.library.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class BusinessDateTimeResolverImpl implements BusinessDateTimeResolver {
    @Override
    public LocalDateTime getLocalDateTime() {
        ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
        return zdt.toLocalDateTime();
    }
}
