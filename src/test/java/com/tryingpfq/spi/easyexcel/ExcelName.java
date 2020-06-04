package com.tryingpfq.spi.easyexcel;

import java.lang.annotation.*;

/**
 * @author tryingpfq
 * @date 2020/4/17
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelName {

    String fileName() default "";

    String sheetName() default "";
}
