package com.bbs.service;

import java.util.List;

import com.bbs.bean.BbsComment;
import com.bbs.bean.BbsCommentEX;
import com.bbs.dao.CommentDaoImpl;

public class CommentService {
	CommentDaoImpl cdi = new CommentDaoImpl();

	public int addComment(BbsComment bc) {
		return cdi.addComment(bc);
	}

	public int editComment(String cont, int id) {
		return cdi.editComment(cont, id);
	}

	public List<BbsCommentEX> getAllCommentByTopicId(int id) {
		return cdi.getAllCommentByTopicId(id);
	}

	public List<BbsCommentEX> selectAllById(int id) {
		return cdi.selectAllById(id);
	}

	public int addAgreeNum(int id, boolean flag) {
		return cdi.addAgreeNum(id, flag);
	}

	public List<BbsCommentEX> getAllCommentById(int id) {
		return cdi.getAllCommentById(id);
	}

	public List<BbsCommentEX> getMessageById(int id) {
		return cdi.getMessageById(id);
	}

	public int deleteMessageById(int id) {
		return cdi.deleteMessageById(id);
	}

	public int deleteAllMessageById(int id) {
		return cdi.deleteAllMessageById(id);
	}

	public int deleteCommentById(int id) {
		return cdi.deleteCommentById(id);
	}

	public int acceptCommentById(int id, int uid, int kiss) {
		return cdi.acceptCommentById(id, uid, kiss);
	}
}
