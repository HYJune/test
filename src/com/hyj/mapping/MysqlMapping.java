package com.hyj.mapping;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class MysqlMapping {
	List<SqlClass> classes = new ArrayList<>();
	Map<String, SqlClass> classes_map1 = new HashMap<>();
	Map<String, SqlClass> classes_map2 = new HashMap<>();
	private MysqlMapping(){
		
	}
	public static MysqlMapping i;
	
	//静态加载
	static{
		i=new MysqlMapping();
		try{
			i.load();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void load() throws Exception{
		// TODO Auto-generated method stub
		
		SAXReader xmlReader = new SAXReader();
		try {
//			InputStream inputStream = getClass().getResourceAsStream("com.my.hyj-mapping.xml"); 
			File parentPath = new File("");
			String childPath ="src/hyj-mapping.xml";
			File file =new File(parentPath.getAbsolutePath(),childPath);
			Document document = xmlReader.read(file);
			if(document==null)System.out.println("can not read "+file.getPath());
			Element root = document.getRootElement();//最外层元素
//			System.out.println(root.getName());
//			inputStream.close();
			//保存类元素集合
			List<Element> xmlclasses = new ArrayList<>();
			xmlclasses =root.elements("class");
			for(Element xmlclass:xmlclasses){
				SqlClass sqlc = new SqlClass();
				
				//获取类的类名和mysql数据库的表名
				sqlc.name = xmlclass.attributeValue("name");
				sqlc.table = xmlclass.attributeValue("table");
				//保存列名元素集合
				List<Element> xmlProps = new ArrayList<>();
				xmlProps =xmlclass.elements("property");
				for(Element xmlprop:xmlProps ){
					SqlClass.Property prop = new SqlClass.Property();
					
					//获取列的属性
					prop.name = xmlprop.attributeValue("name");//colName
					prop.type = xmlprop.attributeValue("type");//colType
					prop.generated ="ture".equals(xmlprop.attributeValue("generated"));
					sqlc.properties.add(prop);
					
					if(prop.generated){
						sqlc.autoIncrement = prop;
					}
				}
				classes.add(sqlc);
				classes_map1.put(sqlc.name, sqlc);
				classes_map2.put(sqlc.table, sqlc);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public SqlClass findClass(String className,String tableName){
		if(className!= null){
			SqlClass r = classes_map1.get(className);
			if(r!=null)return r;
		}
		if(tableName!= null){
			SqlClass r = classes_map2.get(tableName);
			if(r!=null)return r;
		}
		return null;
	}
}
