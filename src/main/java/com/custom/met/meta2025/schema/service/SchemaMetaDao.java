package com.custom.met.meta2025.schema.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.custom.met.cmmn.model.CustomMap;

@Repository
public class SchemaMetaDao {

	@Autowired
	private SqlSessionTemplate sst;
	
	/**
	 * <pre>
	 * 메소드명: createSchema
	 * 설명: 스키마 생성
	 * </pre>
	 * @param params
	 * @return
	 */
	public int createSchema(CustomMap params) {
		return sst.update("SchemaMeta.createSchema", params);
	}
	
	/**
	 * <pre>
	 * 메소드명: addSchemaComment
	 * 설명: 스키마코멘트 생성
	 * </pre>
	 * @param params
	 * @return
	 */
	public int addSchemaComment(CustomMap params) {
		return sst.update("SchemaMeta.addSchemaComment", params);
	}
	
	/**
	 * <pre>
	 * 메소드명: dropSchema
	 * 설명: 스키마 삭제
	 * </pre>
	 * @param params
	 * @return
	 */
	public int dropSchema(CustomMap params) {
		return sst.update("SchemaMeta.dropSchema", params);
	}
	
	/**
	 * <pre>
	 * 메소드명: selectSchemaMetaSno
	 * 설명: 스키마메타일련번호 조회
	 * </pre>
	 * @return
	 */
	public String selectSchemaMetaSno() {
		return sst.selectOne("SchemaMeta.selectSchemaMetaSno");
	}
	
	/**
	 * <pre>
	 * 메소드명: selectSchemaMeta
	 * 설명: 스키마메타정보 단건조회
	 * </pre>
	 * @param params
	 * @return
	 */
	public CustomMap selectSchemaMeta(CustomMap params) {
		return sst.selectOne("SchemaMeta.selectSchemaMeta", params);
	}
	
	/**
	 * <pre>
	 * 메소드명: selectSchemaMetaInfoList
	 * 설명: 스키마메타정보 목록조회
	 * </pre>
	 * @param params
	 * @return
	 */
	public List<CustomMap> selectSchemaMetaInfoList(CustomMap params) {
		return sst.selectList("SchemaMeta.selectSchemaMetaInfoList", params);
	}
	
	/**
	 * <pre>
	 * 메소드명: selectSchemaMetaScInfoList
	 * 설명: 스키마메타정보 목록조회
	 * </pre>
	 * @param params
	 * @return
	 */
	public List<CustomMap> selectSchemaMetaScInfoList(CustomMap params) {
		return sst.selectList("SchemaMeta.selectSchemaMetaScInfoList", params);
	}
	
	/**
	 * <pre>
	 * 메소드명: insertSchemaMeta
	 * 설명: 스키마메타정보 등록
	 * </pre>
	 * @param params
	 * @return
	 */
	public int insertSchemaMeta(CustomMap params) {
		return sst.insert("SchemaMeta.insertSchemaMeta", params);
	}
	
	
	/**
	 * <pre>
	 * 메소드명: deleteSchemaMeta
	 * 설명: 스키마메타정보 삭제
	 * </pre>
	 * @param params
	 * @return
	 */
	public int deleteSchemaMeta(CustomMap params) {
		return sst.update("SchemaMeta.deleteSchemaMeta", params);
	}
	
	
}
