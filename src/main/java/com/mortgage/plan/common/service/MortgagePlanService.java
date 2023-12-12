package com.mortgage.plan.common.service;

import com.mortgage.plan.common.model.CustomerMortgageInfo;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class MortgagePlanService {

    public List<CustomerMortgageInfo> getCustomerMortgageInfo(InputStream stream) throws IOException
    {

        List<CustomerMortgageInfo> models = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {

            String[] nextLine;
            int line = 0;

            //Skip the header
            reader.readNext();

            while ((nextLine = reader.readNext()) != null) {
                line++;
                if (nextLine.length == 4) {
                    CustomerMortgageInfo model = new CustomerMortgageInfo(line, nextLine[0],
                            Double.parseDouble(nextLine[1]), Double.parseDouble(nextLine[2]),
                            Integer.parseInt(nextLine[3]));
                    models.add(model);

                    Double monthlyPayment = calculateMonthlyPayment(model.getTotalLoan(), model.getInterest(),
                            model.getYears());
                    model.setMonthlyPayment(monthlyPayment);
                }
            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

        return models;
    }

    public Double calculateMonthlyPayment(Double totalLoan, Double interest, Integer years) {
        Double monthlyInterest = interest / 100 / 12;
        Integer months = years * 12;
        return totalLoan * (monthlyInterest * Math.pow(1 + monthlyInterest, months)) / (Math.pow(1 + monthlyInterest, months) - 1);
    }
}
