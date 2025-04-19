package com.example.aspect;

import com.example.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class ProductAspect {

    @Around("Pointcuts.allAddMethods()")
    public Object aroundAddingProductAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Product product = null;

        if (methodSignature.getName().equals("addProduct")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object arg : arguments) {
                if (arg instanceof Product) {
                    product = (Product) arg;
                    log.info("Попытка добавить продукт: {} (Цена: {})",
                            product.getName(), product.getPrice());
                }
            }
        }

        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.error("Ошибка при добавлении продукта: {}", e.getMessage());
            throw e;
        }

        if (product != null) {
            log.info("Продукт успешно добавлен: {}", product.getName());
        }
        return result;
    }

    @Around("Pointcuts.allGetMethods()")
    public Object aroundGettingProductAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String productName = null;
        Long productId = null;

        if (methodSignature.getName().equals("getAllProducts")) {
            log.info("Запрос на получение всех продуктов");
        }
        else if (methodSignature.getName().equals("getProductByName")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object arg : arguments) {
                if (arg instanceof String) {
                    productName = (String) arg;
                    log.info("Поиск продукта по имени: {}", productName);
                }
            }
        }
        else if (methodSignature.getName().equals("getProductById")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object arg : arguments) {
                if (arg instanceof Long) {
                    productId = (Long) arg;
                    log.info("Поиск продукта по ID: {}", productId);
                }
            }
        }

        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.error("Ошибка при получении продукта: {}", e.getMessage());
            throw e;
        }

        if (methodSignature.getName().equals("getAllProducts")) {
            log.info("Список всех продуктов успешно получен");
        }
        else if (methodSignature.getName().equals("getProductByName") && productName != null) {
            log.info("Результат поиска по имени '{}': {}", productName, result != null ? "найден" : "не найден");
        }
        else if (methodSignature.getName().equals("getProductById") && productId != null) {
            log.info("Результат поиска по ID {}: {}", productId, result != null ? "найден" : "не найден");
        }

        return result;
    }

    @Around("Pointcuts.allUpdateMethods()")
    public Object aroundUpdatingProductAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (args.length > 0 && args[0] instanceof Product) {
            Product product = (Product) args[0];
            log.info("Попытка обновить продукт: ID {}", product.getId());
        }

        Object result = joinPoint.proceed();
        log.info("Продукт успешно обновлен");
        return result;
    }

    @Around("Pointcuts.allDeleteMethods()")
    public Object aroundDeletingProductAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (args.length > 0 && args[0] instanceof Long) {
            Long productId = (Long) args[0];
            log.info("Попытка удалить продукт с ID: {}", productId);
        }

        Object result = joinPoint.proceed();
        log.info("Продукт успешно удален");
        return result;
    }
}