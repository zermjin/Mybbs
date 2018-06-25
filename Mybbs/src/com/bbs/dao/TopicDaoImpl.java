package com.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bbs.bean.BbsTopic;
import com.bbs.bean.BbsTopicEX;
import com.bbs.util.DButil;

public class TopicDaoImpl implements TopicDao {

	@Override
	public List<BbsTopicEX> selectTopic() {
		List<BbsTopicEX> list = new ArrayList<BbsTopicEX>();
		BbsTopicEX bt = null;
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = DButil.getInstance().getConnection();
			String sql = "SELECT tbt.userid,tbt.is_top, tbt.id, tbt.title, tbt.createtime, tbt.view_count, tbt.category_id,"
					+ " tbc.nickname category_name, tbt.is_good, tbt.is_end, tbu.nickname nickname, tbu.head_url "
					+ " FROM tab_bbs_topicinfo AS tbt "
					+ " JOIN tab_bbs_userinfo AS tbu ON tbt.userid = tbu.id "
					+ " JOIN tab_bbs_category AS tbc ON tbt.category_id = tbc.id"
					+ " WHERE tbt.state!=0"
					+ " ORDER BY createtime desc ";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				bt = new BbsTopicEX();
				bt.setUserid(rs.getInt("userid"));
				bt.setId(rs.getInt("id"));
				bt.setTitle(rs.getString("title"));
				bt.setNickname(rs.getString("nickname"));
				bt.setCreatetime(rs.getDate("createtime"));
				bt.setCategory_name(rs.getString("category_name"));
				bt.setCategoryId(rs.getInt("category_id"));
				bt.setViewCount(rs.getInt("view_count"));
				bt.setHead_url(rs.getString("head_url"));
				bt.setIsGood(rs.getInt("is_good"));
				bt.setIsEnd(rs.getInt("is_end"));
				bt.setIsTop(rs.getInt("is_top"));
				list.add(bt);
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
	public int addTopic(BbsTopic bt) {
		int ret = 0;
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = DButil.getInstance().getConnection();
			String sql = "insert into tab_bbs_topicinfo(title,content,createtime,category_id,userid,kiss)values(?,?,now(),?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, bt.getTitle());
			ps.setString(2, bt.getContent());
			// java.sql.Date<->java.util.Date 之间的转换
			//ps.setDate(3, SqlDate.toDate(bt.getCreatetime()));
			ps.setInt(3, bt.getCategoryId());
			ps.setInt(4, bt.getUserid());
			ps.setInt(5, bt.getKiss());
			ret = ps.executeUpdate();
			
			String sql2="UPDATE tab_bbs_userinfo SET kiss_num = (kiss_num - ?) WHERE id = ?";
			ps = conn.prepareStatement(sql2);
			ps.setInt(1, bt.getKiss());
			ps.setInt(2, bt.getUserid());
			ret = ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DButil.getInstance().close(ps);
		DButil.getInstance().close(conn);
		return ret;
	}

	/**
	 * 通过TopicID选择Topic
	 */
	@Override
	public BbsTopicEX getTopicByID(int id) {
		BbsTopicEX bt = null;
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = DButil.getInstance().getConnection();
			String sql = " SELECT COUNT(tbc.id) comm_total," + "tbt.is_top, tbt.userid, tbu.head_url,tbt.id,tbt.title,tbt.content,tbt.createtime,tbt.kiss,"
					+ " tbt.view_count,tbt.category_id,tbt.is_good,tbt.is_end,tbu.nickname "
					+ " FROM tab_bbs_topicinfo AS tbt " + " JOIN tab_bbs_userinfo AS tbu ON tbt.userid = tbu.id "
					+ " LEFT JOIN tab_bbs_comment AS tbc ON tbt.id = tbc.topic_or_comment_id"
					+ " WHERE tbt.id =? AND tbt.state!=0 "
					+ " GROUP BY tbt.id"
					+ " ORDER BY tbt.createtime DESC";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			// System.out.println(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				bt = new BbsTopicEX();
				bt.setId(id);
				
				bt.setUserid(rs.getInt("userid"));
				bt.setTitle(rs.getString("title"));
				bt.setNickname(rs.getString("nickname"));
				bt.setContent(rs.getString("content"));
				bt.setHead_url(rs.getString("head_url"));
				bt.setCreatetime(rs.getDate("createtime"));
				bt.setCategoryId(rs.getInt("category_id"));
				bt.setViewCount(rs.getInt("view_count"));
				bt.setIsGood(rs.getInt("is_good"));
				bt.setIsEnd(rs.getInt("is_end"));
				bt.setIsTop(rs.getInt("is_top"));
				bt.setKiss(rs.getInt("kiss"));
				bt.setCommentTotal(rs.getInt("comm_total"));
				// bt.setCommentTotal(rs.getInt("count_total"));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// System.out.println("Exception");
			e.printStackTrace();
		}
		DButil.getInstance().close(ps);
		DButil.getInstance().close(conn);
		DButil.getInstance().close(rs);
		//System.out.println("bt:" + bt);
		return bt;
	}

	@Override
	public int insertTopicViewCounts(int id) {
		int ret = 0;
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = DButil.getInstance().getConnection();
			String sql = "update tab_bbs_topicinfo set view_count = view_count +1 where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
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
	/**
	 * 通过UserID选择Topic/Comment
	 */
	public List<BbsTopicEX> selectTopicById(int id) {
		List<BbsTopicEX> list = new ArrayList<BbsTopicEX>();
		BbsTopicEX bt = null;
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = DButil.getInstance().getConnection();
			String sql = " SELECT tbt.id, tbt.title, tbu.nickname, tbt.createtime, tbt.view_count"
						+ " FROM tab_bbs_topicinfo AS tbt, tab_bbs_userinfo AS tbu"
						+ " WHERE tbt.userid = tbu.id AND tbt.userid =? AND tbt.state!=0 "
						+ " ORDER BY tbt.createtime DESC";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bt = new BbsTopicEX();
				bt.setId(rs.getInt("id"));
				bt.setTitle(rs.getString("title"));
				bt.setCreatetime(rs.getDate("createtime"));
				bt.setViewCount(rs.getInt("view_count"));
				bt.setNickname(rs.getString("nickname"));
				list.add(bt);
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
	public List<BbsTopicEX> getPagedTopics(int pageSize, int pageIndex) {
		List<BbsTopicEX> list = new ArrayList<BbsTopicEX>();
		BbsTopicEX bt = null;
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = DButil.getInstance().getConnection();
			String sql = "select tbt.userid,tbt.is_top,tbt.id,tbt.title,tbt.createtime,tbt.view_count,tbt.category_id,tbt.is_good,tbt.is_end,tbu.nickname,tbu.head_url from tab_bbs_topicinfo as tbt join tab_bbs_userinfo as tbu on tbt.userid=tbu.id WHERE tbt.state != 0 ORDER BY tbt.createtime DESC limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pageSize * (pageIndex - 1));
			ps.setInt(2, pageSize);
			rs = ps.executeQuery();
			while (rs.next()) {
				bt = new BbsTopicEX();
				bt.setId(rs.getInt("id"));
				bt.setUserid(rs.getInt("userid"));
				bt.setTitle(rs.getString("title"));
				bt.setNickname(rs.getString("nickname"));
				bt.setCreatetime(rs.getDate("createtime"));
				bt.setCategoryId(rs.getInt("category_id"));
				bt.setViewCount(rs.getInt("view_count"));
				bt.setHead_url(rs.getString("head_url"));
				bt.setIsGood(rs.getInt("is_good"));
				bt.setIsEnd(rs.getInt("is_end"));
				bt.setIsTop(rs.getInt("is_top"));
				list.add(bt);
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
	public int getTotalCount() {
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = DButil.getInstance().getConnection();
			String sql = "select count(*) as totalnum from tab_bbs_topicinfo as tbt join tab_bbs_userinfo as tbu on tbt.userid=tbu.id WHERE tbt.state != 0";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt("totalnum");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DButil.getInstance().close(ps);
		DButil.getInstance().close(conn);
		DButil.getInstance().close(rs);
		return count;
	}

	@Override
	public int updateTopic(BbsTopic bt,int oldkiss) {
		int ret = 0;
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = DButil.getInstance().getConnection();
			String sql = "UPDATE tab_bbs_topicinfo "
						+ " SET title=?,content=?,createtime=NOW(),category_id=?,kiss=?"
						+ " WHERE id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, bt.getTitle());
			ps.setString(2, bt.getContent());
			ps.setInt(3, bt.getCategoryId());
			ps.setInt(4, bt.getKiss());
			ps.setInt(5, bt.getId());
			ret=ps.executeUpdate();
			
			String sql2="UPDATE tab_bbs_userinfo SET kiss_num = (kiss_num + ?)-? WHERE id = ?";
			ps = conn.prepareStatement(sql2);
			ps.setInt(1, oldkiss);
			ps.setInt(2, bt.getKiss());
			ps.setInt(3, bt.getUserid());
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
	public int deleteTopicById(int id) {
		PreparedStatement ps=null;
		Connection conn=null;
		int rel = 0;
		try {
			conn=DButil.getInstance().getConnection();
			String sql="Update tab_bbs_topicinfo set state=0 WHERE tab_bbs_topicinfo.id=?";
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
	public int goodTopicById(int id,boolean flag) {
		PreparedStatement ps=null;
		Connection conn=null;
		int rel = 0;
		try {
			conn=DButil.getInstance().getConnection();
			String sql="";
			if(flag)
				sql="Update tab_bbs_topicinfo set is_good=1 WHERE tab_bbs_topicinfo.id=?";
			else
				sql="Update tab_bbs_topicinfo set is_good=0 WHERE tab_bbs_topicinfo.id=?";
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
	public int topTopicById(int id,boolean flag) {
		PreparedStatement ps=null;
		Connection conn=null;
		int rel = 0;
		try {
			conn=DButil.getInstance().getConnection();
			String sql="";
			if(flag)
				sql="Update tab_bbs_topicinfo set is_top=1 WHERE tab_bbs_topicinfo.id=?";
			else
				sql="Update tab_bbs_topicinfo set is_top=0 WHERE tab_bbs_topicinfo.id=?";
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
	public int endTopicById(int id) {
		PreparedStatement ps=null;
		Connection conn=null;
		int rel = 0;
		try {
			conn=DButil.getInstance().getConnection();
			String sql="Update tab_bbs_topicinfo set is_end=1 WHERE tab_bbs_topicinfo.id=?";
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
	public List<BbsTopicEX> getPagedGoodTopics(int pageSize, int pageIndex) {
		List<BbsTopicEX> list = new ArrayList<BbsTopicEX>();
		BbsTopicEX bt = null;
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = DButil.getInstance().getConnection();
			String sql = "select tbt.is_top,tbt.id,tbt.title,tbt.createtime,tbt.view_count,tbt.category_id,tbt.is_good,tbt.is_end,tbu.nickname,tbu.head_url from tab_bbs_topicinfo as tbt join tab_bbs_userinfo as tbu on tbt.userid=tbu.id WHERE tbt.state != 0 and tbt.is_good=1 ORDER BY tbt.createtime DESC limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pageSize * (pageIndex - 1));
			ps.setInt(2, pageSize);
			rs = ps.executeQuery();
			while (rs.next()) {
				bt = new BbsTopicEX();
				bt.setId(rs.getInt("id"));
				bt.setTitle(rs.getString("title"));
				bt.setNickname(rs.getString("nickname"));
				bt.setCreatetime(rs.getDate("createtime"));
				bt.setCategoryId(rs.getInt("category_id"));
				bt.setViewCount(rs.getInt("view_count"));
				bt.setHead_url(rs.getString("head_url"));
				bt.setIsGood(rs.getInt("is_good"));
				bt.setIsEnd(rs.getInt("is_end"));
				bt.setIsTop(rs.getInt("is_top"));
				list.add(bt);
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
	public List<BbsTopicEX> getPagedEndTopics(int pageSize, int pageIndex) {
		List<BbsTopicEX> list = new ArrayList<BbsTopicEX>();
		BbsTopicEX bt = null;
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = DButil.getInstance().getConnection();
			String sql = "select tbt.is_top,tbt.id,tbt.title,tbt.createtime,tbt.view_count,tbt.category_id,tbt.is_good,tbt.is_end,tbu.nickname,tbu.head_url from tab_bbs_topicinfo as tbt join tab_bbs_userinfo as tbu on tbt.userid=tbu.id WHERE tbt.state != 0 and tbt.is_end=1 ORDER BY tbt.createtime DESC limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pageSize * (pageIndex - 1));
			ps.setInt(2, pageSize);
			rs = ps.executeQuery();
			while (rs.next()) {
				bt = new BbsTopicEX();
				bt.setId(rs.getInt("id"));
				bt.setTitle(rs.getString("title"));
				bt.setNickname(rs.getString("nickname"));
				bt.setCreatetime(rs.getDate("createtime"));
				bt.setCategoryId(rs.getInt("category_id"));
				bt.setViewCount(rs.getInt("view_count"));
				bt.setHead_url(rs.getString("head_url"));
				bt.setIsGood(rs.getInt("is_good"));
				bt.setIsEnd(rs.getInt("is_end"));
				bt.setIsTop(rs.getInt("is_top"));
				list.add(bt);
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
	public List<BbsTopicEX> getPagedNotEndTopics(int pageSize, int pageIndex) {
		List<BbsTopicEX> list = new ArrayList<BbsTopicEX>();
		BbsTopicEX bt = null;
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = DButil.getInstance().getConnection();
			String sql = "select tbt.is_top,tbt.id,tbt.title,tbt.createtime,tbt.view_count,tbt.category_id,tbt.is_good,tbt.is_end,tbu.nickname,tbu.head_url from tab_bbs_topicinfo as tbt join tab_bbs_userinfo as tbu on tbt.userid=tbu.id WHERE tbt.state != 0 and tbt.is_end=0 ORDER BY tbt.createtime DESC limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pageSize * (pageIndex - 1));
			ps.setInt(2, pageSize);
			rs = ps.executeQuery();
			while (rs.next()) {
				bt = new BbsTopicEX();
				bt.setId(rs.getInt("id"));
				bt.setTitle(rs.getString("title"));
				bt.setNickname(rs.getString("nickname"));
				bt.setCreatetime(rs.getDate("createtime"));
				bt.setCategoryId(rs.getInt("category_id"));
				bt.setViewCount(rs.getInt("view_count"));
				bt.setHead_url(rs.getString("head_url"));
				bt.setIsGood(rs.getInt("is_good"));
				bt.setIsEnd(rs.getInt("is_end"));
				bt.setIsTop(rs.getInt("is_top"));
				list.add(bt);
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
	public int getTotalCount(int type) {
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = DButil.getInstance().getConnection();
			String sql="";
			if(type==1)
				sql = "select count(*) as totalnum from tab_bbs_topicinfo as tbt join tab_bbs_userinfo as tbu on tbt.userid=tbu.id WHERE tbt.state != 0 and tbt.is_end=0";
			else if(type==2)
				sql = "select count(*) as totalnum from tab_bbs_topicinfo as tbt join tab_bbs_userinfo as tbu on tbt.userid=tbu.id WHERE tbt.state != 0 and tbt.is_end=1";
			else if(type==3)
				sql = "select count(*) as totalnum from tab_bbs_topicinfo as tbt join tab_bbs_userinfo as tbu on tbt.userid=tbu.id WHERE tbt.state != 0 and tbt.is_good=1";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt("totalnum");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DButil.getInstance().close(ps);
		DButil.getInstance().close(conn);
		DButil.getInstance().close(rs);
		return count;
	}

	@Override
	public List<BbsTopicEX> selectVHotTopic() {
		List<BbsTopicEX> list = new ArrayList<BbsTopicEX>();
		BbsTopicEX bt = null;
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = DButil.getInstance().getConnection();
			String sql = "SELECT tbt.is_top, tbt.id, tbt.title, tbt.createtime, tbt.view_count, tbt.category_id,"
					+ " tbc.nickname category_name, tbt.is_good, tbt.is_end, tbu.nickname nickname, tbu.head_url "
					+ " FROM tab_bbs_topicinfo AS tbt "
					+ " JOIN tab_bbs_userinfo AS tbu ON tbt.userid = tbu.id "
					+ " JOIN tab_bbs_category AS tbc ON tbt.category_id = tbc.id"
					+ " WHERE tbt.state!=0 and TIMESTAMPDIFF(DAY,tbt.createtime,now())<7"
					+ " ORDER BY view_count desc LIMIT 5";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				bt = new BbsTopicEX();
				bt.setId(rs.getInt("id"));
				bt.setTitle(rs.getString("title"));
				bt.setNickname(rs.getString("nickname"));
				bt.setCreatetime(rs.getDate("createtime"));
				bt.setCategory_name(rs.getString("category_name"));
				bt.setCategoryId(rs.getInt("category_id"));
				bt.setViewCount(rs.getInt("view_count"));
				bt.setHead_url(rs.getString("head_url"));
				bt.setIsGood(rs.getInt("is_good"));
				bt.setIsEnd(rs.getInt("is_end"));
				bt.setIsTop(rs.getInt("is_top"));
				list.add(bt);
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
	public List<BbsTopicEX> selectAHotTopic() {
		List<BbsTopicEX> list = new ArrayList<BbsTopicEX>();
		BbsTopicEX bt = null;
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = DButil.getInstance().getConnection();
			String sql = "SELECT count(tbc.id) comm_total,tbt.is_top, tbt.id, tbt.title, tbt.createtime, tbt.view_count, tbt.category_id,"
					+ " tbc.nickname category_name, tbt.is_good, tbt.is_end, tbu.nickname nickname, tbu.head_url "
					+ " FROM tab_bbs_topicinfo AS tbt "
					+ " JOIN tab_bbs_userinfo AS tbu ON tbt.userid = tbu.id "
					+ " JOIN tab_bbs_category AS tbc ON tbt.category_id = tbc.id"
					+ " JOIN tab_bbs_comment AS tbcn ON tbcn.topic_or_comment_id=tbt.id"
					+ " WHERE tbt.state!=0 and tbcn.is_topic=0 and TIMESTAMPDIFF(DAY,tbt.createtime,now())<7"
					+ " GROUP BY tbt.id"
					+ " ORDER BY comm_total DESC LIMIT 5";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				bt = new BbsTopicEX();
				bt.setId(rs.getInt("id"));
				bt.setTitle(rs.getString("title"));
				bt.setNickname(rs.getString("nickname"));
				bt.setCreatetime(rs.getDate("createtime"));
				bt.setCategory_name(rs.getString("category_name"));
				bt.setCategoryId(rs.getInt("category_id"));
				bt.setViewCount(rs.getInt("view_count"));
				bt.setHead_url(rs.getString("head_url"));
				bt.setIsGood(rs.getInt("is_good"));
				bt.setIsEnd(rs.getInt("is_end"));
				bt.setIsTop(rs.getInt("is_top"));
				bt.setCommentTotal(rs.getInt("comm_total"));
				list.add(bt);
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
	public int collectTopic(int userid, int topicid) {
		//System.out.println("userid:"+userid+",topicid:"+topicid);
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		int rel = 0;
		try {
			conn = DButil.getInstance().getConnection();
			String sql1="select * from tab_bbs_collect where user_id=? and topic_id=?";
			ps = conn.prepareStatement(sql1);
			ps.setInt(1, userid);
			ps.setInt(2, topicid);
			rs=ps.executeQuery();
			if(rs.next()){
				rel=2;
			}else{
				
				String sql="INSERT INTO tab_bbs_collect(user_id,topic_id,collect_time) VALUES(?,?,now())";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, userid);
				ps.setInt(2, topicid);
				rel = ps.executeUpdate();
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DButil.getInstance().close(ps);
		DButil.getInstance().close(conn);
		DButil.getInstance().close(rs);
		return rel;
	}

	@Override
	public List<BbsTopicEX> getCollectTopicById(int id) {
		List<BbsTopicEX> list = new ArrayList<BbsTopicEX>();
		BbsTopicEX bt = null;
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = DButil.getInstance().getConnection();
			String sql = " SELECT tbt.id, tbt.title, tbc.collect_time"
						+ " FROM tab_bbs_topicinfo AS tbt, tab_bbs_userinfo AS tbu, tab_bbs_collect AS tbc"
						+ " WHERE tbc.user_id = tbu.id AND tbc.topic_id=tbt.id"
						+ " AND tbu.id =? AND tbt.state != 0 "
						+ " ORDER BY tbc.collect_time desc";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bt = new BbsTopicEX();
				bt.setId(rs.getInt("id"));
				bt.setTitle(rs.getString("title"));
				bt.setCollectTime(rs.getDate("collect_time"));
				list.add(bt);
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

}
