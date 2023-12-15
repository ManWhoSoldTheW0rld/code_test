package com.mortgage.plan.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MortgagePlanResponse {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("customer")
    private String customer;

    @JsonProperty("total_loan")
    private Double totalLoan;

    @JsonProperty("interest")
    private Double interest;

    @JsonProperty("years")
    private Integer years;

    @JsonProperty("monthly_payment")
    private Double monthlyPayment;

    public MortgagePlanResponse(Integer id, String customer, Double totalLoan, Double interest, Integer years) {
        this.id = id;
        this.customer = customer;
        this.totalLoan = totalLoan;
        this.interest = interest;
        this.years = years;
    }

    public String toString() {
        return "****************************************************************************************************\n"
                + " Prospect " + id + ": " + customer + " wants to borrow " + totalLoan + " € for a period of "
                + years + " years and pay " + monthlyPayment + " € each month \n"
                + "****************************************************************************************************\n";
    }
}
