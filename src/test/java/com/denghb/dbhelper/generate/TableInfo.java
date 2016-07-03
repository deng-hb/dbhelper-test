package com.denghb.dbhelper.generate;


import java.io.Serializable;

import com.denghb.dbhelper.utils.DbHelperUtils;

public class TableInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String columnName;
	private String dataType;
	private String columnComment;

	// 大写对象名称
	private String objectName;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getDataType() {
		// TODO 
		return ColumnUtils.databaseTypeToJavaType(dataType);
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

	public String getObjectName() {
		return DbHelperUtils.firstCharToUpperCase(this.columnName);
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	@Override
	public String toString() {
		return "TableInfo [columnName=" + columnName + ", dataType=" + dataType + ", columnComment=" + columnComment + "]";
	}

}
