package ru.tegorov.sample;

public class Calculator {

    public int sum(int... numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum = sum + number;
        }
        return sum;
    }
}
