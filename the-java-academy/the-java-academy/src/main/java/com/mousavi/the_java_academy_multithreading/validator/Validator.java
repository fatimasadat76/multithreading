package com.mousavi.the_java_academy_multithreading.validator;


import org.json.JSONObject;
import org.json.JSONArray;
import java.util.*;
import java.security.MessageDigest;

public class Validator {
    private JSONArray validAccounts = new JSONArray();

    private String encrypt(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(data.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void validateCustomer(String customerLine, List<String> accountLines, String fileName, int recordNumber) {
        String[] customerFields = customerLine.split(",");
        String customerId = customerFields[0];
        String customerName = customerFields[1];
        String customerSurname = customerFields[2];
        String customerNationalId = customerFields[3];
        String customerBirthDate = customerFields[4];

        for (String accountLine : accountLines) {
            String[] accountFields = accountLine.split(",");
            String accountId = accountFields[0];
            String customerIdFromAccount = accountFields[1];
            String accountOpenDate = accountFields[2];
            double balance = Double.parseDouble(accountFields[3]);

            if (customerId.equals(customerIdFromAccount) && balance > 1000) {
                JSONObject accountJson = new JSONObject();
                accountJson.put("CUSTOMER_ID", customerId);
                accountJson.put("FILE_NAME", fileName);
                accountJson.put("CUSTOMER_NAME", customerName);
                accountJson.put("RECORD_NUMBER", recordNumber);
                accountJson.put("ERROR_CODE", "None"); // یا کد خطا در صورت وجود
                accountJson.put("CUSTOMER_SURNAME", customerSurname);
                accountJson.put("CUSTOMER_NATIONAL_ID", customerNationalId);
                accountJson.put("ERROR_CLASSIFICATION_NAME", "Valid");
                accountJson.put("ACCOUNT_NUMBER", encrypt(accountId));
                accountJson.put("ERROR_JSON_FILE", "N/A"); // یا فایل JSON خطا در صورت وجود
                accountJson.put("ERROR_DESCRIPTION", "N/A"); // یا توضیحات خطا در صورت وجود
                accountJson.put("ACCOUNT_OPEN_DATE", accountOpenDate);
                accountJson.put("ERROR_DATE", "N/A"); // یا تاریخ خطا در صورت وجود
                accountJson.put("ACCOUNT_BALANCE", encrypt(String.valueOf(balance)));
                validAccounts.put(accountJson);
            }
        }
    }

    public JSONArray getValidAccounts() {
        return validAccounts;
    }
}