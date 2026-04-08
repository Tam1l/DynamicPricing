package com.ridesharing.pricing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.ridesharing.pricing.model.PricingRequest;
import com.ridesharing.pricing.model.PricingResponse;

public class PricingServiceTest {

    private PricingService pricingService;

    @BeforeEach
    public void setup() {
        pricingService = new PricingService();
    }

    @Test
    public void testNonPeakPricing_StandardDemand() {
        // 10 miles, 20 mins -> Base = 2.50 + (10 * 1.15) + (20 * 0.25) = 2.50 + 11.50 + 5.00 = 19.00
        PricingRequest request = new PricingRequest(10.0, 20.0, false, 10, 10);
        PricingResponse response = pricingService.calculateFare(request);
        
        assertEquals(19.00, response.getFinalFare(), 0.01);
        assertEquals(1.0, response.getSurgeMultiplier(), 0.01);
    }

    @Test
    public void testPeakPricing_StandardDemand() {
        // 10 miles, 20 mins -> Base = 19.00 * 1.5 (peak multiplier) = 28.50
        PricingRequest request = new PricingRequest(10.0, 20.0, true, 10, 10);
        PricingResponse response = pricingService.calculateFare(request);
        
        assertEquals(28.50, response.getFinalFare(), 0.01);
        assertEquals(1.5, response.getSurgeMultiplier(), 0.01);
    }

    @Test
    public void testNonPeakPricing_HighDemandSurge() {
        // High rider to driver ratio (20 riders / 10 drivers = 2.0 surge)
        // Base = 19.00 * 2.0 = 38.00
        PricingRequest request = new PricingRequest(10.0, 20.0, false, 20, 10);
        PricingResponse response = pricingService.calculateFare(request);
        
        assertEquals(38.00, response.getFinalFare(), 0.01);
        assertEquals(2.0, response.getSurgeMultiplier(), 0.01);
    }

    @Test
    public void testCapOnDemandSurge() {
        // Exceptionally high demand (50 riders / 10 drivers = 5.0 surge, but capped at 2.5)
        PricingRequest request = new PricingRequest(10.0, 20.0, false, 50, 10);
        PricingResponse response = pricingService.calculateFare(request);
        
        assertEquals(19.00 * 2.5, response.getFinalFare(), 0.01);
        assertEquals(2.5, response.getSurgeMultiplier(), 0.01);
    }
}
