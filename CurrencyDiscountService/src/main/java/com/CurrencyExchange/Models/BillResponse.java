package com.CurrencyExchange.Models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillResponse
{
    private double netAmount;
    private String currency;


    public BillResponse(double netAmount, String currency) {
        this.netAmount = netAmount;
        this.currency = currency;
    }
}
