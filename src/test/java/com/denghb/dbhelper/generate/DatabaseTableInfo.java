package com.denghb.dbhelper.generate;

public class DatabaseTableInfo {

	private String tableName;
	
	private String tableComment;

	public String getTableName() {

		if(null != tableName){
			tableName = tableName.replaceAll("\\*/", "");
		}
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableComment() {
		if(null != tableComment){
			tableComment = tableComment.replaceAll("\\*/", "");
		}
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}
	
	
}