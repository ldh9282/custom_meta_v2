package com.custom.met.meta2025.column.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.custom.met.cmmn.exception.CustomException;
import com.custom.met.cmmn.exception.CustomExceptionCode;
import com.custom.met.cmmn.model.CustomMap;

@Service
public class ColumnMetaService {

	@Autowired
	private ColumnMetaDao columnMetaDao;
	
	public CustomMap getColumnMetaInfoList(CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		try {
			params.put("pageNum", params.getString("pageNum", "1"));
			params.put("rowAmountPerPage", params.getString("rowAmountPerPage", "10"));
			
			List<CustomMap> columnMetaInfoList = columnMetaDao.selectColumnMetaInfoList(params);
			
			resultMap.put("columnMetaInfoList", columnMetaInfoList);
			resultMap.put("count", columnMetaInfoList.size() > 0 ? columnMetaInfoList.get(0).getString("count") : "0");
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR511, new String[] { "컬럼메타정보목록" }, e);
		}
		
		return resultMap;
	}
}
