package cn.itcast.user.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.jdbc.utils.JdbcUtils;
import cn.itcast.user.domain.User;

/**
 * UserDao
 * @author Administrator
 */
/*
 * 使用c3p0连接池
 */
public class UserDao {
	private QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
	
	public User findByNameAndPass(String loginname, String loginpass) {
		try {
			String sql = "select * from s_user where loginname=? and loginpass=?";
			return qr.query(sql, new BeanHandler<User>(User.class), loginname, loginpass);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
