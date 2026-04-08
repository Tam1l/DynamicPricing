package com.ridesharing.pricing.service;

import org.springframework.stereotype.Service;
import com.ridesharing.pricing.model.PricingRequest;
import com.ridesharing.pricing.model.PricingResponse;

@Service
public class PricingService {
    
    private static final double BASE_FARE = 2.50;
    private static final double PER_MILE_RATE = 1.15;
    private static final double PER_MINUTE_RATE = 0.25;
    private static final double PEAK_SURGE_MULTIPLIER = 1.5;

    public PricingResponse calculateFare(PricingRequest request) {
        double distanceCharge = request.getDistanceMiles() * PER_MILE_RATE;
        double timeCharge = request.getDurationMinutes() * PER_MINUTE_RATE;
        
        double baseFareTotal = BASE_FARE + distanceCharge + timeCharge;
        
        // Demand surge: e.g. ratio of riders to drivers
        double demandSurge = 1.0;
        if (request.getActiveDrivers() > 0) {
             double demandRatio = (double) request.getActiveRiders() / request.getActiveDrivers();
             if (demandRatio > 1.2) {
                 demandSurge = Math.min(demandRatio, 2.5); // cap surge at 2.5x from demand
             }
        }
        
        // Time-based peak surge
        double timeSurge = request.isPeakHour() ? PEAK_SURGE_MULTIPLIER : 1.0;
        
        // Total combined surge multiplier (max of demand or time to avoid excessive charges)
        double totalMultiplier = Math.max(demandSurge, timeSurge);
        
        double finalFare = baseFareTotal * totalMultiplier;
        
        // Round final fare to 2 decimal places
        double roundedFare = Math.round(finalFare * 100.0) / 100.0;
        
        return new PricingResponse(roundedFare, totalMultiplier, baseFareTotal);
    }
}
