package com.denghb.dbhelper.generate;

/**
 * 
 * @author denghb
 *
 */
public class TableDdl {

	private String table;

	private String createTable;

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getCreateTable() {
		return createTable;
	}

	public void setCreateTable(String createTable) {
		this.createTable = createTable;
	}

	@Override
	public String toString() {
		return "TableDdl [table=" + table + ", createTable=" + createTable + "]";
	}

}
