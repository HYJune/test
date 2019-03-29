package com.hyj.mapping;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class String2other {
	public static Object getOtherdate(String str,Class cls){
		Object obj =null;
		switch (cls.getSimpleName()) {
		case "int":				
			obj = Integer.valueOf(str).intValue();			
			break;
		case "boolean":
			obj = Boolean.valueOf(str);
			break;
		case "byte":
			obj =(byte) str.charAt(0);
			break;
		case "char":
			obj = str.charAt(0);
		case "String":
			obj = str;
			break;
		case "Integer":
			obj = Integer.valueOf(str);
			break;
		case "Date":
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				obj = (Date)sdf.parse(str);
//				obj =sdf.format(obj);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		default:
			obj =str;
			break;
		}
		return obj;
	}
	
	public static String getString(Object obj){
		String str = null;
		switch(obj.getClass().getSimpleName())
		{
		case "boolean":
		case "Boolean":
			if((boolean) obj){
				str ="1";
			}else{
				str="0";
			};
			break;
		default:
			str=String.valueOf(obj);
			break;
		}
		
		return str;
		
	}
//	public static void main(String args[]){
//		
//	
//		System.out.println(getOtherdate("1989-03-03", Date.class));
//	}
}
