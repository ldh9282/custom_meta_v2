package com.custom.met.meta2025.term.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.custom.met.cmmn.exception.CustomException;
import com.custom.met.cmmn.exception.CustomExceptionCode;
import com.custom.met.cmmn.model.CustomMap;

@Service
public class TermService {

	@Autowired
	private TermDao termDao;
	
	public CustomMap insertTermInfo(CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		try {
			CustomMap termRegCheckMap = termDao.selectTermRegCheck(params);
			
			if (!"Y".equals(termRegCheckMap.getString("domainNameAbleYn"))) {
				throw new CustomException(CustomExceptionCode.ERR999, new String[] { "[" + params.getString("domainName") + "] 해당 도메인이 존재하지 않습니다." });
			} else if (!"Y".equals(termRegCheckMap.getString("termNameAbleYn"))) {
				throw new CustomException(CustomExceptionCode.ERR999, new String[] { "[" + params.getString("termName") + "] 해당 용어가 이미 존재합니다." });
			}
			
			String termSno = termDao.selectTermSno();
			
			params.put("termSno", termSno);
			
			termDao.insertTerm(params);
			
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR999, new String[] { e.getMessage() }, e);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR521, new String[] { "용어정보" }, e);
		}
		
		return resultMap;
	}
	
	public CustomMap getTermInfoList(CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		try {
			params.put("pageNum", params.getString("pageNum", "1"));
			params.put("rowAmountPerPage", params.getString("rowAmountPerPage", "10"));
			
			List<CustomMap> termInfoList = termDao.selectTermList(params);
			
			resultMap.put("termInfoList", termInfoList);
			resultMap.put("count", termInfoList.size() > 0 ? termInfoList.get(0).getString("count") : "0");
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR511, new String[] { "용어정보목록" }, e);
		}
		
		return resultMap;
	}
	
	public CustomMap deleteTermInfo(CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		try {
			termDao.deleteTermInfo(params);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR541, new String[] { "용어정보" }, e);
		}
		
		return resultMap;
	}
	
	public CustomMap getTermScInfoList(CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		try {
			List<CustomMap> termScInfoList = termDao.selectTermScList(params);
			
			resultMap.put("termScInfoList", termScInfoList);
			resultMap.put("count", termScInfoList.size() > 0 ? termScInfoList.get(0).getString("count") : "0");
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR511, new String[] { "용어정보검색" }, e);
		}
		
		return resultMap;
	}
}
