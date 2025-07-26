package com.custom.met.cmmn.constant;

/**
 * <pre>
 * 클래스명: CustomAuthCode
 * 설명: 권한코드
 * </pre>
 */
public enum CustomAuthCode {

	/**
	 * <pre>
	 * 구분코드: A001 구분코드명: DBA
	 * </pre>
	 */
	A001("A001", "DBA"),
	/**
	 * <pre>
	 * 구분코드: A002 구분코드명: MODELER
	 * </pre>
	 */
	A002("A002", "MODELER"),
	;
	private final String code;
	private final String codeName;
	
	CustomAuthCode(String code, String codeName) {
		this.code = code;
		this.codeName = codeName;
	}

	public String getCode() {
		return code;
	}

	public String getCodeName() {
		return codeName;
	}
	
	
	
}
