package com.mortgage.plan.web.controller;

import com.mortgage.plan.common.model.MortgageInfoResult;
import com.mortgage.plan.common.service.MortgagePlanService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MortgageController {

    private final MortgagePlanService mortgagePlanService;

    public MortgageController(MortgagePlanService mortgagePlanService) {
        this.mortgagePlanService = mortgagePlanService;
    }

    @GetMapping("/plan")
    public MortgageInfoResult getPlans(@RequestParam("file") MultipartFile file) throws IOException {
        InputStream stream = file.getInputStream();
        return mortgagePlanService.getCustomerMortgageInfo(stream);
    }
}
