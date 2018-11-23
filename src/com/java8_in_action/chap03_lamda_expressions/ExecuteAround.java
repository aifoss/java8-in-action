package com.java8_in_action.chap03_lamda_expressions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by sofia on 12/22/16.
 */
public class ExecuteAround {

    public static String processFileLimited() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("src/org/java8/winterbe/misc/test2.txt"))) {
            return br.readLine();
        }
    }

    public interface BufferedReaderProcessor {
        String process(BufferedReader b) throws IOException;
    }

    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("src/org/java8/winterbe/misc/test2.txt"))) {
            return p.process(br);
        }
    }


    public static void main(String[] args) throws IOException {
        String result = processFileLimited();
        System.out.println(result);

        System.out.println("---");

        String oneLine = processFile((BufferedReader b) -> b.readLine());
        System.out.println(oneLine);

        String twoLines = processFile((BufferedReader b) -> b.readLine() + "\n" + b.readLine());
        System.out.println(twoLines);
    }

}
