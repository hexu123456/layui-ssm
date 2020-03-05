package com.hx.aop;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;

import com.hx.entity.TblSysLogInfo;
import com.hx.tools.WebContextHolder;

public class LogAspectXml {
	/**
	 * 前置通知 JoinPoint接口表示目标类连接点对象 java.lang.Object[] getArgs()获取连接点方法运行时的入参列表
	 * Signature getSignature() ：获取连接点的方法签名对象 java.lang.Object getTarget()
	 * ：获取连接点所在的目标对象 java.lang.Object getThis() ：获取代理对象本身；
	 */

	public void doBefore(JoinPoint jp) {
		System.out.println("执行之前");
	}
	public void doAfter(JoinPoint jp) {}
	  
	public void doAfterReturn(JoinPoint jp) {
		System.out.println("返回之后");
		HttpServletRequest request= WebContextHolder.getRequest();
		//方法参数
		String strMethodName = jp.getSignature().getName();
		String strClassName = jp.getTarget().getClass().getName();
		Object[] params = jp.getArgs();

		StringBuffer bfParams = new StringBuffer();
		Enumeration<String> paraNames = null;
		if (params != null && params.length > 0) {
			paraNames = request.getParameterNames();
			String key;
			String value;
			while (paraNames.hasMoreElements()) {
				key = paraNames.nextElement();
				value = request.getParameter(key);
				if (!key.contains("password")) {
					bfParams.append(key).append("=").append(value).append("&");
				}
			}
			if (StringUtils.isEmpty(bfParams.toString())) {
				bfParams.append(request.getQueryString());
			}
		}

		//String strMessage = String.format("[类名]:%s,[方法]:%s,[参数]:%s", strClassName, strMethodName,bfParams.toString());
		//String strMessage = String.format("[操作人]:%s,[类名]:%s,[方法]:%s",getAccount(), strClassName, strMethodName);
		try {
			//保存数据库
			TblSysLogInfo  syslog = new TblSysLogInfo();
			//syslog.setContent(strMessage);
			syslog.setIpaddress(getIpAddr(request));
			syslog.setType("2");//系统
			syslog.setStatus("0");//是否正常(0.正常  1.异常)
			//baseDao.insertDate(syslog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void doAfterThrowing(JoinPoint jp) {
		HttpServletRequest request= WebContextHolder.getRequest();
		//方法参数
		String strMethodName = jp.getSignature().getName();
		String strClassName = jp.getTarget().getClass().getName();
		Object[] params = jp.getArgs();

		StringBuffer bfParams = new StringBuffer();
		Enumeration<String> paraNames = null;
		if (params != null && params.length > 0) {
			paraNames = request.getParameterNames();
			String key;
			String value;
			while (paraNames.hasMoreElements()) {
				key = paraNames.nextElement();
				value = request.getParameter(key);
				if (!key.contains("password")) {
					bfParams.append(key).append("=").append(value).append("&");
				}
			}
			if (StringUtils.isEmpty(bfParams.toString())) {
				bfParams.append(request.getQueryString());
			}
		}

		//String strMessage = String.format("[类名]:%s,[方法]:%s,[参数]:%s", strClassName, strMethodName,bfParams.toString());
		//String strMessage = String.format("[操作人]:%s,[类名]:%s,[方法]:%s",getAccount(), strClassName, strMethodName);
		try {
			//保存数据库
			TblSysLogInfo  syslog = new TblSysLogInfo();
			//syslog.setContent(strMessage);
			syslog.setIpaddress(getIpAddr(request));
			syslog.setType("2");//系统
			syslog.setStatus("1");//是否正常(0.正常  1.异常)
			//baseDao.insertDate(syslog);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	/*public static String getAccount(){
		HttpServletRequest request= WebContextHolder.getRequest();
		HttpSession session = request.getSession();
		String account=session.getAttribute("account").toString();
		return account;
	}*/
}
