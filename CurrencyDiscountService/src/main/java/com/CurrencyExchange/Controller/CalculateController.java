package com.CurrencyExchange.Controller;

import com.CurrencyExchange.Models.BillRequest;
import com.CurrencyExchange.Models.BillResponse;
import com.CurrencyExchange.Services.DiscountService;
import com.CurrencyExchange.Services.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CalculateController
{
    @Autowired
    private DiscountService discountService;

    @Autowired
    private ExchangeRateService exchangeRateService;

    @PostMapping("/calculate")
    public BillResponse calculate(@RequestBody BillRequest bill) {
        double discountedAmount = discountService.applyDiscount(
                bill.getItems(), bill.getUserType(), bill.getCustomerTenure()
        );

        double finalAmount = exchangeRateService.convert(
                bill.getOriginalCurrency(),
                bill.getTargetCurrency(),
                discountedAmount
        );

        return new BillResponse(finalAmount, bill.getTargetCurrency());
    }

}
