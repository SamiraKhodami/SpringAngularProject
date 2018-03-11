package com.smr.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collections;

@Aspect
@Component
public class LogAspect {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    @Around("@annotation(com.smr.annotation.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        final long start = System.currentTimeMillis();

        final Object proceed = joinPoint.proceed();

        final long executionTime = System.currentTimeMillis() - start;

        LOGGER.debug(joinPoint.getSignature() + " EXECUTION TIME : " + executionTime + "ms");

        return proceed;
    }
	
	@Pointcut(value="execution(* com.smr.service..*(..))")
	public void anyServiceMethod() {
	}

	
	@Before("anyServiceMethod()")
	public void logBeforeMethod(JoinPoint joinPoint){
		Object[] args = joinPoint.getArgs();
		LOGGER.debug(" START OF "+joinPoint.getSignature().getName() + String.join("", Collections.nCopies(args.length, " {} ")), args);
	}	
	
	@AfterReturning(  
              pointcut = "anyServiceMethod()",  
              returning= "result")    
    public void logAfterReturningMethod(JoinPoint joinPoint,Object result){
		LOGGER.debug(" END OF "+joinPoint.getSignature().getName() + " {} ", result);
	}
	
	@AfterThrowing(  
              pointcut = "anyServiceMethod()",  
              throwing= "error")         
    public void logAfterThrowingException(JoinPoint joinPoint,Throwable error)  
    {  
		LOGGER.debug(joinPoint.getSignature() + " EXCEPTION : " + error );
    } 


}