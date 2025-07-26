package com.custom.met.meta2025.table.web;

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
import com.custom.met.cmmn.utils.MapUtils;
import com.custom.met.cmmn.utils.StringUtils;
import com.custom.met.cmmn.web.CustomController;
import com.custom.met.meta2025.table.service.TableMetaService;

import lombok.extern.log4j.Log4j2;

@Controller @Log4j2
public class TableMetaController extends CustomController {

	@Autowired
	private TableMetaService tableMetaService;
	
	/**
	 * <pre>
	 * 메소드명: mettb01
	 * 설명: 테이블메타 목록페이지
	 * </pre>
	 * @param map
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/METTB01")
	public ModelAndView mettb01(@RequestParam Map<String, Object> map, ModelAndView modelAndView) {
		CustomMap params = new CustomMap(map);
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		
		CustomMap tableMetaInfoListMap = tableMetaService.getTableMetaInfoList(params);
		
		List<CustomMap> tableMetaInfoList = tableMetaInfoListMap.getCustomMapList("tableMetaInfoList");
		
		params.put("count", tableMetaInfoListMap.getString("count"));
		CustomMap pagingCreator = new PagingCreator(params).toCustomMap();
		
		modelAndView.addObject("pagingCreator", pagingCreator);
		modelAndView.addObject("tableMetaInfoList", tableMetaInfoList);
		modelAndView.addObject("params", params);
		
		modelAndView.setViewName("meta2025/table/tableMetaList");
		
		return modelAndView;
	}
	
	/**
	 * <pre>
	 * 메소드명: mettb02
	 * 설명: 테이블메타 등록페이지
	 * </pre>
	 * @param map
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/METTB02")
	public ModelAndView mettb02(@RequestParam Map<String, Object> map, ModelAndView modelAndView) {
		CustomMap params = new CustomMap(map);
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		
		modelAndView.setViewName("meta2025/table/tableMetaReg");
		
		return modelAndView;
	}
	
	/**
	 * <pre>
	 * 메소드명: mettb03
	 * 설명: 테이블메타 상세페이지
	 * </pre>
	 * @param map
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/METTB03")
	public ModelAndView mettb03(@RequestParam Map<String, Object> map, ModelAndView modelAndView) {
		CustomMap params = new CustomMap(map);
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		
		MapUtils.paramsValidation(params, new String[][] {
			{ "tableMetaSno", "required" }
			, { "tableMetaSno", "number" }
		});
		
		CustomMap detail = tableMetaService.getTableMetaDetail(params);
		
		modelAndView.addObject("detail", detail);
		
		modelAndView.setViewName("meta2025/table/tableMetaDetail");
		
		return modelAndView;
	}
	
	/**
	 * <pre>
	 * 메소드명: mettb04
	 * 설명: 테이블메타 등록요청
	 * </pre>
	 * @param params
	 * @return
	 */
	@PostMapping("/METTB04")
	@ResponseBody
	public Object mettb04(@RequestBody CustomMap params) {
		
		CustomMap resultMap = new CustomMap();
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		
		params.put("sysCreator", StringUtils.NVL(SecurityUtils.getUsername(), "SYSTEM"));
		
		resultMap = tableMetaService.insertTableMetaInfo(params);
		
		return getResponse(resultMap);
	}
	
	/**
	 * <pre>
	 * 메소드명: mettb05
	 * 설명: 테이블메타 삭제요청
	 * </pre>
	 * @param params
	 * @return
	 */
	@PostMapping("/METTB05")
	@ResponseBody
	public Object mettb05(@RequestBody CustomMap params) {
		
		CustomMap resultMap = new CustomMap();
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		
		resultMap = tableMetaService.deleteTableMetaInfo(params);
		
		return getResponse(resultMap);
	}
	
	/**
	 * <pre>
	 * 메소드명: mettb06
	 * 설명: 테이블메타 수정요청
	 * </pre>
	 * @param params
	 * @return
	 */
	@PostMapping("/METTB06")
	@ResponseBody
	public Object mettb06(@RequestBody CustomMap params) {
		
		CustomMap resultMap = new CustomMap();
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		params.put("sysModifier", StringUtils.NVL(SecurityUtils.getUsername(), "SYSTEM"));
		
		resultMap = tableMetaService.updateTableMetaInfo(params);
		
		return getResponse(resultMap);
	}
}
