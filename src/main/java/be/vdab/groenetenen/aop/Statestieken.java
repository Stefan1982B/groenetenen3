package be.vdab.groenetenen.aop;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
class Statistieken {
	private static final Logger LOGGER = LoggerFactory.getLogger(Statistieken.class);
	private final ConcurrentHashMap<String, AtomicInteger> statistieken = new ConcurrentHashMap<>();

	@After("execution(* be.vdab.groenetenen.services.*.*(..))")
	void statistiekBijwerken(JoinPoint joinPoint) {
		String joinPointSignatuur = joinPoint.getSignature().toLongString();
		AtomicInteger vorigAantalOproepen = statistieken.putIfAbsent(joinPointSignatuur, new AtomicInteger(1));
		int aantalOproepen = vorigAantalOproepen == null ? 1 : vorigAantalOproepen.incrementAndGet();
		LOGGER.info("{} werd {} keer opgeroepen", joinPointSignatuur, aantalOproepen);
	}
}