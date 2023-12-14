package com.mortgage.plan.console;

import com.mortgage.plan.common.dto.MortgagePlanResponse;
import com.mortgage.plan.common.dto.MortgageInfoResult;
import com.mortgage.plan.common.service.MortgagePlanService;

import java.io.IOException;

public class ConsoleApp {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Usage: java com.mortgage.plan.console.ConsoleApp <file-path>");
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
