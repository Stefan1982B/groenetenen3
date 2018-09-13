package be.vdab.groenetenen.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
class Performance {
	private static final Logger LOGGER = LoggerFactory.getLogger(Performance.class);

	@Around("execution(* be.vdab.groenetenen.services.*.*(..))")
	Object schrijfPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
		long voor = System.nanoTime();
		try {
			return joinPoint.proceed();
		} finally {
			long duurtijd = System.nanoTime() - voor;
			LOGGER.info("{} duurde {} nanoseconden", joinPoint.getSignature().toLongString(), duurtijd);
		}
	}
}
