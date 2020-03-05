package com.hx.rest.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hx.dao.FlatRightDao;
import com.hx.entity.TblRightInfo;
import com.hx.service.FlatRightService;
import com.hx.tools.WebResult;

@Controller
public class LoginController {
	
	
	@Autowired
	private FlatRightDao flatAdminDao;
	@Autowired
	private FlatRightService flatAdminService;
	
	
	//登录主页
	private static final String MAIN = "main";
	@RequestMapping("admin/main")  
    public String main(HttpServletRequest request){  
        return MAIN;  
    }
	@RequestMapping("admin/tomain")  
	public String tomain(HttpServletRequest request){  
		System.out.println(11);
		return "redirect:/main.jsp";    
	}
	// 页面顶层跳转(管理员)
	@RequestMapping(value = "/admin/skip")
	public String skipage() {
		return "skip";
	}
	//首页
	@RequestMapping(value = "/admin/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value="login/auth",method=RequestMethod.POST)
	public String doLogin(HttpServletRequest request,HttpServletResponse response,HttpSession session,
			@RequestParam(value="remenberme",defaultValue="")String remenberme,
			@RequestParam("account")String account,
			@RequestParam("password")String password) throws Exception{
		String msg=null;
		String content=null;
		UsernamePasswordToken token=new UsernamePasswordToken(account, password);
		if(!StringUtils.isEmpty(remenberme))
			token.setRememberMe(true);
		//得到subject 
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			System.out.println("登录成功");
			account=(String) session.getAttribute("account");
			request.getSession().setAttribute("ctx", request.getContextPath());
			System.out.println(request.getContextPath());
			List<TblRightInfo> plist = flatAdminService.getRightListByAccount((String) session.getAttribute("account"));
			session.setAttribute("plist", plist);
			
			
			//保存正常登陆日志 status(0.正常  1.异常) type(1.用户  2.管理员)
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			content="["+account+"]于"+sdf.format(new Date())+"登陆平台";//日志内容
			//baseService.save(WebUtil.saveOperLog("0","2",content));
			  return "redirect:/admin/main";
		} catch (IncorrectCredentialsException e) {  
            msg = "登录密码错误!";  
            session.setAttribute("msg", msg);
            System.out.println(msg);  
        } catch (ExcessiveAttemptsException e) {  
            msg = "登录失败次数过多";  
            session.setAttribute("msg", msg);
            System.out.println(msg);  
        } catch (LockedAccountException e) {  
            msg = "帐号已被锁定!";  
            session.setAttribute("msg", msg);
            System.out.println(msg);  
        } catch (DisabledAccountException e) {  
            msg = "帐号已被禁用!";  
            session.setAttribute("msg", msg);
            System.out.println(msg);  
        } catch (ExpiredCredentialsException e) {  
            msg = "帐号已过期!";  
            session.setAttribute("msg", msg);
            System.out.println(msg);  
        } catch (UnknownAccountException e) {  
            msg = "帐号不存在!";
            session.setAttribute("msg", msg);
            System.out.println(msg);  
        } catch (UnauthorizedException e) {  
            msg = "您没有得到相应的授权!";  
            session.setAttribute("msg", msg);
            System.out.println(msg);  
        } catch (Exception e) {
			e.printStackTrace();
			//保存异常登陆日志
			try {
				//baseService.save(WebUtil.saveOperLog("1","2",content));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} 
		return "redirect:/login.jsp";  
	}
	@RequestMapping(value = "login",method={RequestMethod.GET,RequestMethod.POST})
    public String login(HttpServletRequest request){  
        return "login";  
    }  
	@RequestMapping(value = "/admin/getLeftMenu",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<TblRightInfo> getLeftMenu(HttpServletRequest request) throws Exception{  
		List<TblRightInfo> plist = flatAdminService.getRightListByAccount("gzjojo"); 
		return plist;
	}  

}
