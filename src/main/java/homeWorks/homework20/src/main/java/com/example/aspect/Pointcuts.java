package com.example.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
    @Pointcut("execution(* com.example.service.ProductService.addProduct(..))")
    public void allAddMethods() {}

    @Pointcut("execution(* com.example.service.ProductService.get*(..))")
    public void allGetMethods() {}

    @Pointcut("execution(* com.example.service.ProductService.update*(..))")
    public void allUpdateMethods() {}

    @Pointcut("execution(* com.example.service.ProductService.delete*(..))")
    public void allDeleteMethods() {}
}