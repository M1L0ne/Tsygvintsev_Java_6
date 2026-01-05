package tsygvintsev;

import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);

    static void main(String[] args) {
        int choice;
        int taskNum;

        System.out.println("Для перехода обратно в меню и выхода вводите '-1'");
        do {
            System.out.println("Введите номер задачи (от 1 до 8): ");
            System.out.println("1 - Invoke, 2 - Default, 3 - ToString, 4 - Validate, 5 - Two");
            System.out.println("6 - Cache, 7 - Тест для Validate, 8 - Тест для Invoke");
            try {
                taskNum = sc.nextInt();
                switch (taskNum) {
                    case 1:
                        try {
                            InvokeTask invoke = new InvokeTask();
                            System.out.println("Выполняются методы с @Invoke:");
                            InvokeTask.runInvokeMethods(invoke);
                        } catch (Exception e) {
                            System.err.println("Ошибка: " + e.getMessage());
                        }
                        break;
                    case 2:
                        try {
                            DefaultTask task2 = new DefaultTask();
                            DefaultTask.printDefaultClasses(task2);
                        } catch (Exception e) {
                            System.err.println("Ошибка: " + e.getMessage());
                        }
                        System.out.println();
                        break;
                    case 3:
                        try {
                            StringTask task3 = new StringTask();
                            System.out.println("Объект: " + task3);
                            System.out.println("Результат Task3.toString(obj):");
                            System.out.println(StringTask.toString(task3));
                        } catch (Exception e) {
                            System.err.println("Ошибка: " + e.getMessage());
                        }
                        System.out.println();
                        break;
                    case 4:
                        try {
                            System.out.println("Проверка аннотации @Validate над классом ValidateTask:");
                            ValidateTask.printValidationClasses(ValidateTask.class);
                            System.out.println("Проверка аннотации @Validate над аннотацией ValidateTest:");
                            ValidateTask.printValidationClasses(ValidateTest.class);
                            System.out.println("Проверка аннотации @Validate над классом DefaultTask:");
                            ValidateTask.printValidationClasses(DefaultTask.class);
                        } catch (Exception e) {
                            System.err.println("Ошибка: " + e.getMessage());
                        }
                        System.out.println();
                        break;
                    case 5:
                        try {
                            System.out.println("Чтение значений из аннотации @Two над классом TwoTask:");
                            TwoTask.printTwoValues(TwoTask.class);
                        } catch (Exception e) {
                            System.err.println("Ошибка: " + e.getMessage());
                        }
                        System.out.println();
                        break;
                    case 6:
                        try {
                            System.out.println("Анализ аннотации @Cache над классом Task6:");
                            CacheTask.printCacheAreas(CacheTask.class);
                        } catch (Exception e) {
                            System.err.println("Ошибка: " + e.getMessage());
                        }
                        System.out.println();
                        break;
                    case 7:
                        System.out.println();
                        break;
                    case 8:
                        System.out.println();
                        break;
                    case -1:
                        System.out.println("Выход...");
                        break;
                    default:
                        System.out.println("Ошибка: введено число вне диапазона");


                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введено некорректное значение");
                return;
            }
        } while (taskNum != -1);

    }

    public static List<Integer> readIntegerList() {
        System.out.println("Введите кол-во чисел в массиве: ");
        int arrayLen = sc.nextInt();
        System.out.println("Вводите элементы массива через Enter:");
        List<Integer> list = new ArrayList<>();
        sc.nextLine();
        for (int i = 0; i < arrayLen; i++) {
            list.add(sc.nextInt());
        }
        return list;
    }

    public static void printIntegerList(List<Integer> list) {
        for (Integer element : list) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

}