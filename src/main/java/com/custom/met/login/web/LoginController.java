package com.custom.met.login.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.custom.met.cmmn.model.CustomMap;
import com.custom.met.cmmn.web.CustomController;
import com.custom.met.login.service.LoginService;

@Controller
public class LoginController extends CustomController {

	@Autowired
	private LoginService loginService;
	
	/**
	 * <pre>
	 * 메소드명: metlg01
	 * 설명: 로그인 페이지
	 * </pre>
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/METLG01")
	public ModelAndView metlg01(ModelAndView modelAndView) {
		
		modelAndView.setViewName("/login/login");
		
		return modelAndView;
	}
	
	/**
	 * <pre>
	 * 메소드명: metlg02
	 * 설명: 회원가입페이지 페이지
	 * </pre>
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/METLG02")
	public ModelAndView metlg02(ModelAndView modelAndView) {
		
		modelAndView.setViewName("/login/register");
		
		return modelAndView;
	}
	
	/**
	 * <pre>
	 * 메소드명: metlg03
	 * 설명: 회원등록요청
	 * </pre>
	 * @param params
	 * @return
	 */
	@PostMapping("/METLG03")
	@ResponseBody
	public Object metlg03(@RequestBody CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		params.put("sysCreator", "SYSTEM");
		
		resultMap = loginService.insertMember(params);
		
		return getResponse(resultMap);
	}
}
