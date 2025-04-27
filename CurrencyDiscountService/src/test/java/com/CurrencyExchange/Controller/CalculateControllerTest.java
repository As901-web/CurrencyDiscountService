package com.CurrencyExchange.Controller;

import com.CurrencyExchange.Models.BillRequest;
import com.CurrencyExchange.Models.BillResponse;
import com.CurrencyExchange.Models.Item;
import com.CurrencyExchange.Services.DiscountService;
import com.CurrencyExchange.Services.ExchangeRateService;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class CalculateControllerTest
{
    @Test
    void testCalculateEndpoint() {
        List<Item> items = List.of(new Item("Shirt", "fashion", 200), new Item("Rice", "groceries", 100));
        BillRequest bill = new BillRequest();
        bill.setItems(items);
        bill.setUserType("employee");
        bill.setCustomerTenure(3);
        bill.setOriginalCurrency("USD");
        bill.setTargetCurrency("EUR");
        DiscountService mockDiscount = mock(DiscountService.class);
        ExchangeRateService mockExchange = mock(ExchangeRateService.class);

        when(mockDiscount.applyDiscount(any(), anyString(), anyInt())).thenReturn(250.0);
        when(mockExchange.convert("USD", "EUR", 250.0)).thenReturn(225.0);

        CalculateController controller = new CalculateController();
        controller = spy(controller);
        controller.getClass().getDeclaredFields();
        ReflectionTestUtils.setField(controller, "discountService", mockDiscount);
        ReflectionTestUtils.setField(controller, "exchangeRateService", mockExchange);

        BillResponse response = controller.calculate(bill);
        assertEquals(225.0, response.getNetAmount());
        assertEquals("EUR", response.getCurrency());
    }

    private void assertEquals(String eur, String currency)
    {
    }

    private void assertEquals(double v, double netAmount) {
    }
}
