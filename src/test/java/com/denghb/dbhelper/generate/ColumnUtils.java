package com.denghb.dbhelper.generate;

import org.springframework.util.StringUtils;

import com.denghb.dbhelper.utils.DbHelperUtils;

public class ColumnUtils {

	/**
	 * 移除所有的"_"然后每个"_"下一个字符转大写
	 * 
	 * @return
	 */
	public static String removeAll_AndNextCharToUpperCase(String string) {
		if (null != string) {
			while (true) {
				string = remove_AndNextCharToUpperCase(string);
				if (-1 == string.indexOf("_")) {
					return string;
				}
			}
		}
		return null;
	}

	/**
	 * 移除第一个"_"并将后一个字符串大写
	 * 
	 * @param string
	 * @return
	 */
	private static String remove_AndNextCharToUpperCase(String string) {
		int index = string.indexOf("_");
		if (-1 < index) {
			// 将字符串截成2份 以"_"分开
			String str1 = string.substring(0, index);
			String str2 = string.substring(index + 1, string.length());
			return str1 + DbHelperUtils.firstCharToUpperCase(str2);
		}
		return string;
	}

	/**
	 * 数据库类型转java类型 TODO 待完善
	 * 
	 * @param dataType
	 * @return
	 */
	public static String databaseTypeToJavaType(String dataType) {
		if (StringUtils.hasText(dataType)) {
			dataType = dataType.toLowerCase().trim();
			if ("varchar".equals(dataType) || "text".equals(dataType) || "string".equals(dataType)) {
				dataType = "String";
			} else if ("int".equals(dataType) || "integer".equals(dataType)) {
				dataType = "Integer";
			} else if ("bigint".equals(dataType) || "long".equals(dataType)) {
				dataType = "Long";
			} else if ("timestamp".equals(dataType) || "datetime".equals(dataType) || "date".equals(dataType)) {
				dataType = "Date";
			} else if ("tinyint".equals(dataType) || "boolean".equals(dataType)) {
				dataType = "Boolean";
			} else {
				throw new RuntimeException("dataType not find :" + dataType);
			}
			return dataType;
		}
		return null;
	}
}
