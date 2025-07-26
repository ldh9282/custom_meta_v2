package com.custom.met.meta2025.table.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.custom.met.cmmn.model.CustomMap;

@Repository
public class TableMetaDao {

	@Autowired
	private SqlSessionTemplate sst;
	
	/**
	 * <pre>
	 * 메서드명: selectTableMetaSno
	 * 설명: 테이블메타일련번호 조회
	 * </pre>
	 * @return
	 */
	public String selectTableMetaSno() {
		return sst.selectOne("TableMeta.selectTableMetaSno");
	}
	
	/**
	 * <pre>
	 * 메서드명: insertTableMeta
	 * 설명: 테이블메타기본정보 등록
	 * </pre>
	 * @param params
	 * @return
	 */
	public int insertTableMeta(CustomMap params) {
		return sst.insert("TableMeta.insertTableMeta", params);
	}
	
	/**
	 * <pre>
	 * 메서드명: selectTableMeta
	 * 설명: 테이블메타기본정보 상세조회
	 * </pre>
	 * @param params
	 * @return
	 */
	public CustomMap selectTableMeta(CustomMap params) {
		return sst.selectOne("TableMeta.selectTableMeta", params);
	}
	
	/**
	 * <pre>
	 * 메서드명: selectTableColumnList
	 * 설명: 테이블컬럼 목록조회
	 * </pre>
	 * @param params
	 * @return
	 */
	public List<CustomMap> selectTableColumnList(CustomMap params) {
		return sst.selectList("TableMeta.selectTableColumnList", params);
	}
	
	/**
	 * <pre>
	 * 메서드명: createTable
	 * 설명: 테이블생성
	 * </pre>
	 * @param params
	 * @return
	 */
	public int createTable(CustomMap params) {
		return sst.update("TableMeta.createTable", params);
	}
	
	/**
	 * <pre>
	 * 메서드명: addTableComment
	 * 설명: 테이블코멘트생성
	 * </pre>
	 * @param params
	 * @return
	 */
	public int addTableComment(CustomMap params) {
		return sst.update("TableMeta.addTableComment", params);
	}
	
	/**
	 * <pre>
	 * 메서드명: createPkIndex
	 * 설명: Pk인덱스생성
	 * </pre>
	 * @param params
	 * @return
	 */
	public int createPkIndex(CustomMap params) {
		return sst.update("TableMeta.createPkIndex", params);
	}
	
	
	/**
	 * <pre>
	 * 메서드명: selectTableMetaInfoList
	 * 설명: 테이블메타 목록조회
	 * </pre>
	 * @param params
	 * @return
	 */
	public List<CustomMap> selectTableMetaInfoList(CustomMap params) {
		return sst.selectList("TableMeta.selectTableMetaInfoList", params);
	}

	/**
	 * <pre>
	 * 메서드명: dropTable
	 * 설명: 테이블삭제
	 * </pre>
	 * @param params
	 * @return
	 */
	public int dropTable(CustomMap params) {
		return sst.update("TableMeta.dropTable", params);
	}

	/**
	 * <pre>
	 * 메서드명: deleteTableMetaInfo
	 * 설명: 테이블메타기본정보삭제
	 * </pre>
	 * @param params
	 * @return
	 */
	public int deleteTableMetaInfo(CustomMap params) {
		return sst.update("TableMeta.deleteTableMetaInfo", params);
	}
	
}