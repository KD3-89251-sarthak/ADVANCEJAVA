package com.sunbeam.daos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sunbeam.pojos.User;

public class UserDaoImpl extends Dao implements UserDao {
	private PreparedStatement stmtFindByEmail;
	private PreparedStatement stmtFindByID;
	private PreparedStatement stmtfindAll;
	private PreparedStatement stmtSave;
	private PreparedStatement stmtUpdate;
	private PreparedStatement stmtDelete;
	
	
	
	public UserDaoImpl() throws Exception{
		super();
		stmtFindByEmail = con.prepareStatement("SELECT * FROM users where email = ?");
		stmtFindByID = con.prepareStatement("SELECT * FROM users where id = ?");
		stmtfindAll = con.prepareStatement("SELECT * FROM users");
		stmtSave = con.prepareStatement("INSERT INTO USERS(first_name, last_name, email, password, dob, status, role) VALUES(?,?,?,?,?,?,?)");
		stmtUpdate = con.prepareStatement("UPDATE users set first_name = ? , last_name=?, email = ?, password = ?, dob = ?, status = ?, role = ? WHERE id = ?");
		stmtDelete = con.prepareStatement("DELETE FROM users WHERE id = ?");
		
		
		
	}

	@Override
	public User findByEmail(String email) throws Exception {
		stmtFindByEmail.setString(1, email);
		try(ResultSet rs = stmtFindByEmail.executeQuery()){
			if(rs.next()) {
				int userId = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				email = rs.getString("email");
				String password = rs.getString("password");
				Date birth = rs.getDate("dob");
				boolean status = rs.getBoolean("status");
				String role = rs.getString("role");
				User user = new User(userId, firstName, lastName, email, password, birth, status, role);
				return user;
						
			}
			
		}
		
		
		return null;
	}

	@Override
	public User findById(int userId) throws Exception {
		stmtFindByID.setInt(1, userId);
		try(ResultSet rs = stmtFindByID.executeQuery()){
			
			if(rs.next()) {
				userId = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				Date birth = rs.getDate("dob");
				boolean status = rs.getBoolean("status");
				String role = rs.getString("role");
				User user = new User(userId, firstName, lastName, email, password, birth, status, role);
				return user;
			}
			
		}
		
		
		
		return null;
	}
	
	
	@Override 
	public void close() throws Exception{
		stmtDelete.close();
		stmtUpdate.close();
		stmtSave.close();
		stmtfindAll.close();
		stmtFindByID.close();
		stmtFindByEmail.close();
		super.close();
		
	}

	@Override
	public List<User> findAll() throws Exception {
		List<User> list = new ArrayList<>();
		try(ResultSet rs = stmtfindAll.executeQuery()){
			while(rs.next()) {
				
				int userId = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				Date birth = rs.getDate("dob");
				boolean status = rs.getBoolean("status");
				String role = rs.getString("role");
				User user = new User(userId, firstName, lastName, email, password, birth, status, role);
				list.add(user);
				
				
			}
			
			
		}
		
		return list;
	}

	@Override
	public int save(User u) throws Exception {
		
		stmtSave.setString(1, u.getFirstName());
		stmtSave.setString(2, u.getLastName());
		stmtSave.setString(3, u.getEmail());
		stmtSave.setString(4, u.getPassword());
		stmtSave.setDate(5, u.getBirth());
		stmtSave.setBoolean(6, u.isStatus());
		stmtSave.setString(7, u.getRole());
		int cnt = stmtSave.executeUpdate();
		return cnt;
		
	}

	@Override
	public int deleteById(int userId) throws Exception {
		stmtDelete.setInt(1, userId);
		int cnt = stmtDelete.executeUpdate();
		
		
		
		return cnt;
	}

	@Override
	public int update(User u) throws Exception {
		
		stmtUpdate.setString(1, u.getFirstName());
		stmtUpdate.setString(2, u.getLastName());
		stmtUpdate.setString(3, u.getEmail());
		stmtUpdate.setString(4, u.getPassword());
		stmtUpdate.setDate(5, u.getBirth());
		stmtUpdate.setBoolean(6, u.isStatus());
		stmtUpdate.setString(7, u.getRole());
		stmtUpdate.setInt(8, u.getId());
		int cnt = stmtUpdate.executeUpdate();
		return cnt;
		
	
	}

	
	

}
