package com.example.execute_qr.model;

import lombok.Data;

@Data
public class QrRequest {

    private String tradeId;
    private Integer amount;
    private String currency;
    private String description;
    private Boolean isSingleUse;
    private Integer expirationInDays;
    private String clearingHouse;
    private Boolean image;

}