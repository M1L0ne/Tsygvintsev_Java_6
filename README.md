# Цыгвинцев Олег Сергеевич Лабораторная №6

# Задание 1
## Задача 1
### Текст задачи
@Invoke.  
Разработайте аннотацию @Invoke, со следующими характеристиками:

• Целью может быть только МЕТОД

• Доступна во время исполнения программы

• Не имеет свойств

Создайте класс, содержащий несколько методов, и проаннотируйте хотя бы один из них
аннотацией @Invoke.
Реализуйте обработчик (через Reflection API), который находит методы, отмеченные
аннотацией @Invoke, и вызывает их автоматически.
### Алгоритм решения
    package tsygvintsev;
    
    import java.lang.annotation.ElementType;
    import java.lang.annotation.Retention;
    import java.lang.annotation.RetentionPolicy;
    import java.lang.annotation.Target;
    
    /**
    * Аннотация, помечающая метод для автоматического вызова.
    * <p>
    * Может применяться только к методам.
    * Сохраняется в runtime для обработки через Reflection API.
    */
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Invoke {
    }


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


# Задание 1
## Задача 2
### Текст задачи
@Default.  
Разработайте аннотацию @Default, со следующими характеристиками:

• Целью может быть ТИП или ПОЛЕ

• Доступна во время исполнения программы

• Имеет обязательное свойство value типа Class

Проаннотируйте какой-либо класс данной аннотацией, указав тип по умолчанию.
Напишите обработчик, который выводит имя указанного класса по умолчанию.
### Алгоритм решения
    package tsygvintsev;
    
    import java.lang.annotation.ElementType;
    import java.lang.annotation.Retention;
    import java.lang.annotation.RetentionPolicy;
    import java.lang.annotation.Target;

    /**
    * Аннотация, указывающая класс по умолчанию.
    * <p>
    * Может применяться к типам (классам) и полям.
    * Обязательно содержит ссылку на класс через {@code value()}.
    */
    @Target({ElementType.TYPE, ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Default {
        /**
        * Указывает класс по умолчанию.
        *     
        * @return класс, используемый по умолчанию
        */
        Class<?> value();
    }


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


# Задание 1
## Задача 3
### Текст задачи
@ToString.
Разработайте аннотацию @ToString, со следующими характеристиками:

• Целью может быть ТИП или ПОЛЕ

• Доступна во время исполнения программы

• Имеет необязательное свойство valuec двумя вариантами значений: YES или NO

• Значение свойства по умолчанию: YES

Проаннотируйте класс аннотацией @ToString, а одно из полей – с @ToString(Mode.NO).
Создайте метод, который формирует строковое представление объекта, учитывая только те поля,
где @ToString имеет значение YES.
### Алгоритм решения
    package tsygvintsev;
    
    import java.lang.annotation.ElementType;
    import java.lang.annotation.Retention;
    import java.lang.annotation.RetentionPolicy;
    import java.lang.annotation.Target;
    
    /**
    * Управляет включением поля или класса в строковое представление.
    * <p>
    * Применяется к типам и полям. По умолчанию включено (YES).
    */
    @Target({ElementType.TYPE, ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ToString {
    
        /**
        * Режим включения в toString.
        */
        enum Mode { YES, NO }
        
        /**
        * Указывает, следует ли включать элемент в строковое представление.
        *
        * @return режим; по умолчанию YES
        */
        Mode value() default Mode.YES;
    }


    package tsygvintsev;

    import java.lang.reflect.Field;

    /**
    * Класс-демонстрация аннотации {@link ToString}.
    * Используется для формирования строкового представления с фильтрацией полей.
    */
    @ToString
    public class ToStringTask {
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


# Задание 1
## Задача 4
### Текст задачи
@Validate.  
Разработайте аннотацию @Validate, со следующими характеристиками:

• Целью может быть ТИП или АННОТАЦИЯ

• Доступна во время исполнения программы

• Имеет обязательное свойство value, типа Class[]

Проаннотируйте класс аннотацией @Validate, передав список типов для проверки.
Реализуйте обработчик, который выводит, какие классы указаны в аннотации.
### Алгоритм решения
    package tsygvintsev;
    
    import java.lang.annotation.ElementType;
    import java.lang.annotation.Retention;
    import java.lang.annotation.RetentionPolicy;
    import java.lang.annotation.Target;
    
    /**
    * Указывает, какие классы должны участвовать в валидации.
    * <p>
    * Применяется к типам и другим аннотациям.
    * Содержит массив классов для проверки.
    */
    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Validate {
    
        /**
        * Массив классов, подлежащих валидации.
        *
        * @return непустой массив классов
        */
        Class<?>[] value();
    }


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


# Задание 1
## Задача 5
### Текст задачи
@Two.
Разработайте аннотацию @Two, со следующими характеристиками:

• Целью может быть ТИП

• Доступна во время исполнения программы

• Имеет два обязательных свойства: first типа String и second типа int

Проаннотируйте какой-либо класс аннотацией @Two, передав строковое и числовое значения.
Реализуйте обработчик, который считывает и выводит значения этих свойств.
### Алгоритм решения
    package tsygvintsev;
    
    import java.lang.annotation.ElementType;
    import java.lang.annotation.Retention;
    import java.lang.annotation.RetentionPolicy;
    import java.lang.annotation.Target;
    
    /**
    * Аннотация с двумя обязательными параметрами: строкой и числом.
    * <p>
    * Применяется только к типам (классам, интерфейсам и т.д.).
    */
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Two {
    
        /**
        * Первое значение — строка.
        *
        * @return непустая строка
        */
        String first();
    
        /**
        * Второе значение — целое число.
        *
        * @return целое число
        */
        int second();
    }


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


# Задание 1
## Задача 6
### Текст задачи
@Cache.  
Разработайте аннотацию @Cache, со следующими характеристиками:

• Целью может быть ТИП

• Доступна во время исполнения программы

• Имеет необязательное свойство value, типа String[]

• Значение свойства по умолчанию: пустой массив

Проаннотируйте класс аннотацией @Cache, указав несколько кешируемых областей.
Создайте обработчик, который выводит список всех кешируемых областей или сообщение, что
список пуст.
### Алгоритм решения
    package tsygvintsev;
    
    import java.lang.annotation.ElementType;
    import java.lang.annotation.Retention;
    import java.lang.annotation.RetentionPolicy;
    import java.lang.annotation.Target;
    
    /**
    * Указывает кешируемые области для класса.
    * <p>
    * Применяется к типам. Содержит массив имён областей.
    * По умолчанию — пустой массив (кеширование не настроено).
    */
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Cache {
    
        /**
        * Имена кешируемых областей.
        *
        * @return массив имён; по умолчанию пустой
        */
        String[] value() default {};
    }


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


# Задание 2
## Задача 4
### Текст задачи
Написать тест, используя фреймворк JUnit, который проверяет корректность работы механизма
валидации классов, отмеченных аннотацией @Validate.

• Создать класс с аннотацией @Validate, указывающей массив типов для проверки.

• Использовать тест, который вызывает обработчик и проверяет, что список классов,

переданный в аннотации, корректно извлекается и при передаче пустого массива
выбрасывается исключение IllegalArgumentException.

• В тесте использовать аннотацию @Test с параметром expected (или assertThrows в JUnit 5).
### Алгоритм решения
    package tsygvintsev;
    
    import org.junit.jupiter.api.Test;
    import static org.junit.jupiter.api.Assertions.*;
    
    /**
    * Тесты для проверки корректности работы аннотации {@link Validate}
    * и обработчика в классе {@link ValidateJUnitTest}.
    */
    public class ValidateJUnitTest {
    
        /**
        * Проверяет, что метод корректно извлекает классы из аннотации @Validate.
        */
        @Test
        public void testGetValidationClasses_ReturnsCorrectArray() {
            Class<?>[] result = ValidateTask.getValidationClasses(ValidateTask.class);
    
            assertArrayEquals(
                new Class<?>[]{String.class, Integer.class, ValidateTask.class},
                result,
                "Массив классов не совпадает с ожидаемым"
            );
        }
    
        /**
        * Проверяет, что при пустом массиве в @Validate выбрасывается исключение.
        */
        @Test
        public void testGetValidationClasses_EmptyArray_ThrowsException() {
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ValidateTask.getValidationClasses(EmptyValidate.class),
                "Ожидалось исключение при пустом массиве"
            );
    
            assertTrue(
                exception.getMessage().contains("пуст"),
                "Сообщение об ошибке должно указывать на пустой массив"
            );
        }

        @Validate({})
        private static class EmptyValidate {}
    }


# Задание 2
## Задача 2
### Текст задачи
Создайте тест, используя фреймворк JUnit, который проверяет корректность вызова методов,
отмеченных аннотацией @Invoke.

• Использовать Reflection API для поиска методов с аннотацией.

• Убедиться, что метод действительно выполняется без исключений.

• Проверить, что возвращаемое значение или побочный эффект соответствует ожиданиям
(например, устанавливает флаг или изменяет состояние объекта).

• Тест должен использовать аннотацию @BeforeEach для подготовки тестируемого
экземпляра класса.
### Алгоритм решения
    package tsygvintsev;
    
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    import static org.junit.jupiter.api.Assertions.*;
    
    /**
    * Тесты для проверки корректности работы аннотации {@link Invoke}
    * и механизма автоматического вызова методов.
    */
    public class InvokeJUnitTest {
    
        private InvokeTask instance;
    
        /**
        * Создаёт новый экземпляр InvokeTask перед каждым тестом.
        */
        @BeforeEach
        public void setUp() {
            instance = new InvokeTask();
        }
    
        /**
        * Проверяет, что методы с @Invoke выполняются и изменяют состояние объекта.
        *
        * @throws Exception если Reflection API не сработал
        */
        @Test
        public void testInvokeMethods_ExecuteAndChangeState() throws Exception {
            assertFalse(instance.isInitialized(), "Объект не должен быть инициализирован изначально");
            assertEquals(0, instance.getValidationCount(), "Счётчик валидаций должен быть 0");
    
            InvokeTask.runInvokeMethods(instance);
    
            assertTrue(instance.isInitialized(), "Метод init() должен установить флаг initialized");
            assertEquals(1, instance.getValidationCount(), "Метод validate() должен увеличить счётчик на 1");
        }
    
        /**
        * Проверяем, что мы действительно используем Reflection API.
        */
        @Test
        public void testReflectionFindsInvokeMethods() {
            long count = java.util.Arrays.stream(InvokeTask.class.getDeclaredMethods())
            .filter(m -> m.isAnnotationPresent(Invoke.class))
            .count();
    
            assertEquals(2, count, "Должно быть ровно 2 метода с @Invoke");
        }
    }