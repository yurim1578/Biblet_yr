package org.exam.www.controller;

import java.util.HashMap;
import java.util.List;

import org.exam.www.model.AdministratorVO;
import org.exam.www.model.CommandListAppr;
import org.exam.www.model.MemberVO;
import org.exam.www.service.AdmPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdministratorController {

	private AdmPageService admPageService;

	@Autowired
	public AdministratorController(AdmPageService admPageService) {
		this.admPageService = admPageService;
	}

	// 합칠때 로그인시 mapping되도록 ->POST로 변경
	@RequestMapping("/adminPage")
	public String adminPage(Model model) {
		// 회원정보탭
		List<MemberVO> memberList = admPageService.listOfMember();
		// System.out.println(memberList.toString());
		model.addAttribute("memberList", memberList);
		int memcount = admPageService.countMember();
		model.addAttribute("memcount", memcount);

		//평가탭
		List<CommandListAppr> apprList = admPageService.listOfAppraisal();
		model.addAttribute("apprList", apprList);
		
		int starcount = admPageService.countStar();
		model.addAttribute("starcount", starcount);

		//코멘트탭
		List<CommandListAppr> commentList = admPageService.listOfAppraisal();
		model.addAttribute("commentList", commentList);
		
		int commentcount = admPageService.countComment();
		model.addAttribute("commentcount", commentcount);
		
		// 관리자정보탭
		List<AdministratorVO> adminList = admPageService.listOfAdmin();
		model.addAttribute("adminList", adminList);
		int admcount = admPageService.countAdmin();
		model.addAttribute("admcount", admcount);

		// return "/administratorPage";
		return "/administratorPage";
	}

//	@RequestMapping(value = "/adminPageAdm", method = RequestMethod.POST)
//	public String adminPageAdm(Model model) {
//		// 관리자정보탭
//		List<AdministratorVO> adminList = admPageService.listOfAdmin();
//		model.addAttribute("adminList", adminList);
//		int admcount = admPageService.countAdmin();
//		model.addAttribute("admcount", admcount);
//
//		// return "/administratorPage";
//		return "/administratorPage";
//	}

	//@ResponseBody 
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchInfo(@ModelAttribute("member") MemberVO member, @ModelAttribute("admin") AdministratorVO admin,
			@ModelAttribute("appr") CommandListAppr appr,Model model) throws Exception {

		if ("".equals(member.getOption()) || member.getOption() == null) {
			member.setOption("mem_num");
			member.setKeyword(null);
		}
		// System.out.println(member.toString());
		// System.out.println(admin.toString());
		if ("".equals(admin.getAdmOption()) || admin.getAdmOption() == null) {
			admin.setAdmOption("adm_num");
			admin.setAdmKeyword(null);
		}
		// System.out.println(admin.toString());
		if("".equals(appr.getCoOption())||appr.getCoOption()==null){
			appr.setCoOption("mem_id");
			appr.setCoKeyword(null);
		}
		
		//회원검색
		List<MemberVO> searchList = admPageService.searchMember(member);
		model.addAttribute("searchList", searchList);

		int memcount = admPageService.countMember();
		model.addAttribute("memcount", memcount);
		
		//평가 정보 전달
		List<CommandListAppr> apprList = admPageService.listOfAppraisal();
		model.addAttribute("apprList", apprList);
		
		int starcount = admPageService.countStar();
		model.addAttribute("starcount", starcount);
		
		//코멘트 검색
		List<CommandListAppr> searchApprList=admPageService.searchComments(appr);
		model.addAttribute("searchApprList", searchApprList);
		
		int commentcount=admPageService.countComment();
		model.addAttribute("commentcount", commentcount);
		
		//관리자 검색
		List<AdministratorVO> searchAdmList = admPageService.searchAdmin(admin);
		model.addAttribute("searchAdmList", searchAdmList);

		int admcount = admPageService.countAdmin();
		model.addAttribute("admcount", admcount);

		// return "administratorPage";
		return "administratorPage";
	}

	@ResponseBody
	@PostMapping("/commentPage")
	public int searchComment(@RequestBody@ModelAttribute("appr") CommandListAppr appr,Model model) {
		if("".equals(appr.getCoOption())||appr.getCoOption()==null){
			appr.setCoOption("mem_id");
			appr.setCoKeyword(null);
			
			return 0;
		}else {
		List<CommandListAppr> searchApprList=admPageService.searchComments(appr);
		model.addAttribute("searchApprList", searchApprList);
		
		int commentcount=admPageService.countComment();
		model.addAttribute("commentcount", commentcount);
		return 1;
		}
	}
//	@RequestMapping(value = "/searchAdm", method = RequestMethod.POST)
//	public String searchAdminInfo(@ModelAttribute("admin") AdministratorVO admin, Model model1) {
//		List<AdministratorVO> searchAdmList = admPageService.searchAdmin(admin);
//		model1.addAttribute("searchAdmList", searchAdmList);
//
//		int admcount = admPageService.countAdmin();
//		model1.addAttribute("admcount", admcount);
//
//		//return "administratorPage";
//		return "administratorPage";
//	}

	@RequestMapping(value = "/adminPage/deleteMember/{mem_num}", method = RequestMethod.GET)
	public String delete(@PathVariable int mem_num, Model model) {
		model.addAttribute("mem_num", mem_num);
		return "/deleteMember";
	}
	
	@RequestMapping(value="/adminPage/deleteComment/{appraisal_num}",method=RequestMethod.GET)
	public String deleteComment(@PathVariable int appraisal_num,Model model) {
		model.addAttribute("appraisal_num",appraisal_num);
		return "/deleteComment";
	}

	@RequestMapping(value = "/adminpage/deleteMember", method = RequestMethod.POST)
	public String delete(int mem_num, String adm_pass, String adm_email, Model model) {
		int rowCount;
		MemberVO member = new MemberVO();
		member.setMem_num(mem_num);
		AdministratorVO admin = new AdministratorVO();
		admin.setAdm_email(adm_email);
		admin.setAdm_pass(adm_pass);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("MEM_NUM", member.getMem_num());// sqlmapper에 이름과 map의 key값이 같아야함
		map.put("ADM_EMAIL", admin.getAdm_email());
		map.put("ADM_PASS", admin.getAdm_pass());

		// System.out.println(map);
		rowCount = admPageService.delete(map);

		if (rowCount == 0) {
			model.addAttribute("mem_num", mem_num);
			model.addAttribute("msg", "관리자 비밀번호가 일치하지 않습니다.");
			return "/deleteMember";
		} else {
			return "redirect:/adminPage";
		}
	}

	
	@RequestMapping(value = "/adminpage/deleteComment", method = RequestMethod.POST)
	public String deleteComment(int appraisal_num, String adm_pass, String adm_email, Model model) {
		int rowCount;
		CommandListAppr appr= new CommandListAppr();
		appr.setAppraisal_num(appraisal_num);
		AdministratorVO admin = new AdministratorVO();
		admin.setAdm_email(adm_email);
		admin.setAdm_pass(adm_pass);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("APPRAISAL_NUM", appr.getAppraisal_num());// sqlmapper에 이름과 map의 key값이 같아야함
		map.put("ADM_EMAIL", admin.getAdm_email());
		map.put("ADM_PASS", admin.getAdm_pass());

		// System.out.println(map);
		rowCount = admPageService.deleteComment(map);

		if (rowCount == 0) {
			model.addAttribute("appraisal_num", appraisal_num);
			model.addAttribute("msg", "관리자 비밀번호가 일치하지 않습니다.");
			return "/deleteComment";
		} else {
			return "redirect:/adminPage";
		}
	}
//	@RequestMapping(value = "/adminPageStar", method = RequestMethod.POST)
//	public String listOfStar(Model model) {
//		List<CommandListAppr> apprList = admPageService.listOfAppraisal();
//		model.addAttribute("apprList", apprList);
//		
//		int starcount = admPageService.countStar();
//		model.addAttribute("starcount", starcount);
//
//		return "/administratorPage";
//	}

//	@RequestMapping(value = "/adminPageComments", method = RequestMethod.POST)
//	public String listOfComments(Model model) {
//		List<CommandListAppr> commentList = admPageService.listOfAppraisal();
//		model.addAttribute("commentList", commentList);
//		
//		int commentcount = admPageService.countComment();
//		model.addAttribute("commentcount", commentcount);
//
//		return "/administratorPage";
//	}
}
