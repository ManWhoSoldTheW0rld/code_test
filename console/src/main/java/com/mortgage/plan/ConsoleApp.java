package com.mortgage.plan;

import com.mortgage.plan.dto.MortgagePlanResponse;
import com.mortgage.plan.dto.MortgageInfoResult;
import com.mortgage.plan.service.MortgagePlanService;

import java.io.IOException;

public class ConsoleApp {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Usage: java -jar target/mortgage-plan.jar <file-path>");
            System.exit(1);
        }

        String filePath = args[0];

        MortgagePlanService service = new MortgagePlanService();

        try {
            MortgageInfoResult mortgageInfoResult = service.getCustomerMortgageInfoFromCsvFile(filePath);

            for (MortgagePlanResponse mortgageInfo : mortgageInfoResult.getCustomersMortgageInfo()) {
                System.out.println(mortgageInfo.toString());
            }

            for (String errors : mortgageInfoResult.getErrors()) {
                System.out.println(errors);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
