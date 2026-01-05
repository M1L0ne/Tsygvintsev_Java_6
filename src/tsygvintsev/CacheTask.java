package tsygvintsev;

/**
 * Класс-демонстрация аннотации {@link Cache}.
 * Проаннотирован с указанием кешируемых областей.
 */
@Cache({"users", "profiles", "sessions"})
public class CacheTask {

    /**
     * Выводит список кешируемых областей из аннотации {@link Cache},
     * если она присутствует над классом.
     *
     * @param clazz анализируемый класс; не должен быть null
     * @throws IllegalArgumentException если clazz равен null
     */
    public static void printCacheAreas(Class<?> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Класс не может быть null");
        }

        if (!clazz.isAnnotationPresent(Cache.class)) {
            System.out.println("Класс " + clazz.getSimpleName() + " не помечен аннотацией @Cache");
            return;
        }

        Cache cache = clazz.getAnnotation(Cache.class);
        String[] areas = cache.value();

        if (areas.length == 0) {
            System.out.println("Список кешируемых областей пуст.");
        } else {
            System.out.println("Кешируемые области (" + areas.length + "):");
            for (int i = 0; i < areas.length; i++) {
                System.out.println((i + 1) + ". " + areas[i]);
            }
        }
    }
}