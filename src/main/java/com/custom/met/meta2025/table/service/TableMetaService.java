package com.custom.met.meta2025.table.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.custom.met.cmmn.exception.CustomException;
import com.custom.met.cmmn.exception.CustomExceptionCode;
import com.custom.met.cmmn.model.CustomMap;
import com.custom.met.meta2025.column.service.ColumnMetaDao;
import com.custom.met.meta2025.seq.service.SeqMetaDao;
import com.custom.met.meta2025.term.service.TermDao;

@Service
public class TableMetaService {

	@Autowired
	private TableMetaDao tableMetaDao;
	
	@Autowired
	private ColumnMetaDao columnMetaDao;
	
	@Autowired
	private SeqMetaDao seqMetaDao;
	
	@Autowired
	private TermDao termDao;
	
	@Transactional
	public CustomMap insertTableMetaInfo(CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		try {
			List<CustomMap> pkColumnList = params.getCustomMapList("pkColumnList");
			List<CustomMap> columnList = params.getCustomMapList("columnList");
			List<CustomMap> sysColumnList = Arrays.asList(
					createSysColumnMap("시스템생성자", "sysCreator", "SYS_CREATOR", "CHARACTER VARYING(200)"),
					createSysColumnMap("시스템수정자", "sysModifier", "SYS_MODIFIER", "CHARACTER VARYING(200)"),
					createSysColumnMap("시스템생성일", "sysCreatedAt", "SYS_CREATED_AT", "TIMESTAMP WITHOUT TIME ZONE"),
					createSysColumnMap("시스템수정일", "sysModifiedAt", "SYS_MODIFIED_AT", "TIMESTAMP WITHOUT TIME ZONE")
			);
			
			params.put("sysColumnList", sysColumnList);
			
			tableMetaDao.createTable(params);
			tableMetaDao.addTableComment(params);
			tableMetaDao.createPkIndex(params);
			
			String tableMetaSno = tableMetaDao.selectTableMetaSno();
			
			params.put("tableMetaSno", tableMetaSno);
			tableMetaDao.insertTableMeta(params);
			
			for (CustomMap item : pkColumnList) {
				String columnMetaSno = columnMetaDao.selectColumnMetaSno();
				params.put("columnMetaSno", columnMetaSno);
				params.put("columnName", item.getString("columnName"));
				params.put("columnCamelName", item.getString("columnCamelName"));
				params.put("columnSnakeName", item.getString("columnSnakeName"));
				params.put("columnType", item.getString("columnType"));
				params.put("termSno", item.getString("termSno"));
				params.put("nullColumnYn", item.getString("nullColumnYn"));
				params.put("pkColumnYn", item.getString("pkColumnYn"));
				params.put("sysColumnYn", item.getString("sysColumnYn"));
				
				columnMetaDao.insertColumnMeta(params);
			}
			
			for (CustomMap item : columnList) {
				String columnMetaSno = columnMetaDao.selectColumnMetaSno();
				params.put("columnMetaSno", columnMetaSno);
				params.put("columnName", item.getString("columnName"));
				params.put("columnCamelName", item.getString("columnCamelName"));
				params.put("columnSnakeName", item.getString("columnSnakeName"));
				params.put("columnType", item.getString("columnType"));
				params.put("termSno", item.getString("termSno"));
				params.put("nullColumnYn", item.getString("nullColumnYn"));
				params.put("pkColumnYn", item.getString("pkColumnYn"));
				params.put("sysColumnYn", item.getString("sysColumnYn"));
				
				columnMetaDao.insertColumnMeta(params);
			}
			
			for (CustomMap item : sysColumnList) {
				String columnMetaSno = columnMetaDao.selectColumnMetaSno();
				params.put("columnMetaSno", columnMetaSno);
				params.put("columnName", item.getString("columnName"));
				params.put("columnCamelName", item.getString("columnCamelName"));
				params.put("columnSnakeName", item.getString("columnSnakeName"));
				params.put("columnType", item.getString("columnType"));
				params.put("termSno", item.getString("termSno"));
				params.put("nullColumnYn", item.getString("nullColumnYn"));
				params.put("pkColumnYn", item.getString("pkColumnYn"));
				params.put("sysColumnYn", item.getString("sysColumnYn"));
				
				columnMetaDao.insertColumnMeta(params);
			}
			
			seqMetaDao.createSeq(params);
			
			String seqMetaSno = seqMetaDao.selectSeqMetaSno();
			
			params.put("seqMetaSno", seqMetaSno);
			
			seqMetaDao.insertSeqMeta(params);
			
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR521, new String[] { "테이블메타정보" }, e);
		}
		
		return resultMap;
	}
	
	public CustomMap getTableMetaDetail(CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		try {
			CustomMap tableMetaInfo = tableMetaDao.selectTableMeta(params);
			List<CustomMap> tableColumnList = tableMetaDao.selectTableColumnList(params);
			
			resultMap.put("tableMetaInfo", tableMetaInfo);
			resultMap.put("tableColumnList", tableColumnList);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR511, new String[] { "테이블메타정보" });
		}
		
		return resultMap;
	}
	
	public CustomMap getTableMetaInfoList(CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		try {
			params.put("pageNum", params.getString("pageNum", "1"));
			params.put("rowAmountPerPage", params.getString("rowAmountPerPage", "10"));
			
			List<CustomMap> tableMetaInfoList = tableMetaDao.selectTableMetaInfoList(params);
			
			resultMap.put("tableMetaInfoList", tableMetaInfoList);
			resultMap.put("count", tableMetaInfoList.size() > 0 ? tableMetaInfoList.get(0).getString("count") : "0");
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR511, new String[] { "테이블메타정보목록" }, e);
		}
		
		return resultMap;
	}
	
	@Transactional
	public CustomMap updateTableMetaInfo(CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		try {
			List<CustomMap> pkColumnList = params.getCustomMapList("pkColumnList");
			List<CustomMap> columnList = params.getCustomMapList("columnList");
			
			CustomMap tableMeta = tableMetaDao.selectTableMeta(params);
			params.put("schemaName", tableMeta.getString("schemaName"));
			params.put("tableName", tableMeta.getString("tableName"));
			
			for (CustomMap item : pkColumnList) {
				params.put("columnMetaSno", item.getString("columnMetaSno"));
				params.put("columnName", item.getString("columnName"));
				params.put("columnCamelName", item.getString("columnCamelName"));
				params.put("columnSnakeName", item.getString("columnSnakeName"));
				params.put("columnType", item.getString("columnType"));
				params.put("termSno", item.getString("termSno"));
				params.put("nullColumnYn", item.getString("nullColumnYn"));
				params.put("pkColumnYn", item.getString("pkColumnYn"));
				params.put("sysColumnYn", item.getString("sysColumnYn"));
				
				CustomMap columnMeta = columnMetaDao.selectColumnMetaInfo(params);
				params.put("oldColumnName", columnMeta.getString("columnName"));
				params.put("oldColumnType", columnMeta.getString("columnType"));
				
				boolean isUpdateColumn = false;
				
				if (!params.getString("oldColumnName").equals(params.getString("columnName"))) {
					columnMetaDao.alterColumnName(params);
					isUpdateColumn = true;
				}
				if (!params.getString("oldColumnType").equals(params.getString("columnType"))) {
					columnMetaDao.alterColumnType(params);
					isUpdateColumn = true;
				}
				
				if (isUpdateColumn) {
					columnMetaDao.updateColumnMetaInfo(params);
				}
				
			}
			for (CustomMap item : columnList) {
				params.put("columnMetaSno", item.getString("columnMetaSno"));
				params.put("columnName", item.getString("columnName"));
				params.put("columnCamelName", item.getString("columnCamelName"));
				params.put("columnSnakeName", item.getString("columnSnakeName"));
				params.put("columnType", item.getString("columnType"));
				params.put("termSno", item.getString("termSno"));
				params.put("nullColumnYn", item.getString("nullColumnYn"));
				params.put("pkColumnYn", item.getString("pkColumnYn"));
				params.put("sysColumnYn", item.getString("sysColumnYn"));
				
				CustomMap columnMeta = columnMetaDao.selectColumnMetaInfo(params);
				params.put("oldColumnName", columnMeta.getString("columnName"));
				params.put("oldColumnType", columnMeta.getString("columnType"));
				
				boolean isUpdateColumn = false;
				
				if (!params.getString("oldColumnName").equals(params.getString("columnName"))) {
					columnMetaDao.alterColumnName(params);
					isUpdateColumn = true;
				}
				if (!params.getString("oldColumnType").equals(params.getString("columnType"))) {
					columnMetaDao.alterColumnType(params);
					isUpdateColumn = true;
				}
				
				if (isUpdateColumn) {
					columnMetaDao.updateColumnMetaInfo(params);
				}
				
			}
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR531, new String[] { "테이블메타정보" }, e);
		}
		
		return resultMap;
	}
	
	@Transactional
	public CustomMap deleteTableMetaInfo(CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		try {
			CustomMap seqMetaInfo = seqMetaDao.selectSeqMetaInfoDetail(params);
			params.put("schemaName", seqMetaInfo.getString("schemaName"));
			params.put("seqName", seqMetaInfo.getString("seqName"));
			
			seqMetaDao.dropSeq(params);
			seqMetaDao.deleteSeqMeta(params);
			
			CustomMap tableMeta = tableMetaDao.selectTableMeta(params);
			params.put("tableName", tableMeta.getString("tableName"));
			
			tableMetaDao.dropTable(params);
			tableMetaDao.deleteTableMetaInfo(params);
			
			columnMetaDao.deleteColumnMetaInfo(params);
			
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR541, new String[] { "테이블메타정보" }, e);
		}
		
		return resultMap;
	}
	
	private CustomMap createSysColumnMap(String columnName, String columnCamelName, String columnSnakeName, String columnType) {
		CustomMap params = new CustomMap();
		params.put("termName", columnName);
		CustomMap termByNameMap = termDao.selectTermByName(params);
		if (termByNameMap == null) {
			termByNameMap = new CustomMap();
		}
		
		CustomMap columnMap = new CustomMap();
		columnMap.put("columnName", termByNameMap.getString("termName", columnName));
		columnMap.put("columnCamelName", termByNameMap.getString("termCamelName", columnCamelName));
		columnMap.put("columnSnakeName", termByNameMap.getString("termSnakeName", columnSnakeName));
		columnMap.put("columnType", termByNameMap.getString("domainType", columnType));
		columnMap.put("termSno", termByNameMap.getString("termSno"));
		columnMap.put("nullColumnYn", "0");
		columnMap.put("pkColumnYn", "0");
		columnMap.put("sysColumnYn", "1");
		return columnMap;
	}
}
