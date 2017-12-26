package com.enation.javashop.android.lib.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Coder  LDD
 * @Data   2017/12/26 下午12:37
 * @From   com.enation.javashop.android.lib.aspect
 * @Note   判断登录状态注解
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD })
@Retention(RetentionPolicy.CLASS)
public @interface LoginHelper {
}
