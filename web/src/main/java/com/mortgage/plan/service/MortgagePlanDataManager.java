package com.mortgage.plan.service;

import com.mortgage.plan.dto.MortgagePlanRequest;
import com.mortgage.plan.exception.ResourceNotFoundException;
import com.mortgage.plan.repository.MortgagePlanRepository;
import com.mortgage.plan.model.MortgagePlan;
import com.mortgage.plan.dto.MortgagePlanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MortgagePlanDataManager {

    private final MortgagePlanRepository mortgagePlanRepository;
    private final MortgagePlanService mortgagePlanService;

    @Autowired
    public MortgagePlanDataManager(MortgagePlanRepository mortgagePlanRepository, MortgagePlanService mortgagePlanService) {
        this.mortgagePlanRepository = mortgagePlanRepository;
        this.mortgagePlanService = mortgagePlanService;
    }

    public Integer addMortgagePlan(MortgagePlanRequest request) {

        new MortgagePlan();
        MortgagePlan plan = MortgagePlan.builder()
                .customer(request.getCustomer())
                .totalLoan(request.getTotalLoan())
                .interest(request.getInterest())
                .years(request.getYears())
                .build();

        Double monthlyPayment = mortgagePlanService.calculateMonthlyPayment(plan.getTotalLoan(), plan.getInterest(), plan.getYears());
        plan.setMonthlyPayment(monthlyPayment);

        plan = mortgagePlanRepository.save(plan);
        return plan.getId();
    }

    public List<MortgagePlanResponse> getMortgagePlanList() {
        List<MortgagePlan> plans = mortgagePlanRepository.findAll();
        return plans.stream()
                .map(plan -> MortgagePlanResponse.builder()
                        .id(plan.getId())
                        .customer(plan.getCustomer())
                        .totalLoan(plan.getTotalLoan())
                        .interest(plan.getInterest())
                        .years(plan.getYears())
                        .monthlyPayment(plan.getMonthlyPayment())
                        .build())
                .toList();
    }

    public MortgagePlanResponse getMortgagePlan(Integer id) {

        MortgagePlan plan = mortgagePlanRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Plan not found with ID: " + id)
        );
        return MortgagePlanResponse.builder()
                .id(plan.getId())
                .customer(plan.getCustomer())
                .totalLoan(plan.getTotalLoan())
                .interest(plan.getInterest())
                .years(plan.getYears())
                .monthlyPayment(plan.getMonthlyPayment())
                .build();
    }
}
