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
    @Column(name = "id")
    private Integer id;

    @Column(name = "customer", nullable = false)
    private String customer;

    @Column(name = "total_loan", nullable = false)
    private Double totalLoan;

    @Column(name = "interest", nullable = false)
    private Double interest;

    @Column(name = "years", nullable = false)
    private Integer years;

    @Column(name = "monthly_payment", nullable = false)
    private Double monthlyPayment;
}
