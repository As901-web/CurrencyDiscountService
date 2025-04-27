package com.CurrencyExchange.Models;

import lombok.Getter;
import lombok.Setter;
import java.util.List;


@Getter
@Setter
public class BillRequest
{
    private List<Item> items;
    private String userType; // "employee", "affiliate", "customer"
    private int customerTenure; // in years
    private String originalCurrency;
    private String targetCurrency;



}
