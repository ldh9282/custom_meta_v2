package com.custom.met.meta2025.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.custom.met.cmmn.exception.CustomException;
import com.custom.met.cmmn.exception.CustomExceptionCode;
import com.custom.met.cmmn.model.CustomMap;
import com.custom.met.meta2025.term.service.TermDao;

@Service
public class DomainService {

	@Autowired
	private DomainDao domainDao;
	
	@Autowired
	private TermDao termDao;
	
	@Transactional
	public CustomMap insertDomainInfo(CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		try {
			CustomMap domainRegAbleYnMap = domainDao.selectDomainRegAbleYn(params);
			
			if ("N".equals(domainRegAbleYnMap.getString("domainRegAbleYn"))) {
				throw new CustomException(CustomExceptionCode.ERR999, new String[] { "[" + params.getString("domainName") + "] 해당 도메인이 이미 존재합니다." });
			}
			
			String domainSno = domainDao.selectDomainSno();
			
			params.put("domainSno", domainSno);
			
			domainDao.insertDomain(params);
			
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR999, new String[] { e.getMessage() }, e);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR521, new String[] { "도메인정보" }, e);
		}
		
		return resultMap;
	}
	
	public CustomMap getDomainInfoList(CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		try {
			params.put("pageNum", params.getString("pageNum", "1"));
			params.put("rowAmountPerPage", params.getString("rowAmountPerPage", "10"));
			
			List<CustomMap> domainInfoList = domainDao.selectDomainList(params);
			
			resultMap.put("domainInfoList", domainInfoList);
			resultMap.put("count", domainInfoList.size() > 0 ? domainInfoList.get(0).getString("count") : "0");
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR511, new String[] { "도메인정보목록" }, e);
		}
		
		return resultMap;
	}
	
	@Transactional
	public CustomMap deleteDomainInfo(CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		try {
			domainDao.deleteDomainInfo(params);
			
			termDao.deleteTermInfo(params);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR541, new String[] { "도메인정보" }, e);
		}
		
		return resultMap;
	}
	
	public CustomMap getDomainScInfoList(CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		try {
			List<CustomMap> domainScInfoList = domainDao.selectDomainScList(params);
			
			resultMap.put("domainScInfoList", domainScInfoList);
			resultMap.put("count", domainScInfoList.size() > 0 ? domainScInfoList.get(0).getString("count") : "0");
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR511, new String[] { "도메인정보검색" }, e);
		}
		
		return resultMap;
	}
}
