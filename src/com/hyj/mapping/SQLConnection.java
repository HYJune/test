package com.hyj.mapping;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.my.Student;

public class SQLConnection {
	Connection connection;
	public SQLConnection(String url, String user, String password) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("正在连接。。。");
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("连接成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * 把pojo类插入到数据库中。构造数据库插入语句：
	 * INSERT INTO `school`.`student` 
	 * (`id`, `name`, `sex`, `phone`, `birthday`) 
	 * VALUES 
	 * ('3', '黄', '1', '1234564', '2019-03-17'); 
	 */
	public void insert(Object pojo) throws Exception {
		
		//1.从配置表中查找pojo类的对应关系,获取属性名称(和数据库中的列名对应)
		Class cls = pojo.getClass();
		SqlClass sqlClass = MysqlMapping.i.findClass(cls.getName(), null);
		
		List<String> colName =new ArrayList<>();
		for(SqlClass.Property e:sqlClass.properties){
		
			colName.add(e.name);
			
		}
		
		//2.获取pojo实例对象的值,并转化成String类型
		List<String> value = new ArrayList<>();
		Method method = null;
		for(String e:colName){
			method = findmethod.getMethod(cls, e);
			Object obj = method.invoke(pojo);
			value.add(String2other.getString(obj));
		}		
		//3.构造sql语句
		String sql="INSERT INTO "+sqlClass.table+nameStr(colName)+"VALUES"+valueStr(value);
		System.out.println(sql);
		if(connection==null){
			System.out.println("没有连接数据库");
		}else{
			Statement stam = connection.createStatement();
			stam.execute(sql);
		}
		
		
	}
//	(`id`, `name`, `sex`, `phone`, `birthday`) 
	public String valueStr(List<String>  list){
		String str ="(";
		int length=list.size();
		for(int i=0;i<length;i++){
			if(i<length-1){
				str=str+"'"+list.get(i)+"',";
			}else{
				str=str+"'"+list.get(i)+"')";
			}
		}
		return str;
	}
	public String nameStr(List<String>  list){
		String str ="(";
		int length=list.size();
		for(int i=0;i<length;i++){
			if(i<length-1){
				str=str+"`"+list.get(i)+"`,";
			}else{
				str=str+"`"+list.get(i)+"`)";
			}
		}
		return str;
	}

	
	
}
