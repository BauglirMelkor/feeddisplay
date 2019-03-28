package com.feed.save.aop;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DuplicateKeyException;

import com.feed.save.exception.NoResultsReturned;

@Configuration
@Aspect
public class ExceptionLogger {
	
	@AfterThrowing(pointcut = "execution(* com.feed.save.*.*.*(..))", throwing = "ex")
	public void logNoResultsReturnedException(NoResultsReturned ex) {
		System.out.println("No Result Returned");
		ex.printStackTrace();
	}
	
	@AfterThrowing(pointcut = "execution(* com.feed.save.*.*.*(..))", throwing = "ex")
	public void logDuplicateKeyException(DuplicateKeyException ex) {
		
		System.out.println("Duplicate Key Exception");
		
	}
	

}
