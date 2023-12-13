package com.mortgage.plan.console;

import com.mortgage.plan.common.model.CustomerMortgageInfo;
import com.mortgage.plan.common.model.MortgageInfoResult;
import com.mortgage.plan.common.service.MortgagePlanService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ConsoleApp {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Usage: java com.mortgage.plan.console.ConsoleApp <file-path>");
            System.exit(1);
        }

        String filePath = args[0];

        if (!filePath.toLowerCase().endsWith(".csv") && !filePath.toLowerCase().endsWith(".txt")) {
            System.out.println("Invalid file format. Only CSV and TXT files are supported.");
            System.exit(1);
        }

        MortgagePlanService service = new MortgagePlanService();

        try {
            InputStream inputStream = new FileInputStream(filePath);

            MortgageInfoResult mortgageInfoResult = service.getCustomerMortgageInfo(inputStream);

            for (CustomerMortgageInfo mortgageInfo : mortgageInfoResult.getCustomersMortgageInfo()) {
                System.out.println(mortgageInfo.toString());
            }

            for (String errors : mortgageInfoResult.getErrors()) {
                System.out.println(errors.toString());
            }
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
