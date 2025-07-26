package com.custom.met.meta2025.schema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.custom.met.cmmn.exception.CustomException;
import com.custom.met.cmmn.exception.CustomExceptionCode;
import com.custom.met.cmmn.model.CustomMap;

@Service
public class SchemaMetaService {

	@Autowired
	private SchemaMetaDao schemaMetaDao;
	
	public CustomMap getSchemaMetaInfoList(CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		try {
			params.put("pageNum", params.getString("pageNum", "1"));
			params.put("rowAmountPerPage", params.getString("rowAmountPerPage", "10"));
			
			List<CustomMap> schemaMetaInfoList = schemaMetaDao.selectSchemaMetaInfoList(params);
			
			resultMap.put("schemaMetaInfoList", schemaMetaInfoList);
			resultMap.put("count", schemaMetaInfoList.size() > 0 ? schemaMetaInfoList.get(0).getString("count") : "0");
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR511, new String[] { "스키마메타정보목록" }, e);
		}
		
		return resultMap;
	}
	
	@Transactional
	public CustomMap insertSchemaMetaInfo(CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		try {
			schemaMetaDao.createSchema(params);
			schemaMetaDao.addSchemaComment(params);
			
			String schemaMetaSno = schemaMetaDao.selectSchemaMetaSno();
			
			params.put("schemaMetaSno", schemaMetaSno);
			
			schemaMetaDao.insertSchemaMeta(params);
			
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR521, new String[] { "스키마메타정보" }, e);
		}
		
		return resultMap;
		
	}
	
	@Transactional
	public CustomMap deleteSchemaMetaInfo(CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		try {
			CustomMap schemaMeta = schemaMetaDao.selectSchemaMeta(params);
			
			params.put("schemaName", schemaMeta.getString("schemaName"));
			
			schemaMetaDao.dropSchema(params);
			
			schemaMetaDao.deleteSchemaMeta(params);
			
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR541, new String[] { "스키마메타정보" }, e);
		}
		
		return resultMap;
	}
	
	public CustomMap getSchemaMetaScInfoList(CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		try {
			List<CustomMap> schemaMetaScInfoList = schemaMetaDao.selectSchemaMetaScInfoList(params);
			
			resultMap.put("schemaMetaScInfoList", schemaMetaScInfoList);
			resultMap.put("count", schemaMetaScInfoList.size() > 0 ? schemaMetaScInfoList.get(0).getString("count") : "0");
			
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR511, new String[] { "스키마메타정보검색" }, e);
		}
		
		return resultMap;
	}
}
