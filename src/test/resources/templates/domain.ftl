package ${packageName};

import com.denghb.dbhelper.annotation.Column;
import com.denghb.dbhelper.annotation.Table;

<#if isDateType >
import java.util.Date;
</#if>

/**
 * DDL
 * <pre>
 * ${tableDdl}
 * <pre>
 * @author DbHelper
 * @generateTime ${generateTime}
 */
@Table(name="${tableName}",database="${databaseName}")
public class ${domainName} implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	<#list list as table>
	/** ${table.columnComment} */
	@Column(name="${table.columnName}")
	private ${table.dataType} ${table.columnName};
	
    </#list>

	<#list list as table>
	public ${table.dataType} get${table.objectName}(){
		return ${table.columnName};
	}

	public void set${table.objectName}(${table.dataType} ${table.columnName}){
		this.${table.columnName} = ${table.columnName};
	}

	</#list>
	@Override
	public String toString(){
		return "${domainName} [<#list list as table>${table.columnName}=" + ${table.columnName} +", </#list>]";
	}
}