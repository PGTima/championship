package com.example.championship.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class WebServiceLogger {

  private static Logger LOG = LoggerFactory.getLogger(WebServiceLogger.class);

  /*@Pointcut(value = "execution(public * com.example.championship.service.impl.ChampionshipServiceImpl*(..))")
  public void serviceMethod() {
  }*/

  @Pointcut("@annotation(com.example.championship.annotation.Loggable)")
  public void loggableMethod() {
  }

 /* @Around(value = "serviceMethod() && loggableMethod()")
  public Object logWevServiceCall(ProceedingJoinPoint thisJoinPoint) throws Throwable {
    String methodName = thisJoinPoint.getSignature().getName();
    Object[] methodArgs = thisJoinPoint.getArgs();

    LOG.info("Call method " + methodName + " with args " + Arrays.toString(methodArgs));
    Object result = thisJoinPoint.proceed();
    LOG.info("Method " + methodName + " returns " + result);
    return result;
  }*/

  @Before(value = "loggableMethod()")
  public void loggerBeforeServiceCall(JoinPoint joinPoint){
    Object[] args = joinPoint.getArgs();
    String methodName = joinPoint.getSignature().getName();
    LOG.info("Before method " + methodName + " with args " + Arrays.toString(args));
  }

  @After(value = "loggableMethod()")
  public void loggerAfterServiceCall(JoinPoint joinPoint){
    String point = joinPoint.toString();
    String methodName = joinPoint.getSignature().getName();
    LOG.info("After Method " + methodName + " ->  " + point);
  }
}
