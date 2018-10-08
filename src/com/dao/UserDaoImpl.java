package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.model.User;

public class UserDaoImpl implements UserDao{
	
	private JdbcTemplate jdbcTemplate;
	
	public UserDaoImpl(DataSource dataSource) {
		jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	@Override
	public User get(String username) {
		String sql="select * from USERS where USER_NAME= ?";
		return jdbcTemplate.query(sql, new Object[]{username}, new ResultSetExtractor<User>(){
			
			@Override
			public User extractData(ResultSet rs) throws SQLException {
				if(rs.next()) {
					User user=new User();
					user.setUserID(rs.getInt("USER_ID"));
					user.setUserName(rs.getString("USER_NAME"));
					user.setPassword(rs.getString("PASSWORD"));
					return user;
				}
				return null;
			}
		});
	}

}
