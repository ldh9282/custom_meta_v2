package com.custom.met.sample.web;

import java.util.Enumeration;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.custom.met.cmmn.constant.CustomAuthCode;
import com.custom.met.cmmn.exception.CustomException;
import com.custom.met.cmmn.exception.CustomExceptionCode;
import com.custom.met.cmmn.model.CustomMap;
import com.custom.met.cmmn.security.utils.SecurityUtils;
import com.custom.met.cmmn.utils.MapUtils;
import com.custom.met.cmmn.web.CustomController;
import com.custom.met.sample.model.SampleUser;
import com.custom.met.sample.service.SampleService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

/**
 * <pre>
 * 클래스명: SampleController
 * 설명: 샘플컨트롤러
 * </pre>
 */
@Controller @Log4j2
public class SampleController extends CustomController {
	
	@Autowired
	private SampleService sampleService;

	/**
	 * <pre>
	 * 메소드명: index
	 * 설명: index 페이지
	 * </pre>
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/")
	public ModelAndView index(ModelAndView modelAndView) {
		
		modelAndView.setViewName("index");
		
		return modelAndView;
	}
	
	/**
	 * <pre>
	 * 메소드명: metsp01
	 * 설명: sample01 페이지
	 * </pre>
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/METSP01")
	public ModelAndView metsp01(@RequestParam Map<String, Object> map, ModelAndView modelAndView) {
		
		CustomMap params = new CustomMap(map);
		
		MapUtils.paramsValidation(params, new String[][] {
			{ "requiredSno", "required" }
			, { "requiredSno", "number" }
		});
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		
		modelAndView.setViewName("sample/sample01");
		
		return modelAndView;
	}
	
	/**
	 * <pre>
	 * 메소드명: metsp02
	 * 설명: 샘플요청 DTO
	 * </pre>
	 * @param sampleUser
	 * @return
	 */
	@PostMapping("/METSP02")
	@ResponseBody
	public Object metsp02(@RequestBody SampleUser sampleUser) {
		CustomMap resultMap = new CustomMap();
		if (log.isDebugEnabled()) { log.debug("sampleUser ::: " + sampleUser); }
		
		resultMap.put("sampleUser", sampleUser);
		
		return getResponse(resultMap);
	}
	
	/**
	 * <pre>
	 * 메소드명: metsp03
	 * 설명: 샘플요청 CustomMap
	 * </pre>
	 * @param params
	 * @return
	 */
	@PostMapping("/METSP03")
	@ResponseBody
	public Object metsp03(@RequestBody CustomMap params) {
		CustomMap resultMap = new CustomMap();
		MapUtils.paramsValidation(params, new String[][] {
			{ "sampleUserAge", "number" }
		});
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		
		resultMap.put("params", params);
		return getResponse(resultMap);
	}
	
	/**
	 * <pre>
	 * 메소드명: metsp04
	 * 설명: 샘플유저정보 등록요청
	 * </pre>
	 * @param params
	 * @return
	 */
	@PostMapping("/METSP04")
	@ResponseBody
	public Object metsp04(@RequestBody CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		
		params.put("sysCreator", "SYSTEM");
		
		sampleService.insertSampleUserInfo(params);
		
		
		return getResponse(resultMap);
	}
	
	/**
	 * <pre>
	 * 메소드명: metsp05
	 * 설명: 샘플유저기본정보 조회요청
	 * </pre>
	 * @param params
	 * @return
	 */
	@PostMapping("/METSP05")
	@ResponseBody
	public Object metsp05(@RequestBody CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		
		resultMap = sampleService.getSampleUser(params);
		
		return getResponse(resultMap);
	}
	
	/**
	 * <pre>
	 * 메소드명: metsp06
	 * 설명: 샘플유저상세정보 조회요청
	 * </pre>
	 * @param params
	 * @return
	 */
	@PostMapping("/METSP06")
	@ResponseBody
	public Object metsp06(@RequestBody CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		
		resultMap = sampleService.getSampleUserDtl(params);
		
		return getResponse(resultMap);
	}
	
	/**
	 * <pre>
	 * 메소드명: metsp07
	 * 설명: 샘플유저정보목록 조회요청
	 * </pre>
	 * @param params
	 * @return
	 */
	@PostMapping("/METSP07")
	@ResponseBody
	public Object metsp07(@RequestBody CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		
		resultMap = sampleService.getSampleUserInfoList(params);
		
		return getResponse(resultMap);
	}
	
	/**
	 * <pre>
	 * 메소드명: metsp08
	 * 설명: 샘플유저상세정보 등록요청
	 * </pre>
	 * @param params
	 * @return
	 */
	@PostMapping("/METSP08")
	@ResponseBody
	public Object metsp08(@RequestBody CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		
		params.put("sysCreator", "SYSTEM");
		
		resultMap = sampleService.insertSampleUserDtl(params);
		
		return getResponse(resultMap);
	}
	
	/**
	 * <pre>
	 * 메소드명: metsp09
	 * 설명: 샘플유저상세정보 수정요청
	 * </pre>
	 * @param params
	 * @return
	 */
	@PostMapping("/METSP09")
	@ResponseBody
	public Object metsp09(@RequestBody CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		
		params.put("sysModifier", "SYSTEM");
		
		resultMap = sampleService.updateSampleUserDtl(params);
		
		return getResponse(resultMap);
	}
	
	/**
	 * <pre>
	 * 메소드명: metsp10
	 * 설명: 샘플유저상세정보 삭제요청
	 * </pre>
	 * @param params
	 * @return
	 */
	@PostMapping("/METSP10")
	@ResponseBody
	public Object metsp10(@RequestBody CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		
		resultMap = sampleService.deleteSampleUserDtl(params);
		
		return getResponse(resultMap);
	}
	
	/**
	 * <pre>
	 * 메소드명: metsp11
	 * 설명: 샘플유저상세정보 수정요청
	 * </pre>
	 * @param params
	 * @return
	 */
	@PostMapping("/METSP11")
	@ResponseBody
	public Object metsp11(@RequestBody CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		
		params.put("sysActor", "SYSTEM");
		
		resultMap = sampleService.mergeSampleUserDtl(params);
		
		return getResponse(resultMap);
	}
	
	/**
	 * <pre>
	 * 메소드명: metsp12
	 * 설명: 샘플유저정보 등록요청 (수동롤백테스트)
	 * </pre>
	 * @param params
	 * @return
	 */
	@PostMapping("/METSP12")
	@ResponseBody
	public Object metsp12(@RequestBody CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		
		params.put("sysCreator", "SYSTEM");
		
		resultMap = sampleService.insertSampleUserInfo2(params);
		
		return getResponse(resultMap);
	}
	
	/**
	 * <pre>
	 * 메소드명: metsp13
	 * 설명: sample02 페이지
	 * </pre>
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/METSP13")
	public ModelAndView metsp13(@RequestParam Map<String, Object> map, ModelAndView modelAndView) {
		
		CustomMap params = new CustomMap(map);
		
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		
		modelAndView.setViewName("sample/sample02");
		
		return modelAndView;
	}
	
	/**
	 * <pre>
	 * 메소드명: metsp14
	 * 설명: sample03 페이지 (권한테스트 AOP)
	 * </pre>
	 * @param modelAndView
	 * @return
	 */
	@PreAuthorize("hasAuthority('A001')")
	@RequestMapping("/METSP14")
	public ModelAndView metsp14(@RequestParam Map<String, Object> map, ModelAndView modelAndView) {
		
		CustomMap params = new CustomMap(map);
		
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		
		modelAndView.setViewName("sample/sample03");
		
		return modelAndView;
	}
	
	/**
	 * <pre>
	 * 메소드명: metsp15
	 * 설명: sample03 페이지 (권한테스트 AOP 안하고 하는법)
	 * </pre>
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/METSP15")
	public ModelAndView metsp15(@RequestParam Map<String, Object> map, ModelAndView modelAndView) {
		
		CustomMap params = new CustomMap(map);
		
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		
		if (!SecurityUtils.hasAuthorized(CustomAuthCode.A001)) {
			throw new CustomException(CustomExceptionCode.ERR403, new String[] { CustomAuthCode.A001.getCode(), CustomAuthCode.A001.getCodeName() });
		}
		
		modelAndView.setViewName("sample/sample03");
		
		return modelAndView;
	}
	
}
