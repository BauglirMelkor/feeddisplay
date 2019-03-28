package com.feed.save.aop;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import com.feed.save.exception.NoResultsReturned;

@Configuration
@Aspect
public class ExceptionLogger {

	@AfterThrowing(pointcut = "execution(* com.feed.save.*.*.*(..))", throwing = "ex")
	public void logNoResultsReturnedException(NoResultsReturned ex) {
		// we can use a proper logging framework and apply other business logic
		System.out.println("No Result Returned");
		ex.printStackTrace();
	}
	

}
