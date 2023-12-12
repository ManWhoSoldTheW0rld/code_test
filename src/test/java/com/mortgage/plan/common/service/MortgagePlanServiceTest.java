package com.mortgage.plan.common.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MortgagePlanServiceTest {

    MortgagePlanService mortgagePlanService;
    public MortgagePlanServiceTest() {
        mortgagePlanService = new MortgagePlanService();
    }

    @Test
    void calculateMonthlyPaymentDataSetOne() {
        double monthlyPayment = mortgagePlanService.calculateMonthlyPayment(1000.0, 5.0, 2);
        assertEquals(43.87, monthlyPayment);
    }

    @Test
    void calculateMonthlyPaymentDataSetTwo() {
        double monthlyPayment = mortgagePlanService.calculateMonthlyPayment(4356.0,1.27,6);
        assertEquals(62.87, monthlyPayment);
    }

    @Test
    void calculateMonthlyPaymentDataSetThree() {
        double monthlyPayment = mortgagePlanService.calculateMonthlyPayment(1300.55,8.67,2);
        assertEquals(59.22,monthlyPayment);
    }

    @Test
    void calculateMonthlyPaymentDataSetFour() {
        double monthlyPayment =  mortgagePlanService.calculateMonthlyPayment(2000.0,6.0,4);
        assertEquals(46.97, monthlyPayment);
    }
}
