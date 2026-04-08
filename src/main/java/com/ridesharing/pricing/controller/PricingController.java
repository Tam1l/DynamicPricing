package com.ridesharing.pricing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ridesharing.pricing.model.PricingRequest;
import com.ridesharing.pricing.model.PricingResponse;
import com.ridesharing.pricing.service.PricingService;

@RestController
@RequestMapping("/api/v1/pricing")
public class PricingController {

    private final PricingService pricingService;

    @Autowired
    public PricingController(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<PricingResponse> calculateFare(@RequestBody PricingRequest request) {
        PricingResponse response = pricingService.calculateFare(request);
        return ResponseEntity.ok(response);
    }
}
