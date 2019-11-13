package com.company;

import java.math.BigDecimal;
import java.util.ArrayList;

class Calculator {

    static BigDecimal calculate(String operation, ArrayList<BigDecimal> values) { // Возвращает результат вычислений в зависимости от операции

        BigDecimal result;

        switch (operation) {

            case "add":
                result = add(values);
                break;

            case "mul":
                result = multiply(values);
                break;

            case "mulAndAdd":
                result = multiplyAndSum(values);
                break;

            default:
                System.out.println("Ошибка операции. Пожалуйста, используйте 'add' / 'mul' / 'mulAndAdd'");
                return null;
        }
        return result;
    }

    private static BigDecimal add(ArrayList<BigDecimal> values) { // Сложение
        BigDecimal result = new BigDecimal(0);

        for (BigDecimal value : values) {
            result = result.add(value);
        }

        return result;
    }

    private static BigDecimal multiply(ArrayList<BigDecimal> values) { // Умножение
        BigDecimal result = new BigDecimal(1);

        for (BigDecimal value : values) {
            result = result.multiply(value);
        }

        return result;
    }

    private static BigDecimal multiplyAndSum(ArrayList<BigDecimal> values) { // Умножение 2-ух и сложение с 3-им
        BigDecimal result;

        if (values.size() >= 3) {
            result = values.get(0).multiply(values.get(1)).add(values.get(2));
        } else {
            System.out.println("Для выполнения операции 'mulAndAdd' необходимо три числа");
            return null;
        }

        return result;
    }
}
