package banking.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class PerformanceAspect {
	
	private static Logger logger = LoggerFactory.getLogger(PerformanceAspect.class);
	
	@Around("execution(* banking.service.AccountServiceImpl.*(..))")
    public Object logTime(ProceedingJoinPoint pjp) throws Throwable {
		
		long start = System.currentTimeMillis();
		
        Object retVal = pjp.proceed();
        
        long end = System.currentTimeMillis();
        
		logger.info("Time to run method " + pjp.getSignature() + ": " + (end - start) + " ms.");
        
        return retVal;
	}

}
