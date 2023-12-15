package com.mortgage.plan.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MortgagePlanRequest {

        @JsonProperty("customer")
        @NotBlank(message = "customer is required")
        @Size(min = 1, max = 255, message = "customer must have between 1 and 255 characters")
        @Pattern(regexp = "^[\\p{L}0-9 ,.'-]+$", message = "customer can only contain letters, digits, spaces, commas, periods, apostrophes, and hyphens")
        private String customer;

        @JsonProperty("total_loan")
        @NotNull(message = "total_loan is required")
        @DecimalMin(value = "0", inclusive = false, message = "total_loan must be greater than 0")
        private Double totalLoan;

        @JsonProperty("interest")
        @NotNull(message = "interest is required")
        @DecimalMin(value = "0", inclusive = false, message = "interest must be greater than 0")
        private Double interest;


        @JsonProperty("years")
        @NotNull(message = "years is required")
        @DecimalMin(value = "0", inclusive = false, message = "years must be greater than 0")
        @DecimalMax(value = "2147483647", inclusive = true, message = "years must be within the integer range")
        private Integer years;
}
