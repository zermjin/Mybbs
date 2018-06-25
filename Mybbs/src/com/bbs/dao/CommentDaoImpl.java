package com.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bbs.bean.BbsComment;
import com.bbs.bean.BbsCommentEX;
import com.bbs.util.DButil;

public class CommentDaoImpl implements CommentDao{

	@Override
	public int addComment(BbsComment bc) {
		int ret=0;
		PreparedStatement ps=null;
		Connection conn=null;
		ResultSet rs=null;
		try {
			conn=DButil.getInstance().getConnection();
			String sql="insert into tab_bbs_comment(topic_or_comment_id,is_topic,content,userid,comment_time) values(?,?,?,?,now())";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, bc.getTopicOrCommentId());
			ps.setInt(2, bc.getIsTopic());//0是主体 1是贴子
			ps.setString(3, bc.getContent());
			ps.setInt(4, bc.getUserid());
			ret=ps.executeUpdate();
			
			int comm = 0 ;
			String sql2 = "SELECT MAX(id) max from tab_bbs_comment";
			ps=conn.prepareStatement(sql2);
			rs=ps.executeQuery();
			if(rs.next()){
				comm = rs.getInt("max");
			}
			int userid = 0 ;
			String sql3 = "SELECT tbt.userid user"
						+ " FROM tab_bbs_topicinfo tbt"
						+ " JOIN tab_bbs_comment tbc ON tbt.id = tbc.topic_or_comment_id"
						+ " WHERE tbc.id = ? and tbc.is_topic=0";
			ps=conn.prepareStatement(sql3);
			ps.setInt(1, comm);
			rs=ps.executeQuery();
			if(rs.next()){
				userid = rs.getInt("user");
			}
			String sql3d = "SELECT tbcf.userid user"
					+ " FROM tab_bbs_comment tbcf"
					+ " JOIN tab_bbs_comment tbc ON tbcf.id = tbc.topic_or_comment_id"
					+ " WHERE tbc.id = ? and tbc.is_topic=1";
			ps=conn.prepareStatement(sql3d);
			ps.setInt(1, comm);
			rs=ps.executeQuery();
			if(rs.next()){
				userid = rs.getInt("user");
			}
			
			String sql4 = "insert into tab_bbs_message(comment_id,user_id) VALUES (?,?)";
			ps=conn.prepareStatement(sql4);
			ps.setInt(1, comm);
			ps.setInt(2, userid);
			ret=ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DButil.getInstance().close(ps);
		DButil.getInstance().close(conn);
		return ret;
	}

	@Override
	public List<BbsCommentEX> getAllCommentByTopicId(int id) {
		List<BbsCommentEX> list=new ArrayList<BbsCommentEX>();
		BbsCommentEX bc=null;
		PreparedStatement ps=null;
		Connection conn=null;
		ResultSet rs=null;
		
		try {
			conn=DButil.getInstance().getConnection();
			String sql = "SELECT tbc.userid,tbc.is_accept,tbu.head_url, tbc.content, tbc.id id, tbc.comment_time, tbu.nickname, tbc.agree_num"
						+" FROM tab_bbs_comment AS tbc"
						+" JOIN tab_bbs_userinfo AS tbu ON tbc.userid = tbu.id"
						+" WHERE tbc.topic_or_comment_id =?"
						+" AND tbc.is_topic = 0 AND tbc.state != 0"
						+" ORDER BY agree_num DESC,comment_time DESC";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			List<BbsCommentEX> commentlist=new ArrayList<BbsCommentEX>();
			
			while(rs.next()){
				bc=new BbsCommentEX();
				commentlist = getAllCommentById(rs.getInt("id"));
				bc.setIsAccept(rs.getInt("is_accept"));
				bc.setUserid(rs.getInt("userid"));
				bc.setId(rs.getInt("id"));
				bc.setCommentlist(commentlist);
				bc.setAgreeNum(rs.getInt("agree_num"));
				bc.setContent(rs.getString("content"));
				bc.setCommentTime(rs.getDate("comment_time"));
				bc.setNickname(rs.getString("nickname"));
				bc.setHead_url(rs.getString("head_url"));
				bc.setTopicOrCommentId(id);
				list.add(bc);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DButil.getInstance().close(ps);
		DButil.getInstance().close(conn);
		DButil.getInstance().close(rs);
		return list;
	}

	@Override
	public List<BbsCommentEX> selectAllById(int id) {
		List<BbsCommentEX> list=new ArrayList<BbsCommentEX>();
		BbsCommentEX bc=null;
		PreparedStatement ps=null;
		Connection conn=null;
		ResultSet rs=null;
		try {
			conn=DButil.getInstance().getConnection();
			String sql = " SELECT tab_bbs_topicinfo.id topicid, title, tab_bbs_userinfo.head_url, agree_num, tab_bbs_comment.content, comment_time"
						+ " FROM tab_bbs_comment, tab_bbs_topicinfo, tab_bbs_userinfo"
						+ " WHERE  tab_bbs_comment.userid = tab_bbs_userinfo.id"
						+ " AND tab_bbs_comment.is_topic = 0"
						+ " AND tab_bbs_comment.topic_or_comment_id = tab_bbs_topicinfo.id"
						+ " AND tab_bbs_comment.userid =? AND tab_bbs_comment.state!=0";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			while(rs.next()){
				bc=new BbsCommentEX();
				bc.setTitle(rs.getString("title"));
				bc.setAgreeNum(rs.getInt("agree_num"));
				bc.setContent(rs.getString("content"));
				bc.setHead_url(rs.getString("head_url"));
				bc.setCommentTime(rs.getDate("comment_time"));
				bc.setTopicOrCommentId(rs.getInt("topicid"));
				list.add(bc);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DButil.getInstance().close(ps);
		DButil.getInstance().close(conn);
		DButil.getInstance().close(rs);
		return list;
	}

	@Override
	public int addAgreeNum(int id,boolean flag) {
		PreparedStatement ps=null;
		Connection conn=null;
		int rel = 0;
		try {
			conn=DButil.getInstance().getConnection();
			String sql="";
			if(flag)
				sql="update tab_bbs_comment set agree_num = agree_num +1 where id=?";
			else
				sql="update tab_bbs_comment set agree_num = agree_num -1 where id=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			rel=ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DButil.getInstance().close(ps);
		DButil.getInstance().close(conn);
		return rel;
	}

	@Override
	public List<BbsCommentEX> getAllCommentById(int id) {
		List<BbsCommentEX> list=new ArrayList<BbsCommentEX>();
		BbsCommentEX bc=null;
		PreparedStatement ps=null;
		Connection conn=null;
		ResultSet rs=null;
		try {
			conn=DButil.getInstance().getConnection();
			String sql="select tbu.head_url,tbc.content,tbc.comment_time,tbu.nickname,tbc.agree_num from tab_bbs_comment as tbc join tab_bbs_userinfo as tbu on tbc.userid=tbu.id where tbc.topic_or_comment_id =? and tbc.is_topic =1 and tbc.state!=0";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			while(rs.next()){
				bc=new BbsCommentEX();
				bc.setAgreeNum(rs.getInt("agree_num"));
				bc.setContent(rs.getString("content"));
				bc.setCommentTime(rs.getDate("comment_time"));
				bc.setNickname(rs.getString("nickname"));
				bc.setHead_url(rs.getString("head_url"));
				list.add(bc);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DButil.getInstance().close(ps);
		DButil.getInstance().close(conn);
		DButil.getInstance().close(rs);
		return list;
	}

	@Override
	public List<BbsCommentEX> getMessageById(int id) {
		List<BbsCommentEX> list=new ArrayList<BbsCommentEX>();
		BbsCommentEX bc=null;
		PreparedStatement ps=null;
		Connection conn=null;
		ResultSet rs=null;
		try {
			conn=DButil.getInstance().getConnection();
			
			String sql = "SELECT tbc.is_topic,tbt.title,tbc.userid,tbu.nickname,tbc.id,tbt.id topicid,tbc.comment_time"
						+ " FROM tab_bbs_message tbm"
						+ " 	JOIN tab_bbs_comment tbc ON tbc.id = tbm.comment_id"
						+ "	JOIN tab_bbs_topicinfo tbt ON tbt.id = tbc.topic_or_comment_id"
						+ "	JOIN tab_bbs_userinfo tbu ON tbc.userid = tbu.id"
						+ " WHERE tbm.user_id=? and tbm.state=0 and tbc.is_topic = 0";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			while(rs.next()){
				bc=new BbsCommentEX();
				bc.setId(rs.getInt("id"));
				bc.setNickname(rs.getString("nickname"));
				bc.setTitle(rs.getString("title"));
				bc.setUserid(rs.getInt("userid"));
				bc.setTopicOrCommentId(rs.getInt("topicid"));
				bc.setCommentTime(rs.getDate("comment_time"));
				bc.setIsTopic(rs.getInt("is_topic"));
				list.add(bc);
			}
			String sql2 = "SELECT tbc.is_topic,tbt.title,tbc.userid,tbu.nickname,tbc.id,tbt.id topicid,tbc.comment_time"
					+ " FROM tab_bbs_message tbm"
					+ " 	JOIN tab_bbs_comment tbc ON tbc.id = tbm.comment_id"
					+ "	JOIN tab_bbs_comment tbcf ON tbcf.id = tbc.topic_or_comment_id"
					+ "	JOIN tab_bbs_topicinfo tbt ON tbt.id = tbcf.topic_or_comment_id"
					+ "	JOIN tab_bbs_userinfo tbu ON tbc.userid = tbu.id"
					+ " WHERE tbm.user_id=? and tbm.state=0 and tbc.is_topic = 1";
			ps=conn.prepareStatement(sql2);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			while(rs.next()){
				bc=new BbsCommentEX();
				bc.setId(rs.getInt("id"));
				bc.setNickname(rs.getString("nickname"));
				bc.setTitle(rs.getString("title"));
				bc.setUserid(rs.getInt("userid"));
				bc.setTopicOrCommentId(rs.getInt("topicid"));
				bc.setCommentTime(rs.getDate("comment_time"));
				bc.setIsTopic(rs.getInt("is_topic"));
				list.add(bc);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DButil.getInstance().close(ps);
		DButil.getInstance().close(conn);
		DButil.getInstance().close(rs);
		return list;
	}

	@Override
	public int deleteMessageById(int id) {
		PreparedStatement ps=null;
		Connection conn=null;
		int rel = 0;
		try {
			conn=DButil.getInstance().getConnection();
			String sql="Update tab_bbs_message set state=1 WHERE tab_bbs_message.comment_id=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			rel=ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DButil.getInstance().close(ps);
		DButil.getInstance().close(conn);
		return rel;
	}

	@Override
	public int deleteAllMessageById(int id) {
		PreparedStatement ps=null;
		Connection conn=null;
		int rel = 0;
		try {
			conn=DButil.getInstance().getConnection();
			String sql="Update tab_bbs_message set state=1 WHERE tab_bbs_message.user_id=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			rel=ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DButil.getInstance().close(ps);
		DButil.getInstance().close(conn);
		return rel;
	}

	@Override
	public int deleteCommentById(int id) {
		PreparedStatement ps=null;
		Connection conn=null;
		int rel = 0;
		try {
			conn=DButil.getInstance().getConnection();
			String sql="Update tab_bbs_comment set state=0 WHERE tab_bbs_comment.id=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			rel=ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DButil.getInstance().close(ps);
		DButil.getInstance().close(conn);
		return rel;
	}

	@Override
	public int acceptCommentById(int id,int uid,int kiss) {
		PreparedStatement ps=null;
		Connection conn=null;
		int rel = 0;
		try {
			conn=DButil.getInstance().getConnection();
			String sql="Update tab_bbs_comment set is_accept=1 WHERE tab_bbs_comment.id=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			rel=ps.executeUpdate();
			String sql2="Update tab_bbs_userinfo set kiss_num=(kiss_num+?) WHERE tab_bbs_userinfo.id=?";
			ps=conn.prepareStatement(sql2);
			ps.setInt(1, kiss);
			ps.setInt(2, uid);
			rel=ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DButil.getInstance().close(ps);
		DButil.getInstance().close(conn);
		return rel;
	}

	@Override
	public int editComment(String cont, int id) {
		PreparedStatement ps=null;
		Connection conn=null;
		int rel = 0;
		try {
			conn=DButil.getInstance().getConnection();
			String sql="Update tab_bbs_comment set content=? WHERE tab_bbs_comment.id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, cont);
			ps.setInt(2, id);
			rel=ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DButil.getInstance().close(ps);
		DButil.getInstance().close(conn);
		return rel;
	}



}
