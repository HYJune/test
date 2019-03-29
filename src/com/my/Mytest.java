package com.my;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.lang.String;

import com.hyj.mapping.MysqlMapping;
import com.hyj.mapping.SQLConnection;
import com.hyj.mapping.SqlClass;

public class Mytest {

	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		Student stu = new Student();
		stu.setId(1002);
		stu.setName("hyj");
		Date date = new Date(20190101);
		stu.setBirthday(date);
		stu.setPhone("123456");
		stu.setSex(false);
		
		Exam exam =new Exam();
		exam.setId(1001);
		exam.setName("hyj");
		exam.setScore(100);
		
		String url = "jdbc:mysql://127.0.0.1:3306/school";
		String user = "root";
		String password = "";
		SQLConnection conn = new SQLConnection(url,user,password);
		try {
			conn.insert(exam);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
		
		
	}

}
