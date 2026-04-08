package com.ridesharing.pricing.model;

public class PricingRequest {
    private double distanceMiles;
    private double durationMinutes;
    private boolean isPeakHour;
    private int activeRiders;
    private int activeDrivers;

    public PricingRequest() {}

    public PricingRequest(double distanceMiles, double durationMinutes, boolean isPeakHour, int activeRiders, int activeDrivers) {
        this.distanceMiles = distanceMiles;
        this.durationMinutes = durationMinutes;
        this.isPeakHour = isPeakHour;
        this.activeRiders = activeRiders;
        this.activeDrivers = activeDrivers;
    }

    public double getDistanceMiles() {
        return distanceMiles;
    }

    public void setDistanceMiles(double distanceMiles) {
        this.distanceMiles = distanceMiles;
    }

    public double getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(double durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public boolean isPeakHour() {
        return isPeakHour;
    }

    public void setPeakHour(boolean peakHour) {
        isPeakHour = peakHour;
    }

    public int getActiveRiders() {
        return activeRiders;
    }

    public void setActiveRiders(int activeRiders) {
        this.activeRiders = activeRiders;
    }

    public int getActiveDrivers() {
        return activeDrivers;
    }

    public void setActiveDrivers(int activeDrivers) {
        this.activeDrivers = activeDrivers;
    }
}
