package org.exam.www.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.exam.www.exception.AuthstatusException;
import org.exam.www.exception.IdPasswordNotMatchingException;
import org.exam.www.model.CommandAdminLogin;
import org.exam.www.model.CommandLogin;
import org.exam.www.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;

	// login
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String form(CommandLogin login, HttpServletRequest request,
			@CookieValue(value = "REMEMBER", required = false) Cookie rememberCookie) throws Exception {

		HttpSession session = request.getSession(false);
		if (session != null) {
			Object authInfo = session.getAttribute("authInfo");
			if (authInfo != null) {
				return "redirect:/main";
			}

		}

		if (rememberCookie != null) {
			login.setMem_id(rememberCookie.getValue());
			login.setRememberId(true);
		}

		return "loginForm";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String submit(@Validated CommandLogin login, Model model, HttpSession session, HttpServletResponse response,
			Errors errors) throws Exception {

		new LoginValidator().validate(login, errors);
		
		if (errors.hasErrors()) {
			return "/loginForm";
		}

		try {
			if (session != null) {
				Object authInfo = session.getAttribute("authInfo");
				if (authInfo != null) {
					return "redirect:/main";
				}

			}

			CommandLogin authInfo = loginService.authenticate(login.getMem_id(), login.getMem_pass(),
					login.getAuthstatus());

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
			return "redirect:/main";

		} catch (IdPasswordNotMatchingException e) {
			errors.rejectValue("mem_pass", "IdPasswordNotMatching");
			return "/error";
		} catch (AuthstatusException e) {

			return "errorAuthstatus";
		}

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate(); // 세션 제거
		return "/main";
	}

	// 관리자 로그인
	@RequestMapping(value = "/adminlogin", method = RequestMethod.GET)
	public String adminform(CommandAdminLogin adminloginCommand, HttpServletRequest request,
			@CookieValue(value = "REMEMBER", required = false) Cookie rememberCookie) throws Exception {

		// 세션 유지 중 로그인 창 요청시 메인 페이지로 이동
		HttpSession session = request.getSession(false);
		if (session != null) {
			Object adminauthInfo = session.getAttribute("adminauthInfo");
			if (adminauthInfo != null) {
				return "/main";
			}

		}

		if (rememberCookie != null) {
			adminloginCommand.setAdm_id(rememberCookie.getValue());
			adminloginCommand.setRememberAdmId(true);
		}

		return "/adminlogin";

	}

	@RequestMapping(value = "/adminlogin", method = RequestMethod.POST)
	public String adminsubmit(@Validated CommandAdminLogin adminloginCommand, HttpSession session,
			HttpServletResponse response, Errors errors) throws Exception {
		new AdminLoginValidator().validate(adminloginCommand, errors);
		
		if (errors.hasErrors()) {
			return "/adminlogin";
		}

		try {

			CommandAdminLogin adminauthInfo = loginService.adminauthenticate(

					adminloginCommand.getAdm_id(), adminloginCommand.getAdm_pass(),
					adminloginCommand.getAdm_authstatus());

			// 세션에 adminauthInfo저장
			session.setAttribute("adminauthInfo", adminauthInfo);
			session.setAttribute("adm_id", adminloginCommand.getAdm_id());
			Cookie rememberCookie = new Cookie("REMEMBER", adminloginCommand.getAdm_id());
			rememberCookie.setPath("/");
			if (adminloginCommand.isRememberAdmId()) {
				rememberCookie.setMaxAge(60 * 60 * 24 * 7);
			} else {
				rememberCookie.setMaxAge(0);
			}
			response.addCookie(rememberCookie);

			
			// 성공시 관리자 페이지로 이동
			return "administratorPage";

		} catch (IdPasswordNotMatchingException e) {
			errors.rejectValue("adm_pass", "IdPasswordNotMatching");
			System.out.println("실패");
			return "/error";
		} catch (AuthstatusException e) {

			return "/errorAuthstauts";
		}
	}
	
	
}
