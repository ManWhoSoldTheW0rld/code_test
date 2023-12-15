package com.mortgage.plan.service;

import com.mortgage.plan.dto.MortgagePlanRequest;
import com.mortgage.plan.dto.MortgagePlanResponse;
import com.mortgage.plan.dto.MortgageInfoResult;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Set;

@Service
public class MortgagePlanService {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public MortgageInfoResult getCustomerMortgageInfoFromCsvFile(String filePath) throws IOException {

        if (!filePath.toLowerCase().endsWith(".csv") && !filePath.toLowerCase().endsWith(".txt")) {
            throw new IllegalArgumentException("Invalid file type");
        }
        InputStream inputStream = new FileInputStream(filePath);
        return getCustomerMortgageInfo(inputStream);
    }

    public MortgageInfoResult getCustomerMortgageInfo(InputStream inputStream) throws IOException {

        MortgageInfoResult result = new MortgageInfoResult();

        try (CSVReader reader = new CSVReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            String[] nextLine;
            int line = 0;

            //Skip the header
            reader.readNext();

            while ((nextLine = reader.readNext()) != null) {
                line++;
                if (nextLine.length == 4) {
                    try {

                        MortgagePlanRequest request = new MortgagePlanRequest(nextLine[0],
                                Double.parseDouble(nextLine[1]), Double.parseDouble(nextLine[2]),
                                Integer.parseInt(nextLine[3]));

                        Set<ConstraintViolation<MortgagePlanRequest>> violations = validator.validate(request);
                        if (!violations.isEmpty()) {
                            for (ConstraintViolation<MortgagePlanRequest> violation : violations) {
                                result.setError(violation.getMessage(), line);
                            }
                            continue;
                        }

                        MortgagePlanResponse model = createCustomerMortgageInfo(line, request);

                        result.setCustomerMortgageInfo(model);
                    } catch (NumberFormatException e) {
                        result.setError(e.getMessage(), line);
                    }
                } else {
                    if (nextLine.length > 0 && !nextLine[0].isEmpty()) {
                        result.setError("Invalid number of columns", line);
                    }
                }
            }

            inputStream.close();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public MortgagePlanResponse createCustomerMortgageInfo(int id, MortgagePlanRequest request) {
        MortgagePlanResponse model = new MortgagePlanResponse(id, request.getCustomer(),
                request.getTotalLoan(), request.getInterest(), request.getYears());

        Double monthlyPayment = calculateMonthlyPayment(model.getTotalLoan(), model.getInterest(), model.getYears());
        model.setMonthlyPayment(monthlyPayment);
        return model;
    }

    public double calculateMonthlyPayment(double totalLoan, double interest, int years) {

        double monthlyInterest = interest / 100 / 12;

        int months = years * 12;

        double monthlyPayment =  totalLoan * (monthlyInterest * pow(1 + monthlyInterest, months)) / (pow(1 + monthlyInterest, months) - 1);

        monthlyPayment = roundToTwoDecimals(monthlyPayment);

        return monthlyPayment;
    }

    public double roundToTwoDecimals(Double value) {
        return (double) ((long) (value * 100 + 0.5)) / 100;
    }

    public double pow(Double base, Integer exponent) {
        if (exponent == 0) {
            return 1.0;
        }

        Double result = base;
        for (int i = 1; i < exponent; i++) {
            result *= base;
        }
        return result;
    }
}
