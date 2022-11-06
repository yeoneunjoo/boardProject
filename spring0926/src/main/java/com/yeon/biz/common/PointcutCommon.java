package com.yeon.biz.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointcutCommon {
	@Pointcut("execution(* com.yeon.biz..*Impl.*(..))")
	public void aPointcut() {}
	@Pointcut("execution(* com.yeon.biz..*Impl.select*(..))")
	public void bPointcut() {}
}
