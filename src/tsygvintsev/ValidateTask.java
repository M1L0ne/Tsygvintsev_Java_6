package tsygvintsev;

/**
 * Класс-демонстрация аннотации {@link Validate}.
 * Проаннотирован списком классов для проверки.
 */
@Validate({String.class, Integer.class, ValidateTask.class})
public class ValidateTask {

    /**
     * Выводит список классов, указанных в аннотации {@link Validate}
     * над переданным классом.
     *
     * @param clazz класс для анализа; не должен быть null
     * @throws IllegalArgumentException если clazz == null
     * @throws IllegalStateException если аннотация @Validate отсутствует
     */
    public static void printValidationClasses(Class<?> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Класс не может быть null");
        }

        if (!clazz.isAnnotationPresent(Validate.class)) {
            throw new IllegalStateException("Класс " + clazz.getSimpleName() + " не помечен аннотацией @Validate");
        }

        Validate validate = clazz.getAnnotation(Validate.class);
        Class<?>[] classes = validate.value();

        if (classes.length == 0) {
            System.out.println("Список классов для валидации пуст.");
            return;
        }

        System.out.println("Классы для валидации (" + classes.length + "):");
        for (int i = 0; i < classes.length; i++) {
            System.out.println((i + 1) + ". " + classes[i].getSimpleName());
        }
    }

    /**
     * Возвращает массив классов из аннотации {@link Validate}.
     * <p>
     * Если аннотация отсутствует или массив пуст — выбрасывает исключение.
     *
     * @param clazz класс для анализа
     * @return непустой массив классов
     * @throws IllegalArgumentException если clazz == null, аннотация отсутствует или массив пуст
     */
    public static Class<?>[] getValidationClasses(Class<?> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Класс не может быть null");
        }

        if (!clazz.isAnnotationPresent(Validate.class)) {
            throw new IllegalArgumentException("Аннотация @Validate отсутствует");
        }

        Class<?>[] classes = clazz.getAnnotation(Validate.class).value();

        if (classes.length == 0) {
            throw new IllegalArgumentException("Массив классов в @Validate пуст");
        }

        return classes;
    }
}