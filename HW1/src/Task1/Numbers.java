package Task1;

import java.util.ArrayList;
import java.util.List;

/*
1.	Напишите программу, которая использует Stream API для обработки списка чисел.
Программа должна вывести на экран среднее значение всех четных чисел в списке.
 */
public class Numbers {
    public static void main(String[] args) {
        // Создание массива целых чисел
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i <= 31; i++){
            numbers.add(i);
        }
        System.out.println("\nИсходный список чисел: " + numbers);

        // Выделение из первого массива четных чисел и внесение их в новый массив
        List<Integer> evenNumbers  = numbers.stream().filter(number -> number % 2 == 0).toList();
        System.out.println("\nСписок четных чисел:   " + evenNumbers);

        // Вычисление среднего числа среди выбранных четных чисел
        int average = evenNumbers.stream().mapToInt(i -> i).sum() / evenNumbers.size();
        System.out.println("\nСреднее значение четных чисел: " + average);

    }
}