package com.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bbs.bean.BbsUser;
import com.bbs.bean.BbsUserEX;
import com.bbs.util.DButil;

public class UserDaoImpl implements UserDao {

	@Override
	public int addUser(BbsUser bu) {
		int ret = 0;
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = DButil.getInstance().getConnection();
			String sql = "insert into tab_bbs_userinfo(email,nickname,password)values(?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, bu.getEmail());
			ps.setString(2, bu.getNickname());
			ps.setString(3, bu.getPassword());
			ret = ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DButil.getInstance().close(ps);
		DButil.getInstance().close(conn);
		return ret;
	}

	@Override
	public BbsUser userLogin(String email, String password) {
		BbsUser bui = null;
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = DButil.getInstance().getConnection();
			String sql = "select * from tab_bbs_userinfo where email=? and password=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				bui = new BbsUser();
				String nickname = rs.getString("nickname");
				int id = rs.getInt("id");
				String headurl = rs.getString("head_url");
				bui.setNickname(nickname);
				bui.setId(id);
				bui.setHeadUrl(headurl);
				bui.setCity(rs.getString("city"));
				bui.setEmail(rs.getString("email"));
				bui.setKissNum(rs.getInt("kiss_num"));
				bui.setSex(rs.getInt("sex"));
				bui.setPassword(rs.getString("password"));
				bui.setSignName(rs.getString("sign_name"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DButil.getInstance().close(ps);
		DButil.getInstance().close(conn);
		DButil.getInstance().close(rs);
		return bui;
	}

	@Override
	public int updateUser(BbsUser bu) {
		int ret = 0;
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = DButil.getInstance().getConnection();
			String sql = "update tab_bbs_userinfo set email=?,nickname=? ,city=?,sex=?,sign_name=? where id=? ";
			ps = conn.prepareStatement(sql);
			// email username sex city sign
			ps.setString(1, bu.getEmail());
			ps.setString(2, bu.getNickname());
			ps.setString(3, bu.getCity());
			ps.setInt(4, bu.getSex());
			ps.setString(5, bu.getSignName());
			ps.setInt(6, bu.getId());
			ret = ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DButil.getInstance().close(ps);
		DButil.getInstance().close(conn);
		return ret;
	}

	@Override
	public int updatePassword(int id, String nowPass, String newPass) {
		int ret = 0;
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = DButil.getInstance().getConnection();
			String sql = "update tab_bbs_userinfo set password=? where id=? and password=? ";
			ps = conn.prepareStatement(sql);
			// email username sex city sign
			ps.setString(1, newPass);
			ps.setInt(2, id);
			ps.setString(3, nowPass);
			ret = ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DButil.getInstance().close(ps);
		DButil.getInstance().close(conn);
		return ret;
	}

	@Override
	public boolean IsUserExist(String email) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		boolean bRet = false;
		try {
			con = DButil.getInstance().getConnection();
			String strSql = "select * from tab_bbs_userinfo " + "where email = ?";
			ps = con.prepareStatement(strSql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			bRet = rs.next();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DButil.getInstance().close(rs);
		DButil.getInstance().close(con);
		DButil.getInstance().close(ps);

		return bRet;
	}

	@Override
	public int saveUserHeaderPicPath(int id, String url) {
		int ret = 0;
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = DButil.getInstance().getConnection();
			String sql = "update tab_bbs_userinfo set head_url=? where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, url);
			ps.setInt(2, id);
			ret = ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DButil.getInstance().close(ps);
		DButil.getInstance().close(conn);
		return ret;
	}

	@Override
	public BbsUser selectUser(int id) {
		BbsUser bt = null;
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = DButil.getInstance().getConnection();
			String sql = "select * from tab_bbs_userinfo where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bt = new BbsUser();
				bt.setId(rs.getInt("id"));
				bt.setNickname(rs.getString("nickname"));
				bt.setHeadUrl(rs.getString("head_url"));
				bt.setCity(rs.getString("city"));
				bt.setEmail(rs.getString("email"));
				bt.setKissNum(rs.getInt("kiss_num"));
				bt.setSex(rs.getInt("sex"));
				bt.setPassword(rs.getString("password"));
				bt.setSignName(rs.getString("sign_name"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DButil.getInstance().close(rs);
		DButil.getInstance().close(ps);
		DButil.getInstance().close(conn);

		return bt;
	}

	@Override
	public List<BbsUserEX> getHotUser() {
		List<BbsUserEX> list = new ArrayList<BbsUserEX>();
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = DButil.getInstance().getConnection();
			String sql = "select tbu.*,COUNT(tbc.id) co"
					+ " from tab_bbs_userinfo AS tbu"
					+ " JOIN tab_bbs_comment AS tbc ON tbc.userid=tbu.id"
					+ " GROUP BY tbu.id"
					+ " ORDER BY co DESC LIMIT 12";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				BbsUserEX bt = new BbsUserEX();
				bt.setId(rs.getInt("id"));
				bt.setNickname(rs.getString("nickname"));
				bt.setHeadUrl(rs.getString("head_url"));
				bt.setCity(rs.getString("city"));
				bt.setEmail(rs.getString("email"));
				bt.setKissNum(rs.getInt("kiss_num"));
				bt.setSex(rs.getInt("sex"));
				bt.setPassword(rs.getString("password"));
				bt.setSignName(rs.getString("sign_name"));
				bt.setAnswer(rs.getInt("co"));
				list.add(bt);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DButil.getInstance().close(rs);
		DButil.getInstance().close(ps);
		DButil.getInstance().close(conn);

		return list;
	}

}
