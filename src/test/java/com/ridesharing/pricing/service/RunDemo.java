package com.ridesharing.pricing.service;

import com.ridesharing.pricing.model.PricingRequest;
import com.ridesharing.pricing.model.PricingResponse;

public class RunDemo {
    public static void main(String[] args) {
        PricingService service = new PricingService();
        System.out.println("\n==============================================");
        System.out.println("       DYNAMIC PRICING ALGORITHM OUTPUT       ");
        System.out.println("==============================================\n");
        
        PricingRequest r1 = new PricingRequest(10.0, 20.0, false, 10, 10);
        PricingResponse res1 = service.calculateFare(r1);
        System.out.println("Scenario 1: 10 miles, 20 mins, Normal Hours, Balanced Demand (10 riders / 10 drivers)");
        System.out.printf("   Base Fare Calculation:   $2.50 (base) + $11.50 (distance) + $5.00 (time) = $%.2f\n", res1.getBaseFare());
        System.out.printf("   Surge Multiplier:        %.1fx\n", res1.getSurgeMultiplier());
        System.out.printf("   FINAL FARE:              $%.2f\n\n", res1.getFinalFare());
        
        PricingRequest r2 = new PricingRequest(10.0, 20.0, true, 10, 10);
        PricingResponse res2 = service.calculateFare(r2);
        System.out.println("Scenario 2: 10 miles, 20 mins, PEAK Hours, Balanced Demand");
        System.out.printf("   Surge Multiplier:        %.1fx (Due to Peak Hour)\n", res2.getSurgeMultiplier());
        System.out.printf("   FINAL FARE:              $%.2f\n\n", res2.getFinalFare());
        
        PricingRequest r3 = new PricingRequest(10.0, 20.0, false, 20, 10);
        PricingResponse res3 = service.calculateFare(r3);
        System.out.println("Scenario 3: 10 miles, 20 mins, Normal Hours, HIGH Demand (20 riders / 10 drivers)");
        System.out.printf("   Surge Multiplier:        %.1fx (Due to 2.0 Demand Ratio)\n", res3.getSurgeMultiplier());
        System.out.printf("   FINAL FARE:              $%.2f\n\n", res3.getFinalFare());

        PricingRequest r4 = new PricingRequest(10.0, 20.0, false, 50, 10);
        PricingResponse res4 = service.calculateFare(r4);
        System.out.println("Scenario 4: 10 miles, 20 mins, Normal Hours, EXTREME Demand (50 riders / 10 drivers)");
        System.out.printf("   Surge Multiplier:        %.1fx (Algorithm capped at 2.5x to protect users)\n", res4.getSurgeMultiplier());
        System.out.printf("   FINAL FARE:              $%.2f\n", res4.getFinalFare());
        System.out.println("==============================================\n");
    }
}
