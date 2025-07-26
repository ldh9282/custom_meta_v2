package com.custom.met.meta2025.seq.web;

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
import com.custom.met.meta2025.seq.service.SeqMetaService;

import lombok.extern.log4j.Log4j2;

@Controller @Log4j2
public class SeqMetaController extends CustomController {

	@Autowired
	private SeqMetaService seqMetaService;
	
	/**
	 * <pre>
	 * 메소드명: metse01
	 * 설명: 시퀀스메타 목록페이지
	 * </pre>
	 * @param map
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/METSE01")
	public ModelAndView metse01(@RequestParam Map<String, Object> map, ModelAndView modelAndView) {
		CustomMap params = new CustomMap(map);
		
		if (log.isDebugEnabled()) { log.debug("params ::: " + params); }
		
		CustomMap seqMetaInfoListMap = seqMetaService.getSeqMetaInfoList(params);
		
		List<CustomMap> seqMetaInfoList = seqMetaInfoListMap.getCustomMapList("seqMetaInfoList");
		params.put("count", seqMetaInfoListMap.getString("count"));
		
		CustomMap pagingCreator = new PagingCreator(params).toCustomMap();
		
		modelAndView.addObject("pagingCreator", pagingCreator);
		modelAndView.addObject("seqMetaInfoList", seqMetaInfoList);
		modelAndView.addObject("params", params);
		
		modelAndView.setViewName("meta2025/seq/seqMetaList");
		
		return modelAndView;
	}
}
