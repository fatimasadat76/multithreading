package com.mousavi.the_java_academy_multithreading.validator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Validator;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;

public class AccountValidator {
    private static final int THREAD_POOL_SIZE = 4;

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException, SQLException {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        List<Future<?>> futures = new ArrayList<>();

        FileManager fileManager = new FileManager();
        Validator validator = new Validator() {
            @Override
            public void reset() {

            }

            @Override
            public void validate(Source source, Result result) throws SAXException, IOException {

            }

            @Override
            public void setErrorHandler(ErrorHandler errorHandler) {

            }

            @Override
            public ErrorHandler getErrorHandler() {
                return null;
            }

            @Override
            public void setResourceResolver(LSResourceResolver resourceResolver) {

            }

            @Override
            public LSResourceResolver getResourceResolver() {
                return null;
            }
        };


        String dbUrl = "jdbc:mysql://localhost:3306/your_database";
        String user = "your_username";
        String password = "your_password";


        String customerQuery = "SELECT * FROM customers";
        String accountQuery = "SELECT * FROM accounts";

        List<String> customerLines = fileManager.readFromDatabase(customerQuery, dbUrl, user, password);
        List<String> accountLines = fileManager.readFromDatabase(accountQuery, dbUrl, user, password);
        String fileName = "database";

        int recordNumber = 1;
        for (String customerLine : customerLines) {
            final int currentRecordNumber = recordNumber;
            futures.add(executorService.submit(() -> {
                validator.validateCustomer(customerLine, accountLines, fileName, currentRecordNumber);
                return null;
            }));
            recordNumber++;
        }

        for (Future<?> future : futures) {
            future.get();
        }

        executorService.shutdown();

        // نوشتن حساب‌های معتبر به فایل JSON
        JSONArray validAccounts = validator.getValidAccounts();
        try (FileWriter file = new FileWriter("valid_accounts.json")) {
            file.write(validAccounts.toString(4)); // با چهار فاصله برای خوانایی بهتر
            file.flush();
        }

        // نوشتن حساب‌های معتبر به فایل XML
        writeAccountsToXML(validAccounts, "valid_accounts.xml");
    }

    private static void writeAccountsToXML(JSONArray accounts, String filePath) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // ریشه XML
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Accounts");
            doc.appendChild(rootElement);

            // افزودن هر حساب به XML
            for (int i = 0; i < accounts.length(); i++) {
                JSONObject account = accounts.getJSONObject(i);

                Element accountElement = doc.createElement("Account");
                rootElement.appendChild(accountElement);

                Element customerId = doc.createElement("CUSTOMER_ID");
                customerId.appendChild(doc.createTextNode(account.getString("CUSTOMER_ID")));
                accountElement.appendChild(customerId);

                Element fileName = doc.createElement("FILE_NAME");
                fileName.appendChild(doc.createTextNode(account.getString("FILE_NAME")));
                accountElement.appendChild(fileName);

                Element customerName = doc.createElement("CUSTOMER_NAME");
                customerName.appendChild(doc.createTextNode(account.getString("CUSTOMER_NAME")));
                accountElement.appendChild(customerName);
                Element recordNumber = doc.createElement("RECORD_NUMBER");
                recordNumber.appendChild(doc.createTextNode(String.valueOf(account.getInt("RECORD_NUMBER"))));
                accountElement.appendChild(recordNumber);

                Element errorCode = doc.createElement("ERROR_CODE");
                errorCode.appendChild(doc.createTextNode(account.getString("ERROR_CODE")));
                accountElement.appendChild(errorCode);

                Element customerSurname = doc.createElement("CUSTOMER_SURNAME");
                customerSurname.appendChild(doc.createTextNode(account.getString("CUSTOMER_SURNAME")));
                accountElement.appendChild(customerSurname);

                Element customerNationalId = doc.createElement("CUSTOMER_NATIONAL_ID");
                customerNationalId.appendChild(doc.createTextNode(account.getString("CUSTOMER_NATIONAL_ID")));
                accountElement.appendChild(customerNationalId);

                Element errorClassificationName = doc.createElement("ERROR_CLASSIFICATION_NAME");
                errorClassificationName.appendChild(doc.createTextNode(account.getString("ERROR_CLASSIFICATION_NAME")));
                accountElement.appendChild(errorClassificationName);

                Element accountNumber = doc.createElement("ACCOUNT_NUMBER");
                accountNumber.appendChild(doc.createTextNode(account.getString("ACCOUNT_NUMBER")));
                accountElement.appendChild(accountNumber);

                Element errorJsonFile = doc.createElement("ERROR_JSON_FILE");
                errorJsonFile.appendChild(doc.createTextNode(account.getString("ERROR_JSON_FILE")));
                accountElement.appendChild(errorJsonFile);

                Element errorDescription = doc.createElement("ERROR_DESCRIPTION");
                errorDescription.appendChild(doc.createTextNode(account.getString("ERROR_DESCRIPTION")));
                accountElement.appendChild(errorDescription);

                Element accountOpenDate = doc.createElement("ACCOUNT_OPEN_DATE");
                accountOpenDate.appendChild(doc.createTextNode(account.getString("ACCOUNT_OPEN_DATE")));
                accountElement.appendChild(accountOpenDate);

                Element errorDate = doc.createElement("ERROR_DATE");
                errorDate.appendChild(doc.createTextNode(account.getString("ERROR_DATE")));
                accountElement.appendChild(errorDate);

                Element accountBalance = doc.createElement("ACCOUNT_BALANCE");
                accountBalance.appendChild(doc.createTextNode(account.getString("ACCOUNT_BALANCE")));
                accountElement.appendChild(accountBalance);
            }

            // نوشتن محتوای XML به فایل
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace(); // یا چاپ یا لاگ خطا
        }
    }
}