package com.bbs.util;

import java.sql.Connection;

import com.bbs.util.DButil;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn = DButil.getInstance().getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DButil.getInstance().close(conn);
	}

}
