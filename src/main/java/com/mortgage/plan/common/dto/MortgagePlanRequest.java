package com.mortgage.plan.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MortgagePlanRequest {
        private String customer;
        private Double totalLoan;
        private Double interest;
        private Integer years;
}
