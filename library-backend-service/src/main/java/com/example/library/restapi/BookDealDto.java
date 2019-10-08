package com.example.library.restapi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BookDealDto {
    private String isbn10;
    private String personId;

    // TODO なぜかSpringBootのjson <-> dto conversionで必要
    // もっと良い方法はないのか、、、
    public BookDealDto() {}
}
