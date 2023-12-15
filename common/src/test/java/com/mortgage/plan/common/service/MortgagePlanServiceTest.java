package com.mortgage.plan.common.service;

import com.mortgage.plan.dto.MortgageInfoResult;
import com.mortgage.plan.service.MortgagePlanService;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

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

    @Test
    void calculateMonthlyPaymentDataSetFive() {
        double monthlyPayment = mortgagePlanService.calculateMonthlyPayment(376117.17, 3.56, 13);
        assertEquals(3015.31, monthlyPayment);
    }

    @Test
    void calculateMonthlyPaymentDataSetSix() {
        double monthlyPayment = mortgagePlanService.calculateMonthlyPayment(500000.0, 3.5, 10);
        assertEquals(4944.29, monthlyPayment);
    }

    @Test
    void calculateMonthlyPaymentDataSetSeven() {
        double monthlyPayment = mortgagePlanService.calculateMonthlyPayment(0.0, 0, 0);
        assertEquals(0, monthlyPayment);
    }

    @Test
    void powDataSetOne() {
        double pow = mortgagePlanService.pow(8.0, 3);
        assertEquals(512, pow);
    }

    @Test
    void powDataSetTwo() {
        double pow = mortgagePlanService.pow(12.1, 2);
        assertEquals(146.41, pow);
    }

    @Test
    void powDataSetThree() {
        double pow = mortgagePlanService.pow(-12.1, 2);
        assertEquals(146.41, pow);
    }

    @Test
    void roundToTwoDecimalsToUp() {
        double pow = mortgagePlanService.roundToTwoDecimals(1.345);
        assertEquals(1.35, pow);
    }

    @Test
    void roundToTwoDecimalsToDown() {
        double pow = mortgagePlanService.roundToTwoDecimals(1.3446);
        assertEquals(1.34, pow);
    }

    @Test
    void getCustomerMortgageInfoValidInput() throws IOException {
        String input = "Name,TotalLoan,Interest,Years\n" +
                "John Doe,100000,5,30\n" +
                "Jane Doe,150000,4.5,25\n";
        InputStream stream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));

        MortgageInfoResult result = mortgagePlanService.getCustomerMortgageInfo(stream);

        // Assert the result based on your expectations
        assertNotNull(result);
        assertEquals(0 , result.getErrors().size());
        assertEquals(2, result.getCustomersMortgageInfo().size());
    }

    @Test
    void getCustomerMortgageInfoInvalidNumberFormat() throws IOException, IOException {
        String input = "Name,TotalLoan,Interest,Years\n" +
                "John Doe,100000,invalid,30\n";
        InputStream stream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));

        MortgageInfoResult result = mortgagePlanService.getCustomerMortgageInfo(stream);

        // Assert the error messages based on your expectations
        assertNotNull(result.getCustomersMortgageInfo());
        assertNotNull(result.getErrors());
        assertTrue(result.getErrors().contains("Line 1: For input string: \"invalid\""));
    }

    @Test
    void getCustomerMortgageInfoInvalidNumberOfColumns() throws IOException, IOException {
        String input = "Name,TotalLoan,Interest,Years\n" +
                "John Doe,100000,30\n";
        InputStream stream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));

        MortgageInfoResult result = mortgagePlanService.getCustomerMortgageInfo(stream);

        // Assert the error messages based on your expectations
        assertNotNull(result.getCustomersMortgageInfo());
        assertNotNull(result.getErrors());
        assertTrue(result.getErrors().contains("Line 1: Invalid number of columns"));
    }
}
