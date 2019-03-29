package com.hyj.mapping;

import java.util.ArrayList;
import java.util.List;
/**
 * 表与类的映射关心
 * @author 45900264
 *@param name className
 *@param table tableName
 */
public class SqlClass {
	public String name;//类名
	public String table;//表名	
	public Property autoIncrement;//自增
	
	//列的集合
	public List<Property> properties = new ArrayList<>();
	//数据库的列属性
	public static class Property{
		public String name;//属性名需与列名相同
		public String type;//映射成java数据类型
		public boolean generated=false; //是否为主键
	}
}
