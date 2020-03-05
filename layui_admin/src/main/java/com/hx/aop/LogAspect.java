package com.hx.aop;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
	
	/**
	 * 前置通知 JoinPoint接口表示目标类连接点对象 java.lang.Object[] getArgs()获取连接点方法运行时的入参列表
	 * Signature getSignature() ：获取连接点的方法签名对象 java.lang.Object getTarget()
	 * ：获取连接点所在的目标对象 java.lang.Object getThis() ：获取代理对象本身；
	 */

	public void doBefore(JoinPoint jp) {}
	public void doAfter(JoinPoint jp) {}

	@AfterReturning(value = "execution(* com.*.service.*.add*(..)) or execution(* com.*.service.*.del*(..)) or execution(* com.*.service.*.update*(..))")
	public void doAfterReturn(JoinPoint jp) {
		
	}

	@AfterThrowing (value = "execution(* com.*.service.*.add*(..)) or execution(* com.*.service.*.del*(..)) or execution(* com.*.service.*.update*(..))")
	public void doAfterThrowing(JoinPoint jp) {
		//System.out.println("-----doAfterThrowing-----");
	}

	// 获取客户端真实访问IP地址
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("PRoxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
