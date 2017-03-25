package com.github.rysiekblah.hashmap.aspect;

import java.lang.annotation.*;

/**
 * Created by Tomasz_Kozlowski on 2/27/2017.
 */
@Inherited
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Binded {

}
