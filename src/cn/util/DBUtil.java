package cn.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//数据库帮助类（通用的数据操作方法）
public class DBUtil {
	
	private static final String URL = "jdbc:mysql://localhost:3306/javaweb?serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PWD = "123456";
	
	public static Connection connection = null;
	public static Statement stm = null;
	public static PreparedStatement pstm = null;
	public static ResultSet rs = null;
	
	//增删改（更新）
	public static boolean executeUpdate(String sql,Object[] params) {
		boolean flag = false;
		try {
			getPreparedStatement(sql,params);
			int count = pstm.executeUpdate();
			if(count>0) {
				flag = true;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return flag;		
	}
	
	//通用的查找：返回值是一个集合ResultSet(static rs)
	public static void executeQuery(String sql,Object[] params){
		//因为需要使用ResultSet，所以所有的关闭需到具体的调用方法的类中关闭(rs,pstm)---千万注意
		try {
			getPreparedStatement(sql,params);
			rs = pstm.executeQuery();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	//查询数据总数（sql分页部分）
	public static long getTotalCount(String sql) { //sql:select count(*) from table
		long count = -1;
		try {
			getPreparedStatement(sql,null);
			rs = pstm.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return count;
	}
	
	//根据条件查询数据总数（sql分页部分）
	public static long getTotalCount(String sql,Object[] params) { 
		long count = -1;
		try {
			getPreparedStatement(sql,params);
			rs = pstm.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return count;
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		//通过反射获取连接
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(URL,USER,PWD);
	}
	
	public static void getPreparedStatement(String sql,Object[] params) throws ClassNotFoundException, SQLException {
		//处理PreparedStatement，将结果保存在pstm
		pstm = getConnection().prepareStatement(sql);
		if(params!=null) {
			for(int i=0;i<params.length;i++) {
				pstm.setObject(i+1, params[i]);
			}
		}
	}
	
	public static void closeAll() {
		//关闭所有连接
		try {
			if(rs!=null)  rs.close();
			if(stm!=null)  stm.close();
			if(pstm!=null)  pstm.close();
			if(connection!=null)  connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
