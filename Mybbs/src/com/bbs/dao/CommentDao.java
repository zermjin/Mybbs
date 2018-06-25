package com.bbs.dao;

import java.util.List;

import com.bbs.bean.BbsComment;
import com.bbs.bean.BbsCommentEX;

public interface CommentDao {
	//回复（提交答案）
	int addComment(BbsComment bc);
	int editComment(String cont,int id);
	//取得某个Topic的所有回复
	List<BbsCommentEX> getAllCommentByTopicId(int id);
	//取得某个用户的所有回复
	List<BbsCommentEX> selectAllById(int id);
	//增加赞数
	int addAgreeNum(int id,boolean flag);
	//取得某个回复的所有回复
	List<BbsCommentEX> getAllCommentById(int id);
	int deleteCommentById(int id);
	int acceptCommentById(int id,int uid,int kiss);
	//==========Message========================
	List<BbsCommentEX> getMessageById(int id);
	int deleteMessageById(int id);
	int deleteAllMessageById(int id);
}
