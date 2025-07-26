package com.custom.met.meta2025.column.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.custom.met.cmmn.model.CustomMap;

@Repository
public class ColumnMetaDao {

	@Autowired
	private SqlSessionTemplate sst;
	
	/**
	 * <pre>
	 * 메서드명: selectColumnMetaSno
	 * 설명: 컬럼메타일련번호조회
	 * </pre>
	 * @return
	 */
	public String selectColumnMetaSno() {
		return sst.selectOne("ColumnMeta.selectColumnMetaSno");
	}
	/**
	 * <pre>
	 * 메서드명: insertColumnMeta
	 * 설명: 컬럼메타기본정보등록
	 * </pre>
	 * @param params
	 * @return
	 */
	public int insertColumnMeta(CustomMap params) {
		return sst.insert("ColumnMeta.insertColumnMeta", params);
	}
	
	/**
	 * <pre>
	 * 메서드명: selectColumnMetaInfoList
	 * 설명: 컬럼메타기본정보목록조회
	 * </pre>
	 * @param params
	 * @return
	 */
	public List<CustomMap> selectColumnMetaInfoList(CustomMap params) {
		return sst.selectList("ColumnMeta.selectColumnMetaInfoList", params);
	}
	
	/**
	 * <pre>
	 * 메서드명: selectColumnMetaInfo
	 * 설명: 컬럼메타기본정보상세조회
	 * </pre>
	 * @param params
	 * @return
	 */
	public CustomMap selectColumnMetaInfo(CustomMap params) {
		return sst.selectOne("ColumnMeta.selectColumnMetaInfo", params);
	}
	
	/**
	 * <pre>
	 * 메서드명: updateColumnMetaInfo
	 * 설명: 컬럼메타기본정보수정
	 * </pre>
	 * @param params
	 * @return
	 */
	public int updateColumnMetaInfo(CustomMap params) {
		return sst.update("ColumnMeta.updateColumnMetaInfo", params);
	}
	
	/**
	 * <pre>
	 * 메서드명: alterColumnName
	 * 설명: 컬럼명 수정
	 * </pre>
	 * @param params
	 * @return
	 */
	public int alterColumnName(CustomMap params) {
		return sst.update("ColumnMeta.alterColumnName", params);
	}
	
	/**
	 * <pre>
	 * 메서드명: alterColumnType
	 * 설명: 컬럼타입 수정
	 * </pre>
	 * @param params
	 * @return
	 */
	public int alterColumnType(CustomMap params) {
		return sst.update("ColumnMeta.alterColumnType", params);
	}
	
	/**
	 * <pre>
	 * 메서드명: deleteColumnMetaInfo
	 * 설명: 컬럼메타기본정보삭제
	 * </pre>
	 * @param params
	 * @return
	 */
	public int deleteColumnMetaInfo(CustomMap params) {
		return sst.update("ColumnMeta.deleteColumnMetaInfo", params);
	}
}