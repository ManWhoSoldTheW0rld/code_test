package com.mortgage.plan.dto;

import java.util.ArrayList;
import java.util.List;

public class MortgageInfoResult {

    private final List<MortgagePlanResponse> customersMortgageInfo;
    private final List<String> errors;

    public MortgageInfoResult() {
        this.customersMortgageInfo = new ArrayList<>() ;
        this.errors = new ArrayList<>();
    }

    public void setError(String error, Integer line) {
        this.errors.add("Line " + line + ": " + error);
    }

    public void setCustomerMortgageInfo(MortgagePlanResponse model) {
        this.customersMortgageInfo.add(model);
    }

    public List<MortgagePlanResponse> getCustomersMortgageInfo() {
        return this.customersMortgageInfo;
    }

    public List<String> getErrors() {
        return this.errors;
    }
}
