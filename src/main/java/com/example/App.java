package com.example;

import com.github.luben.zstd.Zstd;
import com.github.luben.zstd.ZstdDictCompress;
import java.util.Scanner;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter text to compress:");
        String text = scanner.nextLine();

        // Compress text using Zstd
        byte[] compressed = Zstd.compress(text.getBytes());

        Dictionary<String, String> dict= new Hashtable();
        dict.put(text, new String(compressed));
        System.out.println("Compressed text --------->");
        System.out.println(dict);

        scanner.close();
    }
}