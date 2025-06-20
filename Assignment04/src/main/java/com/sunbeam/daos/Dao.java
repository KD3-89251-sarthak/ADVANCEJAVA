package com.sunbeam.daos;

import java.sql.Connection;

import com.sunbeam.util.DbUtil;

public class Dao implements AutoCloseable {

	protected Connection con;
	
	public Dao() throws Exception{
		
		con = DbUtil.getConnection();
		
	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		if(con!= null)
			con.close();
		
	}
	
	
	
	
}
