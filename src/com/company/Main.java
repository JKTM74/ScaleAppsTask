package com.company;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Main {

    private static ArrayList<BigDecimal> values = new ArrayList<>();
    private static String operation;

    public static void main(String[] args) {

        if (args.length == 0){
            System.out.println("Введите аргументы.");
            return;
        }

        if (args[0].equals("-")) {
            getData(new InputStreamReader(System.in));
        } else {
            try {
                getData(new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8));
            } catch (FileNotFoundException e) {
                System.out.println("Файл с параметрами не найден.");
                return;
            }
        }

        if (operation == null || operation.isEmpty()) {
            System.out.println("Вы не ввели операцию.");
            return;
        }

        if (values.isEmpty()) {
            System.out.println("Вы не ввели числа.");
            return;
        }

        BigDecimal result = Calculator.calculate(operation, values);

        if (result == null){
            return;
        }

        if (args.length >= 2 && !args[0].equals("-"))
            try (FileWriter output = new FileWriter(args[1])) {
                output.write(String.valueOf(result));
            } catch (IOException e) {
                e.getMessage();
            }
        else {
            System.out.println("Ответ: " + result);
        }
    }

    private static void getData(InputStreamReader inputStreamReader) { // Получаем операцию и значения из файла или консоли.

        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {

            String input = reader.readLine();

            if (input.contains(" "))
                operation = input.substring(0, input.indexOf(" "));
            else
                operation = input;

            input = input.substring(input.indexOf(" ") + 1);
            String[] stringValues = input.split(" ", input.length());

            for (String value : stringValues) {
                if (isNumeric(value)) {
                    values.add(new BigDecimal(value));
                }
            }

        } catch (IOException e) {
            e.getMessage();
        }
    }

    private static boolean isNumeric(String str) { // Проверка, является ли элемент числом.
        try {
            new BigDecimal(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
