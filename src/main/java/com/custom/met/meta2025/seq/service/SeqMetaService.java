package com.custom.met.meta2025.seq.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.custom.met.cmmn.exception.CustomException;
import com.custom.met.cmmn.exception.CustomExceptionCode;
import com.custom.met.cmmn.model.CustomMap;

@Service
public class SeqMetaService {

	@Autowired
	private SeqMetaDao seqMetaDao;
	
	public CustomMap getSeqMetaInfoList(CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		try {
			params.put("pageNum", params.getString("pageNum", "1"));
			params.put("rowAmountPerPage", params.getString("rowAmountPerPage", "10"));
			
			List<CustomMap> seqMetaInfoList = seqMetaDao.selectSeqMetaInfoList(params);
			
			resultMap.put("seqMetaInfoList", seqMetaInfoList);
			resultMap.put("count", seqMetaInfoList.size() > 0 ? seqMetaInfoList.get(0).getString("count") : "0");
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR511, new String[] { "시퀀스메타정보목록" }, e);
		}
		
		return resultMap;
	}
}
