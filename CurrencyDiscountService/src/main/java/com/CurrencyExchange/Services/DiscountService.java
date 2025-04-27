package com.CurrencyExchange.Services;

import com.CurrencyExchange.Models.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountService
{
    public double applyDiscount(List<Item> items, String userType, int tenure)
    {
        double total = 0;
        double eligibleForPercentage = 0;

        for (Item item : items) {
            total += item.getPrice();
            if (!item.getCategory().equalsIgnoreCase("groceries")) {
                eligibleForPercentage += item.getPrice();
            }
        }
        double discount = 0;
        if ("employee".equalsIgnoreCase(userType)) {
            discount = 0.30 * eligibleForPercentage;
        } else if ("affiliate".equalsIgnoreCase(userType)) {
            discount = 0.10 * eligibleForPercentage;
        } else if ("customer".equalsIgnoreCase(userType) && tenure > 2) {
            discount = 0.05 * eligibleForPercentage;
        }

        double billBasedDiscount = Math.floor(total / 100) * 5;
        return total - discount - billBasedDiscount;
    }
}
