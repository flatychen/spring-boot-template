package com.company.project.core.join;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JoinField {



    /**
     * 用于
     *
     * @return
     */
    String sourcefield() default "id";

    /**
     * 用于
     *
     * @return
     */
    int order() default 1;


}
