package com.zz.police.modules.sys.controller;

import com.google.code.kaptcha.Constants;
import com.zz.police.common.annotation.SysLog;
import com.zz.police.common.support.properties.GlobalProperties;
import com.zz.police.common.utils.MD5Utils;
import com.zz.police.common.utils.ShiroUtils;
import com.zz.police.modules.sys.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * 用户controller
 * @author dengkp
 */
@Controller
public class SysLoginController extends AbstractController {

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private GlobalProperties globalProperties;

	/**
	 * 跳转登录页面
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String toLogin() {
		if (ShiroUtils.isLogin() || ShiroUtils.getUserEntity() != null) {
			return redirect("/");
		}
		return html("/login");
	}
	
	/**
	 * 登录
	 */
	@SysLog("登录")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model) {
		String username = getParam("username").trim();
		String password = getParam("password").trim();
		try {
			// 开启验证码
			if (globalProperties.isKaptchaEnable()) {
				String code = getParam("code").trim();
				if (StringUtils.isBlank(code)) {
					model.addAttribute("errorMsg", "验证码不能为空");
					return html("/login");
				}
				String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
				if (!code.equalsIgnoreCase(kaptcha)) {
					model.addAttribute("errorMsg", "验证码错误");
					return html("/login");
				}
			}
			// 用户名验证
			if (StringUtils.isBlank(username)) {
				model.addAttribute("errorMsg", "用户名不能为空");
				return html("/login");
			}
			// 密码验证
			if (StringUtils.isBlank(password)) {
				model.addAttribute("errorMsg", "密码不能为空");
				return html("/login");
			}
			UsernamePasswordToken token = new UsernamePasswordToken(username, MD5Utils.encrypt(username, password));
			ShiroUtils.getSubject().login(token);
			SecurityUtils.getSubject().getSession().setAttribute("sessionFlag", true);
			return redirect("/");
		} catch (UnknownAccountException | IncorrectCredentialsException | LockedAccountException e) {
			model.addAttribute("errorMsg", e.getMessage());
		} catch (AuthenticationException e) {
			model.addAttribute("errorMsg", "登录服务异常");
		}
		return html("/login");
	}

	/**
	 * 跳转后台控制台
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return html("/index");
	}
	
	/**
	 * 退出
	 */
	@SysLog("退出系统")
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		ShiroUtils.logout();
		return html("/login");
	}
	
}
