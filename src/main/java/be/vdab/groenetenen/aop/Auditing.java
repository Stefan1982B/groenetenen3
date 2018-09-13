package be.vdab.groenetenen.aop;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Auditing {
	private static final Logger LOGGER = LoggerFactory.getLogger(Auditing.class);

	@AfterReturning(pointcut = "execution(* be.vdab.groenetenen.services.*.*(..))", returning = "returnValue")
	void schrijfAudit(JoinPoint joinPoint, Object returnValue) {
		StringBuilder builder = new StringBuilder("Tijdstip\t").append(LocalDateTime.now());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			builder.append("\nGebruiker\t").append(authentication.getName());
		}
		builder.append("\nMethod\t\t").append(joinPoint.getSignature().toLongString());
		Arrays.stream(joinPoint.getArgs()).forEach(object -> builder.append("\nParameter\t").append(object));
		if (returnValue != null) {
			builder.append("\nReturn\t\t");
			if (returnValue instanceof Collection) {
				builder.append(((Collection<?>) returnValue).size()).append(" objects");
			} else {
				builder.append(returnValue.toString());
			}
		}
		LOGGER.info(builder.toString());
	}
}
