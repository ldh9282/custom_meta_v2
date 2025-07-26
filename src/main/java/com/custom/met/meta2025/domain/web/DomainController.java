package com.custom.met.meta2025.domain.web;

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

import com.custom.met.cmmn.constant.CustomAuthCode;
import com.custom.met.cmmn.exception.CustomException;
import com.custom.met.cmmn.exception.CustomExceptionCode;
import com.custom.met.cmmn.model.CustomMap;
import com.custom.met.cmmn.paging.PagingCreator;
import com.custom.met.cmmn.security.utils.SecurityUtils;
import com.custom.met.cmmn.utils.StringUtils;
import com.custom.met.cmmn.web.CustomController;
import com.custom.met.meta2025.domain.service.DomainService;

import lombok.extern.log4j.Log4j2;

@Controller @Log4j2
public class DomainController extends CustomController {

	@Autowired
	private DomainService domainService;
	
	/**
	 * <pre>
	 * 메소드명: metdm01
	 * 설명: 도메인 등록페이지
	 * </pre>
	 * @param map
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/METDM01")
	public ModelAndView metdm01(@RequestParam Map<String, Object> map, ModelAndView modelAndView) {
		CustomMap params = new CustomMap(map);
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		
		modelAndView.setViewName("meta2025/domain/domainReg");
		
		return modelAndView;
	}
	
	/**
	 * <pre>
	 * 메소드명: metdm02
	 * 설명: 도메인 등록요청
	 * </pre>
	 * @param params
	 * @return
	 */
	@PostMapping("/METDM02")
	@ResponseBody
	public Object metdm02(@RequestBody CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		
		params.put("sysCreator", StringUtils.NVL(SecurityUtils.getUsername(), "SYSTEM"));
		
		resultMap = domainService.insertDomainInfo(params);
		
		return getResponse(resultMap);
	}
	
	/**
	 * <pre>
	 * 메소드명: metdm03
	 * 설명: 도메인 목록페이지
	 * </pre>
	 * @param map
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/METDM03")
	public ModelAndView metdm03(@RequestParam Map<String, Object> map, ModelAndView modelAndView) {
		CustomMap params = new CustomMap(map);
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		
		CustomMap domainInfoListMap = domainService.getDomainInfoList(params);
		
		List<CustomMap> domainInfoList = domainInfoListMap.getCustomMapList("domainInfoList");
		params.put("count", domainInfoListMap.getString("count"));
		CustomMap pagingCreator = new PagingCreator(params).toCustomMap();
		
		modelAndView.addObject("pagingCreator", pagingCreator);
		modelAndView.addObject("domainInfoList", domainInfoList);
		modelAndView.addObject("params", params);
		
		modelAndView.setViewName("meta2025/domain/domainList");
		
		return modelAndView;
	}
	
	/**
	 * <pre>
	 * 메소드명: metdm04
	 * 설명: 도메인삭제요청
	 * </pre>
	 * @param params
	 * @return
	 */
	@PostMapping("/METDM04")
	@ResponseBody
	public Object metdm04(@RequestBody CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		
		if (!SecurityUtils.hasAuthorized(CustomAuthCode.A001)) {
			throw new CustomException(CustomExceptionCode.ERR403, new String[] { CustomAuthCode.A001.getCode(), CustomAuthCode.A001.getCodeName() });
		}
		
		resultMap = domainService.deleteDomainInfo(params);
		
		return getResponse(resultMap);
	}
	
	/**
	 * <pre>
	 * 메소드명: metdm05
	 * 설명: 도메인 검색
	 * </pre>
	 * @param params
	 * @return
	 */
	@PostMapping("/METDM05")
	@ResponseBody
	public Object metdm05(@RequestBody CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		
		CustomMap domainScInfoListMap = domainService.getDomainScInfoList(params);
		List<CustomMap> domainScInfoList = domainScInfoListMap.getCustomMapList("domainScInfoList");
		
		resultMap.put("domainScInfoList", domainScInfoList);
		resultMap.put("count", domainScInfoListMap.getString("count"));
		
		
		return getResponse(resultMap);
	}
	
}
