package com.CurrencyExchange.Services;

import com.CurrencyExchange.Models.Item;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountServiceTest
{
    private final DiscountService discountService = new DiscountService();

    private List<Item> items = List.of(
            new Item("Shirt", "fashion", 200),
            new Item("Rice", "groceries", 100)
    );

    @Test
    void testEmployeeGets30PercentOnNonGroceries() {
        double result = discountService.applyDiscount(items, "employee", 1);
        // 200 * 0.3 = 60, plus $5 on $300
        assertEquals(235, result, 0.1);
    }

    @Test
    void testAffiliateGets10PercentOnNonGroceries() {
        double result = discountService.applyDiscount(items, "affiliate", 1);
        // 200 * 0.1 = 20, plus $5 on $300
        assertEquals(275, result, 0.1);
    }

    @Test
    void testLoyalCustomerGets5PercentOnNonGroceries() {
        double result = discountService.applyDiscount(items, "customer", 3);
        // 200 * 0.05 = 10, plus $5
        assertEquals(285, result, 0.1);
    }
    @Test
    void testNoPercentageDiscountOnGroceriesOnly() {
        List<Item> groceriesOnly = List.of(new Item("Banana", "groceries", 100));
        double result = discountService.applyDiscount(groceriesOnly, "employee", 5);
        // $5 discount on $100
        assertEquals(95, result, 0.1);
    }

    @Test
    void testNoLoyaltyDiscountIfLessThan2Years() {
        double result = discountService.applyDiscount(items, "customer", 1);
        // only $5 on $300
        assertEquals(295, result, 0.1);
    }
}
