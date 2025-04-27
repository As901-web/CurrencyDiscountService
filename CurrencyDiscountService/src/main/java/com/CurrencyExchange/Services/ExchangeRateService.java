package com.CurrencyExchange.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ExchangeRateService
{
    @Value("${exchange.api.url}")
    private String apiUrl;

    @Value("${exchange.api.key}")
    private String apiKey;

    public double convert(String from, String to, double amount) {
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + from + "?apikey=" + apiKey;

        Map response = restTemplate.getForObject(url, Map.class);
        Map<String, Double> rates = (Map<String, Double>) response.get("rates");
        Double rate = rates.get(to);
        return amount * rate;
    }
}
