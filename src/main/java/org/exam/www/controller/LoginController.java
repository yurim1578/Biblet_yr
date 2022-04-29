package org.exam.www.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.exam.www.exception.IdPasswordNotMatchingException;
import org.exam.www.model.CommandAuthInfo;
import org.exam.www.model.CommandLogin;
import org.exam.www.model.MemberVO;
import org.exam.www.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	private LoginService loginService;

	@Autowired
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	// login
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String form(CommandLogin login,
			@CookieValue(value = "REMEMBER", required = false) Cookie rememberCookie) throws Exception {

		if (rememberCookie != null) {
			login.setMem_id(rememberCookie.getValue());
			login.setRememberId(true);
		}

		return "loginForm";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String submit(@Valid CommandLogin login, MemberVO member, Model model, HttpSession session,
			HttpServletResponse response, Errors errors) throws Exception {

		if (errors.hasErrors()) {
			return "/loginForm";
		}

		try {
			System.out.println(login.getMem_id());
			System.out.println(login.getMem_pass());
		
			CommandAuthInfo authInfo = loginService.authenticate(login.getMem_id(), login.getMem_pass());

			session.setAttribute("authInfo", authInfo);

			Cookie rememberCookie = new Cookie("REMEMBER", login.getMem_id());
			rememberCookie.setPath("/");
			if (login.isRememberId()) {
				rememberCookie.setMaxAge(60 * 60 * 24 * 7);
			} else {
				rememberCookie.setMaxAge(0);
			}
			response.addCookie(rememberCookie);

			// return "loginSuccess";
			// 로그인 성공 시 메인 페이지
			return "/main";

		} catch (IdPasswordNotMatchingException e) {
			errors.rejectValue("mem_pass","IdPasswordMatching");
			return "/loginForm";
		}

	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate(); //세션 제거
		return "/main";
	}
	
}
