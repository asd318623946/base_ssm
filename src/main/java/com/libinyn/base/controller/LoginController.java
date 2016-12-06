package com.libinyn.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.libinyn.base.been.ResponseVO;

/**
 * 登录首页
 * @author hp
 *
 */
@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	/**
	 * 主页
	 * @return
	 */
	@RequestMapping("/")
	public String index(){
		
		return "redirect:/index";
	} 
	
	/**
	 * @return 跳转主页面或登录页面
	 */
	@RequestMapping(value = "/index", method = {RequestMethod.GET})
	public String toHome(HttpServletRequest req, Model model) {
		
		if (SecurityUtils.getSubject().isAuthenticated() || SecurityUtils.getSubject().isRemembered()) {
			//已经登录了跳转
			return "redirect:/home";
		} else {
			//没有登录跳转到登录页面
			return "login";
		}
	}
	
	/**
	 * @return 主页面 展示
	 */
	@RequestMapping(value = "/home", method = {RequestMethod.GET})
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("/home");
		//需要在主页面展示的数据
		modelAndView.addObject("user", "libin"); 
		modelAndView.addObject("name","nn");
		return modelAndView;
	}
	

	/**
	 * 登录
	 *
	 * @param userCode 用户名
	 * @param password 密码
	 * @return MessageModel mm
	 */
	@RequestMapping(value = "/login")
	@ResponseBody
	public ResponseVO login(String userCode, String password, HttpServletRequest request) {
		ResponseVO response = new ResponseVO(true);
		String error = null;
		/*if (StringUtils.isBlank(userCode)) {
			error = "用户名不能为空";
		}
		if (StringUtils.isBlank(password)) {
			error = "密码不能为空";
		}*/
		try {
			if (error == null) {
				Subject subject = SecurityUtils.getSubject();
				UsernamePasswordToken token = new UsernamePasswordToken(userCode, password);
				subject.login(token);
//				HttpSession session = request.getSession();
//				session.setAttribute(CrsConstants.CURRENT_USER_CODE, userCode);
//				ShiroUser shiroUser = getCurrentUser();
//				session.setAttribute(CrsConstants.CURR_USER_STATUS, shiroUser.getCurrStatus());
//				session.setAttribute(CrsConstants.CURR_USER_ROLE, shiroUser.getCurrRoleCode());
			}
		} catch (IncorrectCredentialsException e) {
			logger.error(e.getMessage(), e);
			error = e.getMessage();
			response.setSuccess(false);
		} catch (Exception e) {
			logger.error("登录异常", e);
			error = "登录异常，请联系管理员！";
			response.setSuccess(false);
		}
		response.setErroMsg(error);;
		return response;
	}
}
