package com.custom.met.meta2025.column.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.custom.met.cmmn.model.CustomMap;
import com.custom.met.cmmn.paging.PagingCreator;
import com.custom.met.cmmn.web.CustomController;
import com.custom.met.meta2025.column.service.ColumnMetaService;

import lombok.extern.log4j.Log4j2;

@Controller @Log4j2
public class ColumnMetaController extends CustomController {

	@Autowired
	private ColumnMetaService columnMetaService;
	
	/**
	 * <pre>
	 * 메소드명: metcu01
	 * 설명: 컬럼메타 목록페이지
	 * </pre>
	 * @param map
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/METCU01")
	public ModelAndView metcu01(@RequestParam Map<String, Object> map, ModelAndView modelAndView) {
		CustomMap params = new CustomMap(map);
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		
		CustomMap columnMetaInfoListMap = columnMetaService.getColumnMetaInfoList(params);
		
		List<CustomMap> columnMetaInfoList = columnMetaInfoListMap.getCustomMapList("columnMetaInfoList");
		params.put("count", columnMetaInfoListMap.getString("count"));
		
		CustomMap pagingCreator = new PagingCreator(params).toCustomMap();
		
		modelAndView.addObject("pagingCreator", pagingCreator);
		modelAndView.addObject("columnMetaInfoList", columnMetaInfoList);
		modelAndView.addObject("params", params);
		
		modelAndView.setViewName("meta2025/column/columnMetaList");
		
		return modelAndView;
	}
}
