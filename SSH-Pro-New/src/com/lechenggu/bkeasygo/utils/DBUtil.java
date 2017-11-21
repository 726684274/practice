package com.lechenggu.bkeasygo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 访问数据库工具类
 * 本工具类的方法不是静态的! 为了多线程时 不共用
 * 通用的增删改和查询方法中的  finally 关闭  完全可以 抽出来做个方法. 但是为了考虑多线程时及时关闭连接 就不抽出去了!
 * 三个方法": 1 获取连接  2 通用的增删改  3通用的查询
 * @author RockyT420
 *
 * final修饰  不写也行  写上就是不让被继承!
 */
public final class DBUtil {
	// 参数们
	private String user = "shop";
	private String password = "123";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	
	/**
	 * 获取连接的方法
	 * @return 正常得到连接对象   异常 得到 null
	 */
	public Connection getCon(){
		Connection con = null;
		//1注册驱动
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//3 驱动管理类获取连接
			con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	/**
	 * 通用的怎删改
	 * @param sql   sql模板
	 * @param obj   sql模板中?的取值的数组
	 * @return 本次update影响的行数
	 */
	public int executeUpdate(String sql,Object[] obj){
		Connection con = this.getCon();
		PreparedStatement pstmt = null;
		try {
			// 按照传入的sql模板  得到 预编译声明!
			pstmt = con.prepareStatement(sql);
			// 设置?的值
			// 判断sql 模板没有?  那么obj就传入null
			if(obj!=null){
				for (int i = 0; i < obj.length; i++) {
					pstmt.setObject((i+1), obj[i]);  // i+1  因为  ? 在模板中 下标是1
				}
			}
			// 执行
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return -1;
	}
	
	/**
	 * 通用的查询方法
	 * @param sql  模板
	 * @param obj  ?的参数 数组
	 * @return 成功则返回 结果集数据 组装成的  List<Map<String, String>>   失败 返回一个null
	 */
	public List<Map<String, String>> executeQuery(String sql,Object[] obj){
		
		List<Map<String, String>> ls = new ArrayList<Map<String, String>>();
		Connection con = this.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 按照传入的sql模板  得到 预编译声明!
			pstmt = con.prepareStatement(sql);
			// 设置?的值
			// 判断sql 模板没有?  那么obj就传入null
			if(obj!=null){
				for (int i = 0; i < obj.length; i++) {
					pstmt.setObject((i+1), obj[i]);  // i+1  因为  ? 在模板中 下标是1
				}
			}
			// 执行
			rs = pstmt.executeQuery();
			// 讲rs中的数据  转存到ls中 并返回
			// 使用结果集元数据 获取 结果集里面的 列数 
			//ResultSetMetaData 有关 ResultSet 中列的名称和类型的信息。 
			ResultSetMetaData rsmd = rs.getMetaData();
			// 列数 
			int counts = rsmd.getColumnCount();
			// 获取结果集中的一条条数据
			while(rs.next()){
				//一条数据 存一个map
				Map<String, String> map = new HashMap<String, String>();
				for (int i = 1; i <=counts; i++) {
					//获取第i列 列名
					String key = rsmd.getColumnName(i);
					//获取第i列 列值
					String value = rs.getString(i);
					//转存map
					map.put(key, value);
				}
				// 将map 加入 ls
				ls.add(map);
			}
			return ls;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
