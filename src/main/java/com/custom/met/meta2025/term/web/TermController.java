package com.custom.met.meta2025.term.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.custom.met.cmmn.model.CustomMap;
import com.custom.met.cmmn.paging.PagingCreator;
import com.custom.met.cmmn.security.utils.SecurityUtils;
import com.custom.met.cmmn.utils.StringUtils;
import com.custom.met.cmmn.web.CustomController;
import com.custom.met.meta2025.term.service.TermService;

import lombok.extern.log4j.Log4j2;

@Controller @Log4j2
public class TermController extends CustomController {

	@Autowired
	private TermService termService;
	
	/**
	 * <pre>
	 * 메소드명: mettm01
	 * 설명: 용어 등록페이지
	 * </pre>
	 * @param map
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/METTM01")
	public ModelAndView mettem01(@RequestParam Map<String, Object> map, ModelAndView modelAndView) {
		CustomMap params = new CustomMap(map);
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		
		modelAndView.setViewName("meta2025/term/termReg");
		return modelAndView;
	}
	
	/**
	 * <pre>
	 * 메소드명: mettm02
	 * 설명: 용어 등록요청
	 * </pre>
	 * @param params
	 * @return
	 */
	@PostMapping("/METTM02")
	@ResponseBody
	public Object mettm02(@RequestBody CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		
		params.put("sysCreator", StringUtils.NVL(SecurityUtils.getUsername(), "SYSTEM"));
		
		resultMap = termService.insertTermInfo(params);
		
		return getResponse(resultMap);
	}
	
	/**
	 * <pre>
	 * 메소드명: mettm03
	 * 설명: 용어 목록페이지
	 * </pre>
	 * @param map
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/METTM03")
	public ModelAndView mettm03(@RequestParam Map<String, Object> map, ModelAndView modelAndView) {
		CustomMap params = new CustomMap(map);
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		
		CustomMap termInfoListMap = termService.getTermInfoList(params);
		
		List<CustomMap> termInfoList = termInfoListMap.getCustomMapList("termInfoList");
		params.put("count", termInfoListMap.getString("count"));
		
		CustomMap pagingCreator = new PagingCreator(params).toCustomMap();
		
		modelAndView.addObject("pagingCreator", pagingCreator);
		modelAndView.addObject("termInfoList", termInfoList);
		modelAndView.addObject("params", params);
		
		modelAndView.setViewName("meta2025/term/termList");
		
		return modelAndView;
	}
	
	/**
	 * <pre>
	 * 메소드명: mettm04
	 * 설명: 용어 삭제요청
	 * </pre>
	 * @param params
	 * @return
	 */
	@PostMapping("/METTM04")
	@ResponseBody
	public Object mettm04(@RequestBody CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		
		
		resultMap = termService.deleteTermInfo(params);
		
		return getResponse(resultMap);
	}
	
	/**
	 * <pre>
	 * 메소드명: mettm05
	 * 설명: 용어 검색
	 * </pre>
	 * @param params
	 * @return
	 */
	@PostMapping("/METTM05")
	@ResponseBody
	public Object mettm05(@RequestBody CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		
		CustomMap termScInfoListMap = termService.getTermScInfoList(params);
		
		List<CustomMap> termScInfoList = termScInfoListMap.getCustomMapList("termScInfoList");
		resultMap.put("termScInfoList", termScInfoList);
		resultMap.put("count", termScInfoListMap.getString("count"));
		
		return getResponse(resultMap);
	}
}
