package com.company.project.core.join;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JoinField {



    /**
     * 用于bean关联的源字段，
     *
     * @return
     */
    String sourceField() default "id";

    /**
     * 用于关联其它表时的字段，一般默认为id
     *
     * @return
     */
    String customOuterField() default "id";

    /**
     * 用于排序
     *
     * @return
     */
    int order() default 1;


}
