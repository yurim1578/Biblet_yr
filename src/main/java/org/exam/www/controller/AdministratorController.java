package org.exam.www.controller;

import java.util.HashMap;
import java.util.List;

import org.exam.www.model.AdministratorVO;
import org.exam.www.model.CommandListAppr;
import org.exam.www.model.MemberVO;
import org.exam.www.service.AdmPageService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class AdministratorController {

	private static final Object Object = null;
	private static final String String = null;
	private AdmPageService admPageService;

	@Autowired
	public AdministratorController(AdmPageService admPageService) {
		this.admPageService = admPageService;
	}

	// 합칠때 로그인시 mapping되도록 ->POST로 변경
	@RequestMapping(value="/adminPage",method=RequestMethod.POST)
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
	public String searchInfo(@ModelAttribute("member") MemberVO member, Model model) throws Exception {

		if ("".equals(member.getOption()) || member.getOption() == null) {
			member.setOption("mem_num");
			member.setKeyword(null);
		}
		// System.out.println(member.toString());
		// System.out.println(admin.toString());
//		if ("".equals(admin.getAdmOption()) || admin.getAdmOption() == null) {
//			admin.setAdmOption("adm_num");
//			admin.setAdmKeyword(null);
//		}
		// System.out.println(admin.toString());
//		if("".equals(appr.getCoOption())||appr.getCoOption()==null){
//			appr.setCoOption("mem_id");
//			appr.setCoKeyword(null);
//		}
		
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
		
//		//코멘트 검색
//		List<CommandListAppr> searchApprList=admPageService.searchComments(appr);
//		model.addAttribute("searchApprList", searchApprList);
//		
//		int commentcount=admPageService.countComment();
//		model.addAttribute("commentcount", commentcount);
//		
		//관리자 검색
//		List<AdministratorVO> searchAdmList = admPageService.searchAdmin(admin);
//		model.addAttribute("searchAdmList", searchAdmList);
//
//		int admcount = admPageService.countAdmin();
//		model.addAttribute("admcount", admcount);

		// return "administratorPage";
		return "administratorPage";
	}

	@ResponseBody
	@RequestMapping(value = "/commentPage", produces = "application/json; charset=UTF-8", method= RequestMethod.POST)
	public String searchAdmInfo(@RequestBody CommandListAppr appr) {
		//System.out.println("0:"+appr.getCoKeyword());
		if("".equals(appr.getCoOption())||appr.getCoOption()==null) {
			//System.out.println("1:"+appr.getCoKeyword());
			appr.setCoOption("mem_id");
			appr.setCoKeyword(null);
			List<CommandListAppr> searchApprList=admPageService.searchComments(appr);
			
			JSONObject jo=new JSONObject();
			
			JSONArray ja=new JSONArray();
			for (int i=0;i<searchApprList.size();i++) {
				JSONObject jso=new JSONObject();
				jso.put("bookcomment",searchApprList.get(i).getBook_comment());
				jso.put("isbn", searchApprList.get(i).getIsbn());
				jso.put("mem_id", searchApprList.get(i).getMem_id());
				jso.put("appraisal_num",searchApprList.get(i).getAppraisal_num());
				ja.put(jso);
			}
			jo.put("item",ja);
			return jo.toString();
		}else {
		List<CommandListAppr> searchApprList=admPageService.searchComments(appr);
		//System.out.println("2:"+appr.getCoKeyword());
		JSONObject jo=new JSONObject();
		
		JSONArray ja=new JSONArray();
		for (int i=0;i<searchApprList.size();i++) {
			JSONObject jso=new JSONObject();
			jso.put("bookcomment",searchApprList.get(i).getBook_comment());
			jso.put("isbn", searchApprList.get(i).getIsbn());
			jso.put("mem_id", searchApprList.get(i).getMem_id());
			jso.put("appraisal_num",searchApprList.get(i).getAppraisal_num());
			ja.put(jso);
		}
		jo.put("item",ja);
		

		return jo.toString();
//		int commentcount=admPageService.countComment();
//		model.addAttribute("commentcount", commentcount);
//		
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/admInfoPage", produces = "application/json; charset=UTF-8", method= RequestMethod.POST)
	public String searchComment(@RequestBody AdministratorVO admin) {
		//System.out.println("0:"+admin.getAdmKeyword());
		if ("".equals(admin.getAdmOption()) || admin.getAdmOption() == null) {
			admin.setAdmOption("adm_num");
			admin.setAdmKeyword(null);
		
			List<AdministratorVO> searchAdmList = admPageService.searchAdmin(admin);

			JSONObject jo=new JSONObject();
			
			JSONArray ja=new JSONArray();
			for (int i=0;i<searchAdmList.size();i++) {
				JSONObject jso=new JSONObject();
				jso.put("adm_num",searchAdmList.get(i).getAdm_num());
				jso.put("adm_name", searchAdmList.get(i).getAdm_name());
				jso.put("adm_id", searchAdmList.get(i).getAdm_id());
				jso.put("adm_regdate",searchAdmList.get(i).getAdm_regdate());
				jso.put("adm_email",searchAdmList.get(i).getAdm_email());
				jso.put("adm_authstatus",searchAdmList.get(i).getAdm_authstatus());
				ja.put(jso);
			}
			jo.put("item",ja);
			return jo.toString();
		}else {
			List<AdministratorVO> searchAdmList = admPageService.searchAdmin(admin);

			JSONObject jo=new JSONObject();
			
			JSONArray ja=new JSONArray();
			for (int i=0;i<searchAdmList.size();i++) {
				JSONObject jso=new JSONObject();
				jso.put("adm_num",searchAdmList.get(i).getAdm_num());
				jso.put("adm_name", searchAdmList.get(i).getAdm_name());
				jso.put("adm_id", searchAdmList.get(i).getAdm_id());
				jso.put("adm_regdate",searchAdmList.get(i).getAdm_regdate());
				jso.put("adm_email",searchAdmList.get(i).getAdm_email());
				jso.put("adm_authstatus",searchAdmList.get(i).getAdm_authstatus());
				ja.put(jso);
			}
			jo.put("item",ja);
			return jo.toString();
//		int commentcount=admPageService.countComment();
//		model.addAttribute("commentcount", commentcount);
//		
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
