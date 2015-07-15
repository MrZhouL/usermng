package cn.itcast.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

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
	
	public List<User> findByAllUser(){
		try {
			String sql ="select * from s_user ";
			return qr.query(sql, new BeanListHandler<User>(User.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<User> findByCondition(User user){
		try {
			List<Object> params = new ArrayList<Object>();
			StringBuilder sql = new StringBuilder("select * from s_user where 1=1 ");
			
			if (user.getUsername()!=null && !user.getUsername().trim().isEmpty()) {
				sql.append("and username like ? ");
				params.add("%"+user.getUsername()+"%");
			}
			if (user.getGender()!=null && !user.getGender().trim().isEmpty()) {
				sql.append("and gender= ? ");
				params.add(user.getGender());
			}
			if (user.getEducation()!=null && !user.getEducation().trim().isEmpty()) {
				sql.append("and education= ? ");
				params.add(user.getEducation());
			}
			if (user.getIsUpload()!=null && !user.getIsUpload().trim().isEmpty()) {
				if (user.getIsUpload().equals("1")) {
					sql.append("and filepath is not null ");
				}else {
					sql.append("and filepath is null ");
				}
			}
			
			return qr.query(sql.toString(), new BeanListHandler<User>(User.class),params.toArray());
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void delUser(String uid) {
		try {
			String sql = "delete from s_user where uid=? ";
			qr.update(sql,uid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public User findByUid(String uid) {
		try {
			
			String sql = "select * from s_user where uid=? ";
			
			return qr.query(sql, new BeanHandler<User>(User.class),uid);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void addUser(User user) {
		try {
			String sql = "insert into s_user values(?,?,?,?,?,?,?,?,?,?,?,?)";
			qr.update(sql, user.getUid(), user.getUsername(),
					user.getLoginname(), user.getLoginpass(), user.getGender(),
					user.getBirthday(), user.getEducation(),
					user.getCellphone(), user.getHobby(), user.getFilepath(),
					user.getFilename(), user.getRemark());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void updateUser(User user) {

		try {
			String sql = "update s_user set username=?,loginname=?,loginpass=?,gender=?,birthday=?,education=?,cellphone=?,hobby=?,filepath=?,filename=? ,remark=? where uid=? ";
			qr.update(sql, user.getUsername(), user.getLoginname(),
					user.getLoginpass(), user.getGender(), user.getBirthday(),
					user.getEducation(), user.getCellphone(), user.getHobby(),
					user.getFilepath(), user.getFilename(), user.getRemark(),
					user.getUid());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
}

