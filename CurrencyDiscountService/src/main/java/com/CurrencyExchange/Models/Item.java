package com.CurrencyExchange.Models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Item
{
    private String name;
    private String category;
    private double price;

    public Item(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }
}
