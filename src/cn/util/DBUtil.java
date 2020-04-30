package cn.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//���ݿ�����ࣨͨ�õ����ݲ���������
public class DBUtil {
	
	private static final String URL = "jdbc:mysql://localhost:3306/javaweb?serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PWD = "123456";
	
	public static Connection connection = null;
	public static Statement stm = null;
	public static PreparedStatement pstm = null;
	public static ResultSet rs = null;
	
	//��ɾ�ģ����£�
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
	
	//ͨ�õĲ��ң�����ֵ��һ������ResultSet(static rs)
	public static void executeQuery(String sql,Object[] params){
		//��Ϊ��Ҫʹ��ResultSet���������еĹر��赽����ĵ��÷��������йر�(rs,pstm)---ǧ��ע��
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
	
	//��ѯ����������sql��ҳ���֣�
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
	
	//����������ѯ����������sql��ҳ���֣�
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
		//ͨ�������ȡ����
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(URL,USER,PWD);
	}
	
	public static void getPreparedStatement(String sql,Object[] params) throws ClassNotFoundException, SQLException {
		//����PreparedStatement�������������pstm
		pstm = getConnection().prepareStatement(sql);
		if(params!=null) {
			for(int i=0;i<params.length;i++) {
				pstm.setObject(i+1, params[i]);
			}
		}
	}
	
	public static void closeAll() {
		//�ر���������
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
