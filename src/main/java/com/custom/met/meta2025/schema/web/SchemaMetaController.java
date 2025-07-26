package com.custom.met.meta2025.schema.web;

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
import com.custom.met.meta2025.schema.service.SchemaMetaService;

import lombok.extern.log4j.Log4j2;

@Controller @Log4j2
public class SchemaMetaController extends CustomController {

	@Autowired
	private SchemaMetaService schemaMetaService;
	
	/**
	 * <pre>
	 * 메소드명: metsc01
	 * 설명: 스키마메타 목록페이지
	 * </pre>
	 * @param map
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/METSC01")
	public ModelAndView metsc01(@RequestParam Map<String, Object> map, ModelAndView modelAndView) {
		CustomMap params = new CustomMap(map);
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		
		CustomMap schemaMetaInfoListMap = schemaMetaService.getSchemaMetaInfoList(params);
		
		List<CustomMap> schemaMetaInfoList = schemaMetaInfoListMap.getCustomMapList("schemaMetaInfoList");
		params.put("count", schemaMetaInfoListMap.getString("count"));
		CustomMap pagingCreator = new PagingCreator(params).toCustomMap();
		
		modelAndView.addObject("pagingCreator", pagingCreator);
		modelAndView.addObject("schemaMetaInfoList", schemaMetaInfoList);
		modelAndView.addObject("params", params);
		
		modelAndView.setViewName("meta2025/schema/schemaMetaList");
		
		return modelAndView;
	}
	
	/**
	 * <pre>
	 * 메소드명: metsc02
	 * 설명: 스키마메타 등록페이지
	 * </pre>
	 * @param map
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/METSC02")
	public ModelAndView metsc02(@RequestParam Map<String, Object> map, ModelAndView modelAndView) {
		CustomMap params = new CustomMap(map);
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		
		modelAndView.setViewName("meta2025/schema/schemaMetaReg");
		
		return modelAndView;
	}
	
	/**
	 * <pre>
	 * 메소드명: metsc03
	 * 설명: 스키마메타 등록요청
	 * </pre>
	 * @param params
	 * @return
	 */
	@PostMapping("/METSC03")
	@ResponseBody
	public Object metsc03(@RequestBody CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		
		params.put("sysCreator", StringUtils.NVL(SecurityUtils.getUsername(), "SYSTEM"));
		
		resultMap = schemaMetaService.insertSchemaMetaInfo(params);
		
		return getResponse(resultMap);
	}
	
	/**
	 * <pre>
	 * 메소드명: metsc04
	 * 설명: 스키마메타 삭제요청
	 * </pre>
	 * @param params
	 * @return
	 */
	@PostMapping("/METSC04")
	@ResponseBody
	public Object metsc04(@RequestBody CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		
		if (!SecurityUtils.hasAuthorized(CustomAuthCode.A001)) {
			throw new CustomException(CustomExceptionCode.ERR403, new String[] { CustomAuthCode.A001.getCode(), CustomAuthCode.A001.getCodeName() });
		}
		
		resultMap = schemaMetaService.deleteSchemaMetaInfo(params);
		
		return getResponse(resultMap);
	}
	
	/**
	 * <pre>
	 * 메소드명: metsc05
	 * 설명: 스키마메타 검색
	 * </pre>
	 * @param params
	 * @return
	 */
	@PostMapping("/METSC05")
	@ResponseBody
	public Object metsc05(@RequestBody CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		
		CustomMap schemaMetaScInfoListMap = schemaMetaService.getSchemaMetaScInfoList(params);
		List<CustomMap> schemaMetaScInfoList = schemaMetaScInfoListMap.getCustomMapList("schemaMetaScInfoList");
		
		resultMap.put("schemaMetaScInfoList", schemaMetaScInfoList);
		resultMap.put("count", schemaMetaScInfoListMap.getString("count"));
		
		return getResponse(resultMap);
	}
}
