package com.mousavi.the_java_academy_multithreading.thread;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import javax.crypto.SecretKey;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class CustomerCSVReaderThread extends CSVReaderThread implements Runnable {
        private String filePath;
        private BlockingQueue<String[]> queue;
        private BlockingQueue<Map<String, String>> errorQueue;
        private SecretKey secretKey;
        private String fileName;

        public CustomerCSVReaderThread(String filePath, BlockingQueue<String[]> queue, BlockingQueue<Map<String, String>> errorQueue) throws Exception {
            super();
            this.filePath = filePath;
            this.queue = queue;
            this.errorQueue = errorQueue;
            this.secretKey = generateKey();
            this.fileName = filePath;
        }

        @Override
        public void run() {
            try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
                String[] line;
                while ((line = csvReader.readNext()) != null) {
                    try {
                        line[4] = decrypt(line[4]); // CUSTOMER_NAME
                        line[5] = decrypt(line[5]); // CUSTOMER_SURNAME
                        queue.put(line);
                    } catch (Exception e) {
                        Map<String, String> error = new HashMap<>();
                        error.put("ERROR_DATE", new Date().toString());
                        error.put("FILE_NAME", fileName);
                        error.put("RECORD_NUMBER", line[6]);
                        error.put("ERROR_CODE", "DECRYPT_ERROR");
                        error.put("ERROR_CLASSIFICATION_NAME", "Decryption Error");
                        error.put("ERROR_DESCRIPTION", e.getMessage());
                        errorQueue.put(error);
                    }
                }
            } catch (IOException | InterruptedException | CsvValidationException e) {
                e.printStackTrace();
            }
        }


    }