package com.custom.met.meta2025.seq.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.custom.met.cmmn.model.CustomMap;

@Repository
public class SeqMetaDao {

	@Autowired
	private SqlSessionTemplate sst;
	
	
	/**
	 * <pre>
	 * 메서드명: createSeq
	 * 설명: 시퀀스생성
	 * </pre>
	 * @param params
	 * @return
	 */
	public int createSeq(CustomMap params) {
		return sst.update("SeqMeta.createSeq", params);
	}
	
	/**
	 * <pre>
	 * 메서드명: selectSeqMetaSno
	 * 설명: 시퀀스메타일련번호조회
	 * </pre>
	 * @return
	 */
	public String selectSeqMetaSno() {
		return sst.selectOne("SeqMeta.selectSeqMetaSno");
	}
	/**
	 * <pre>
	 * 메서드명: insertSeqMeta
	 * 설명: 시퀀스메타기본정보등록
	 * </pre>
	 * @param params
	 * @return
	 */
	public int insertSeqMeta(CustomMap params) {
		return sst.insert("SeqMeta.insertSeqMeta", params);
	}

	/**
	 * <pre>
	 * 메서드명: selectSeqMetaInfoList
	 * </pre>
	 * 설명: 시퀀스메타기본정보목록조회
	 * @param params
	 * @return
	 */
	public List<CustomMap> selectSeqMetaInfoList(CustomMap params) {
		return sst.selectList("SeqMeta.selectSeqMetaInfoList", params);
	}

	/**
	 * <pre>
	 * 메서드명: dropSeq
	 * 설명: 시퀀스삭제
	 * </pre>
	 * @param params
	 * @return
	 */
	public int dropSeq(CustomMap params) {
		return sst.update("SeqMeta.dropSeq", params);
	}

	/**
	 * <pre>
	 * 메서드명: deleteSeqMeta
	 * 설명: 시퀀스메타기본정보삭제
	 * </pre>
	 * @param params
	 * @return
	 */
	public int deleteSeqMeta(CustomMap params) {
		return sst.update("SeqMeta.deleteSeqMeta", params);
		
	}

	/**
	 * <pre>
	 * 메서드명: selectSeqMetaInfoDetail
	 * 설명: 시퀀스메타기본정보조회
	 * </pre>
	 * @param params
	 * @return
	 */
	public CustomMap selectSeqMetaInfoDetail(CustomMap params) {
		return sst.selectOne("SeqMeta.selectSeqMetaInfoDetail", params);
		
	}
}