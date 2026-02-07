package com.custom.met.cmmn.utils;

public class StringUtils {

	/**
	 * <pre>
	 * 메소드명: isNumber
	 * 설명: 숫자체크
	 * </pre>
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		return str != null && str.matches("\\d+");
	}
	
	/**
	 * <pre>
	 * 메소드명: isNVL
	 * 설명: Null or Empty 이면 true
	 * </pre>
	 * @param input
	 * @return
	 */
	public static boolean isNVL(String input) {
		if (input == null || input.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * <pre>
	 * 메소드명: NVL
	 * 설명: Null or Empty 이면 기본값 반환
	 * </pre>
	 * @param input
	 * @param defaultValue
	 * @return
	 */
	public static String NVL(String input, String defaultValue) {
		if (input == null || input.isEmpty()) {
			return defaultValue;
		} else {
			return input;
		}
	}
	
	/**
	 * <pre>
	 * 메서드명: camel2Snake
	 * 설명: 카멜식을 스네이크식으로 변환
	 * </pre>
	 * @param str
	 * @return
	 */
	public static String camel2Snake(String str) {
		if (str == null || str.isEmpty()) {
            return str;
        }
        str = Character.toLowerCase(str.charAt(0)) + str.substring(1);

        return str.replaceAll("([A-Z])", "_$1").toUpperCase();
    }

	/**
	 * <pre>
	 * 메서드명: camel2Snake
	 * 설명: 스네이크식을 카멜식으로 변환
	 * </pre>
	 * @param str
	 * @return
	 */
    public static String snake2Camel(String str) {
    	
    	 if (str == null || str.isEmpty()) {
    	        return str;
    	    }

	    StringBuilder result = new StringBuilder();
	    boolean toUpper = false;

	    for (char c : str.toCharArray()) {
	        if (c == '_') {
	            toUpper = true;
	        } else {
	            result.append(toUpper ? Character.toUpperCase(c) : Character.toLowerCase(c));
	            toUpper = false;
	        }
	    }

	    return result.toString();
    }
    
    /**
	 * <pre>
	 * 메서드명: changeCase
	 * 설명: case 변환
	 * </pre>
	 * @param str
	 * @return
	 */
    public static String changeCase(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        if (str.equals(str.toUpperCase())) {
            return str.toLowerCase();
        } else if (str.equals(str.toLowerCase())) {
            return str.toUpperCase();
        } else {
            return str;
        }
    }
}
