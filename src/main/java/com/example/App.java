package com.example;

import com.github.luben.zstd.Zstd;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Read text file to create dictionary
        Map<String, byte[]> dictionary = new HashMap();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("data.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                byte[] compressed = Zstd.compress(line.getBytes());
                dictionary.put(line, compressed);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(1);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.err.println("Error closing file: " + e.getMessage());
                }
            }
        }

        // Read input from standard input and compress using dictionary
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter text to compress:");
        String input = scanner.nextLine();

        if (dictionary.containsKey(input)) {
            byte[] compressed = dictionary.get(input);
            System.out.println("Compressed text --------->");
            System.out.println(new String(compressed));
        } else {
            System.out.println("Text not found in dictionary.");
        }

        scanner.close();
    }
}