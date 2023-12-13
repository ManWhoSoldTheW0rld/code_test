package com.mortgage.plan.common.model;

import java.util.ArrayList;
import java.util.List;

public class MortgageInfoResult {

    private final List<CustomerMortgageInfo> customersMortgageInfo;
    private final List<String> errors;

    public MortgageInfoResult() {
        this.customersMortgageInfo = new ArrayList<>() ;
        this.errors = new ArrayList<>();
    }

    public void setError(String error, Integer line) {
        this.errors.add("Line " + line + ": " + error);
    }

    public void setCustomerMortgageInfo(CustomerMortgageInfo model) {
        this.customersMortgageInfo.add(model);
    }

    public List<CustomerMortgageInfo> getCustomersMortgageInfo() {
        return this.customersMortgageInfo;
    }

    public List<String> getErrors() {
        return this.errors;
    }
}
