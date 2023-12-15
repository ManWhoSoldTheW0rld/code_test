package com.mortgage.plan.controller;

import com.mortgage.plan.service.MortgagePlanDataManager;
import com.mortgage.plan.dto.MortgagePlanRequest;
import com.mortgage.plan.dto.MortgagePlanResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/mortgage/plan")
public class MortgageController {

    private final MortgagePlanDataManager dataManager;

    @Autowired
    public MortgageController(MortgagePlanDataManager dataManager) {
        this.dataManager = dataManager;
    }

    @GetMapping
    public ResponseEntity<List<MortgagePlanResponse>> getPlans() {

        List<MortgagePlanResponse> tournaments = dataManager.getMortgagePlanList();
        return ResponseEntity.ok(tournaments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MortgagePlanResponse> getPlan(@PathVariable Integer id) {

        MortgagePlanResponse plan = dataManager.getMortgagePlan(id);
        return ResponseEntity.ok(plan);
    }

    @PostMapping
    public ResponseEntity<Void> addPlan(@Valid @RequestBody MortgagePlanRequest request,
                                       UriComponentsBuilder ucb) {

        Integer planId = dataManager.addMortgagePlan(request);

        URI locationOfNewTournament = ucb
                .path("/tournament/{id}")
                .buildAndExpand(planId)
                .toUri();

        return ResponseEntity.created(locationOfNewTournament).build();
    }
}
