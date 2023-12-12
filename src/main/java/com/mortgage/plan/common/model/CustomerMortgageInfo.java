package com.mortgage.plan.common.model;

import lombok.Data;

@Data
public class CustomerMortgageInfo {

        private Integer line;
        private String customer;
        private Double totalLoan;
        private Double interest;
        private Integer years;
        private Double monthlyPayment;

        public CustomerMortgageInfo(Integer line, String customer, Double totalLoan, Double interest, Integer years) {
            this.line = line;
            this.customer = customer;
            this.totalLoan = totalLoan;
            this.interest = interest;
            this.years = years;
        }

    public String toString() {
        return "****************************************************************************************************\n"
                + " Prospect " + line + ": " + customer + " wants to borrow " + totalLoan + " € for a period of "
                + years + " years and pay " + monthlyPayment + " € each month \n"
                + "****************************************************************************************************\n";
    }
}
