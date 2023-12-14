package com.mortgage.plan.web.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mortgage_plan")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MortgagePlan {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "customer")
    private String customer;

    @Column(name = "total_loan")
    private Double totalLoan;

    @Column(name = "interest")
    private Double interest;

    @Column(name = "years")
    private Integer years;

    @Column(name = "monthly_payment")
    private Double monthlyPayment;
}
