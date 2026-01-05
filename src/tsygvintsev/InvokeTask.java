package tsygvintsev;

import java.lang.reflect.Method;

/**
 * Класс-демонстрация работы аннотации {@link Invoke}.
 * Содержит методы, часть из которых помечена для автоматического вызова.
 */

public class InvokeTask {

    /**
     * Метод, помеченный аннотацией {@link Invoke}.
     * Выводит диагностическое сообщение.
     */
    @Invoke
    public void init() {
        System.out.println("init() выполнен");
    }

    /**
     * Метод БЕЗ аннотации — не будет вызван автоматически.
     */
    public void log() {
        System.out.println("log() (не вызывается автоматически)");
    }

    /**
     * Ещё один метод с аннотацией {@link Invoke}.
     */
    @Invoke
    public void validate() {
        System.out.println("validate() выполнен");
    }

    /**
     * Вызывает все методы объекта, помеченные аннотацией {@link Invoke}.
     *
     * @param obj целевой объект; не должен быть null
     * @throws IllegalArgumentException если obj == null
     * @throws RuntimeException при ошибках вызова метода
     */
    public static void runInvokeMethods(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Объект не может быть null");
        }

        Class<?> clazz = obj.getClass();
        Method[] methods = clazz.getDeclaredMethods();

        boolean found = false;
        for (Method method : methods) {
            if (method.isAnnotationPresent(Invoke.class)) {
                found = true;
                try {
                    method.setAccessible(true);
                    method.invoke(obj);
                } catch (Exception e) {
                    throw new RuntimeException("Ошибка при вызове метода " + method.getName(), e);
                }
            }
        }

        if (!found) {
            System.out.println("Нет методов с аннотацией @Invoke.");
        }
    }
}
