package banking.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import banking.data.Account;

@Aspect
@Component
public class LoggingAspect {

	private static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	
	
	@Before("execution(* banking.dao.AccountDaoImpl.*(..))")
	public void logBefore(JoinPoint jp){
		
		logger.info("Entering into "+jp.getSignature());
	}
	
	
	@After("execution(* banking.dao.AccountDaoImpl.*(..))")
	public void logAfter(JoinPoint jp) {
 
		logger.info("Escaping from "+jp.getSignature());
 
	}
	
	@AfterReturning(pointcut = "execution(* banking.dao.AccountDaoImpl.saveAccount(..))", returning= "result")
	public void logAfterReturning(JoinPoint jp, Object result) {
		
		logger.info(jp.getSignature()+", return value: "+ result);
	}
	
	
	
	@AfterThrowing(pointcut="execution(* banking.dao.AccountDaoImpl.saveAccount(..)", throwing="error")
	public void logAfterThrowing(JoinPoint jp, Throwable error){
		
		logger.info(jp.getSignature()+", Error: "+ error);
		
	}
}
