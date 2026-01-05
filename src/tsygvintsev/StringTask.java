package tsygvintsev;

import java.lang.reflect.Field;

/**
 * Класс-демонстрация аннотации {@link ToString}.
 * Используется для формирования строкового представления с фильтрацией полей.
 */
@ToString
public class StringTask {
    private String name = "Alice";
    private int age = 30;

    @ToString(ToString.Mode.NO)
    private String password = "secret";

    private String email = "alice@example.com";

    /**
     * Формирует строковое представление объекта в формате:
     * {@code ClassName[field1=value1, field2=value2, ...]}
     * <p>
     * Включает только поля, помеченные {@code @ToString} со значением {@code YES}
     * (или не помеченные вообще — так как YES по умолчанию).
     *
     * @param obj объект для преобразования; не должен быть null
     * @return строковое представление
     * @throws IllegalArgumentException если obj равен null
     */
    public static String toString(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Объект не может быть null");
        }

        Class<?> clazz = obj.getClass();

        if (clazz.isAnnotationPresent(ToString.class)) {
            ToString classAnn = clazz.getAnnotation(ToString.class);
            if (classAnn.value() == ToString.Mode.NO) {
                return clazz.getSimpleName() + "{}";
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(clazz.getSimpleName()).append("[");

        Field[] fields = clazz.getDeclaredFields();
        boolean first = true;

        for (Field field : fields) {
            field.setAccessible(true);

            ToString.Mode mode = ToString.Mode.YES;
            if (field.isAnnotationPresent(ToString.class)) {
                ToString ann = field.getAnnotation(ToString.class);
                mode = ann.value();
            }

            if (mode == ToString.Mode.YES) {
                if (!first) {
                    sb.append(", ");
                }
                first = false;

                try {
                    Object value = field.get(obj);
                    sb.append(field.getName()).append("=").append(value);
                } catch (IllegalAccessException e) {
                    sb.append(field.getName()).append("=<ошибка доступа>");
                }
            }
        }

        sb.append("]");
        return sb.toString();
    }
}
