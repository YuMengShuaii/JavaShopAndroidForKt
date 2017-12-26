package com.enation.javashop.android.lib.aspect;

import com.enation.javashop.android.lib.utils.ExtendMethodsKt;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @Coder  LDD
 * @Data   2017/12/26 下午12:37
 * @From   com.enation.javashop.android.lib.aspect
 * @Note   判断登录状态注解具体实现
 */
@Aspect
public class LoginHelperAspect {

    @Pointcut("execution(@com.enation.javashop.android.lib.aspect.LoginHelper * *(..))")//方法切入点
    public void methodAnnotated() {
    }

    @Around("methodAnnotated()")//在连接点进行方法替换
    public void aroundJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        ExtendMethodsKt.showMessage("未登录");
    }
}
