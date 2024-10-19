package com.mousavi.the_java_academy_multithreading.thread;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class CSVReaderThread implements Runnable {
    private String filePath;
    private BlockingQueue<String[]> queue;
    private BlockingQueue<Map<String, String>> errorQueue;
    private SecretKey secretKey;
    private String fileName;

    public CSVReaderThread(String filePath, BlockingQueue<String[]> queue, BlockingQueue<Map<String, String>> errorQueue) throws Exception {
        this.filePath = filePath;
        this.queue = queue;
        this.errorQueue = errorQueue;
        this.secretKey = generateKey();
        this.fileName = filePath;
    }

    public CSVReaderThread() {

    }

    @Override
    public void run() {
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                try {
                    line[2] = decrypt(line[2]);
                    line[5] = decrypt(line[5]);
                    line[7] = decrypt(line[7]);
                    line[12] = decrypt(line[12]);
                    line[13] = decrypt(line[13]);
                    queue.put(line);
                } catch (Exception e) {
                    Map<String, String> error = new HashMap<>();
                    error.put("ERROR_DATE", new Date().toString());
                    error.put("FILE_NAME", fileName);
                    error.put("RECORD_NUMBER", line[0]);
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

    public SecretKey generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        return keyGenerator.generateKey();
    }

    public String decrypt(String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedValue = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedValue = cipher.doFinal(decodedValue);
        return new String(decryptedValue);
    }
}

