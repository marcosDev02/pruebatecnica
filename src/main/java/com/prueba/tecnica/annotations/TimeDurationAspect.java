package com.prueba.tecnica.annotations;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class TimeDurationAspect {
	
	@Around("@annotation(com.prueba.tecnica.annotations.TimeDuration)")
	 public void time(ProceedingJoinPoint joinPoint) throws Throwable {

	        long iniciar = System.nanoTime();
	        joinPoint.proceed();
	        long finalizar = System.nanoTime();
	        long tiempoEjecucion = ((finalizar - iniciar)/1000000);
	        double tiempoEjecucion2 = (double)tiempoEjecucion;
	        log.trace("Tardó en ejecutarse : " + tiempoEjecucion2 + "ns ,  el metodo " + joinPoint.getSignature().getName());
	    }
}
