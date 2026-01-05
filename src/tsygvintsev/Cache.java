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