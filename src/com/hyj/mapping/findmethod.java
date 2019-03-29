package com.hyj.mapping;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class findmethod {
	//更据给定的名字colname和参数类型找到对应的set方法
	public static Method setMethod(Class cls ,String colname,Class[] paramters) throws Exception{
		char upchar = colname.toUpperCase().charAt(0);
		colname = colname.substring(1);
		String setname = "set"+upchar+colname;
//		System.out.println("setMethod:"+setName);
		Method setmethod = cls.getMethod(setname,paramters);
		return setmethod;
	}
	public static Method getMethod(Class cls,String colname) throws Exception{
		char upchar = colname.toUpperCase().charAt(0);
		colname = colname.substring(1);
		String getname = "get"+upchar+colname;
//		System.out.println("getMethod:"+getName);
		Method getmethod = cls.getMethod(getname,new Class[]{});
		return getmethod;
	}
}
