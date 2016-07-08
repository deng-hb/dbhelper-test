package com.denghb.dbhelper.generate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.denghb.dbhelper.DbHelper;
import com.denghb.dbhelper.impl.DbHelperImpl;
import com.denghb.dbhelper.utils.DbHelperUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 
 * @author denghb 
 */
public class GenerateDomainFromTable {
	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		DbHelper dbHelper = app.getBean(DbHelperImpl.class);
		
		String databaseName = "admin";
		String tableName = "account";
//		String tableName = "account_access";
//		String tableName = "account_password";
//		String tableName = "sys_resource";
//		String tableName = "sys_role";
//		String tableName = "sys_role_resource";
//		String tableName = "sys_user";
//		String tableName = "sys_user_role";
		
		String packageName = "com.denghb.admin.domain";
		String projectUrl = "../admin/src/main/java/com/denghb/admin/domain/";
		
		// 类名
		String domainName = ColumnUtils.removeAll_AndNextCharToUpperCase(tableName);
		domainName = DbHelperUtils.firstCharToUpperCase(domainName);
		// 查询对应数据库对应表的字段信息
		String sql = "SELECT * FROM information_schema.COLUMNS WHERE table_name = ? AND table_schema = ? ";

		List<TableInfo> list = dbHelper.list(sql, TableInfo.class, tableName, databaseName);

		// 查询DDL
		TableDdl tableDdl = dbHelper.queryForObject("show create table " + databaseName+"."+tableName, TableDdl.class);
		System.out.println(tableDdl);
		System.out.println(list.toString());

		try {
			Configuration config = new Configuration();
			config.setClassForTemplateLoading(ClassLoader.class, "/templates/");
			Template template = config.getTemplate("domain.ftl", "UTF-8");
			// 创建数据模型
			Map<String, Object> root = new HashMap<String, Object>();

			root.put("list", list);
			root.put("domainName", domainName);
			root.put("tableName", tableName);
			
			root.put("databaseName", databaseName);
			root.put("isDateType", true);// 实现方式待优化
			root.put("tableDdl", tableDdl.getCreateTable());
			
			root.put("generateTime", new Date().toString());
			root.put("packageName", packageName);
			
			// TODO 路径
			File file = new File(projectUrl+domainName + ".java");
			if (!file.exists()) {
				// System.out.println("file exist");
				file.createNewFile();
			}
			Writer out = new BufferedWriter(new FileWriter(file));
			template.process(root, out);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
