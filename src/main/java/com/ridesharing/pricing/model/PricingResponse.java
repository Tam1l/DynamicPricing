package com.ridesharing.pricing.model;

public class PricingResponse {
    private double finalFare;
    private double surgeMultiplier;
    private double baseFare;

    public PricingResponse() {}

    public PricingResponse(double finalFare, double surgeMultiplier, double baseFare) {
        this.finalFare = finalFare;
        this.surgeMultiplier = surgeMultiplier;
        this.baseFare = baseFare;
    }

    public double getFinalFare() {
        return finalFare;
    }

    public void setFinalFare(double finalFare) {
        this.finalFare = finalFare;
    }

    public double getSurgeMultiplier() {
        return surgeMultiplier;
    }

    public void setSurgeMultiplier(double surgeMultiplier) {
        this.surgeMultiplier = surgeMultiplier;
    }

    public double getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(double baseFare) {
        this.baseFare = baseFare;
    }
}
