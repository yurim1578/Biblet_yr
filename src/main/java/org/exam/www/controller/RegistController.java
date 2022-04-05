package org.exam.www.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.exam.www.exception.AlreadyExistEmailException;
import org.exam.www.exception.AlreadyExistIdException;
import org.exam.www.model.MemberVO;
import org.exam.www.service.MailSendService;
import org.exam.www.service.RegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistController {
	
	private RegistService registService;
	private MailSendService mailSendService;

	@Autowired
	public RegistController(RegistService registService, MailSendService mailSendService) {
		this.registService=registService;
		this.mailSendService=mailSendService;
	}
	//ㄴ기본생성자생성 후 setter에 @Autowired줘도됨
	
	@RequestMapping(value="/registPage",method=RequestMethod.GET)
	public String memRegistPage(@ModelAttribute("member")MemberVO member,HttpSession session) {
		return "/registPage";
	}
	
	@RequestMapping(value="/registPage",method=RequestMethod.POST)
	public String memRegist(@ModelAttribute("member")MemberVO member,Errors errors) {
		System.out.println(member.toString());
		new AddValidator().validate(member, errors);
		if(errors.hasErrors()) {
			return "/registPage";
		}
		try {
			registService.memRegist(member);
			
			//임의의 authKey생성 및 이메일 발송
			String authkey=mailSendService.sendAuthMail(member.getMem_email());
			member.setAuthkey(authkey);
			
			HashMap<java.lang.String, java.lang.String> map=new HashMap<String,String>();
			map.put("mem_email", member.getMem_email());
			map.put("authkey", member.getAuthkey());
			
			///DB에 authkey 업데이트
			registService.updateKey(map);
			return "/member_check";
		}catch(AlreadyExistEmailException e) {
			errors.rejectValue("mem_email", "alreadyExistEmail");
			return "/registPage";
		}catch(AlreadyExistIdException e) {
			errors.rejectValue("mem_id", "alreadyExistId");
			return "/registPage";
		}
	}
	
	@RequestMapping("/registconfirm")
	public String confirm(@ModelAttribute("member")MemberVO member) {
		member.setAuthstatus(1);
		registService.updateStatus(member);
		return "/regist_confirm";
	}
	
	@RequestMapping(value="/home")
	public String goHome(HttpServletRequest request) throws Exception {
		return "/home";
	}
	
	
}
