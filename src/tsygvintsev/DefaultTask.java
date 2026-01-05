package tsygvintsev;

import java.lang.reflect.Field;

/**
 * Класс-демонстрация аннотации {@link Default}.
 * Содержит примеры использования аннотации над классом и полем.
 */
@Default(String.class)
public class DefaultTask {
    @Default(Integer.class)
    private int id;

    @Default(DefaultTask.class)
    private Object fallback;

    /**
     * Выводит информацию о классах по умолчанию для переданного объекта:
     * - для его класса (если есть {@code @Default})
     * - для каждого поля (если поле проаннотировано {@code @Default})
     *
     * @param obj целевой объект; не должен быть null
     * @throws IllegalArgumentException если obj == null
     */
    public static void printDefaultClasses(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Объект не может быть null");
        }

        Class<?> clazz = obj.getClass();

        if (clazz.isAnnotationPresent(Default.class)) {
            Default classDefault = clazz.getAnnotation(Default.class);
            System.out.println("Класс по умолчанию для " + clazz.getSimpleName() + ": " +
                    classDefault.value().getSimpleName());
        } else {
            System.out.println("У класса " + clazz.getSimpleName() + " нет аннотации @Default");
        }

        Field[] fields = clazz.getDeclaredFields();
        boolean fieldFound = false;
        for (Field field : fields) {
            if (field.isAnnotationPresent(Default.class)) {
                fieldFound = true;
                Default fieldDefault = field.getAnnotation(Default.class);
                System.out.println("Поле '" + field.getName() + "', класс по умолчанию: " +
                        fieldDefault.value().getSimpleName());
            }
        }

        if (!fieldFound) {
            System.out.println("Ни одно поле не помечено аннотацией @Default");
        }
    }
}
