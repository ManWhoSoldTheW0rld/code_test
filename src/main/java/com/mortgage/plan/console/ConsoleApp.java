package com.mortgage.plan.console;

import com.mortgage.plan.common.model.CustomerMortgageInfo;
import com.mortgage.plan.common.service.MortgagePlanService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ConsoleApp {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Usage: java com.mortgage.plan.console.ConsoleApp <file-path>");
            System.exit(1);
        }

        String filePath = args[0];

        MortgagePlanService service = new MortgagePlanService();

        try {
            InputStream inputStream = new FileInputStream(filePath);

            List<CustomerMortgageInfo> mortgageInfoList = service.getCustomerMortgageInfo(inputStream);

            for (CustomerMortgageInfo mortgageInfo : mortgageInfoList) {
                System.out.println(mortgageInfo.toString());
            }

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
