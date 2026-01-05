package tsygvintsev;

/**
 * Класс-демонстрация аннотации {@link Two}.
 * Проаннотирован с конкретными значениями first и second.
 */
@Two(first = "Привет", second = 42)
public class TwoTask {

    /**
     * Выводит значения свойств аннотации {@link Two}, если она присутствует над классом.
     *
     * @param clazz анализируемый класс; не должен быть null
     * @throws IllegalArgumentException если clazz == null
     * @throws IllegalStateException если аннотация @Two отсутствует
     */
    public static void printTwoValues(Class<?> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Класс не может быть null");
        }

        if (!clazz.isAnnotationPresent(Two.class)) {
            throw new IllegalStateException("Класс " + clazz.getSimpleName() + " не помечен аннотацией @Two");
        }

        Two ann = clazz.getAnnotation(Two.class);
        System.out.println("Значение first: \"" + ann.first() + "\"");
        System.out.println("Значение second: " + ann.second());
    }
}