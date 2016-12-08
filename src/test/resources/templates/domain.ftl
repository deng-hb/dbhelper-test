package ${packageName};

import com.denghb.dbhelper.annotation.Column;
import com.denghb.dbhelper.annotation.Id;
import com.denghb.dbhelper.annotation.Table;

/**
 * ${tableComment}
 * DDL
 * <pre>${tableDdl}<pre>
 * @author DbHelper
 * @generateTime ${generateTime}
 */
@Table(name="${tableName}",database="${databaseName}")
public class ${domainName} implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	<#list list as table>
	/** ${table.columnComment} */
	<#if table.columnKey = "PRI">@Id</#if>@Column(name="${table.columnName}")
	private ${table.dataType} ${table.objectName};
	
    </#list>

	<#list list as table>
	public ${table.dataType} get${table.methodName}(){
		return ${table.objectName};
	}

	public void set${table.methodName}(${table.dataType} ${table.objectName}){
		this.${table.objectName} = ${table.objectName};
	}

	</#list>
	@Override
	public String toString(){
		StringBuffer stringBuffer = new StringBuffer("${domainName} [");
		<#list list as table>
		stringBuffer.append("${table.objectName}=");
		stringBuffer.append(${table.objectName});
		stringBuffer.append(",");
		</#list>
		stringBuffer.append("]");
	
		return stringBuffer.toString();
	}
}