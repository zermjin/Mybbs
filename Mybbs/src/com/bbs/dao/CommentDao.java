package com.bbs.dao;

import java.util.List;

import com.bbs.bean.BbsComment;
import com.bbs.bean.BbsCommentEX;

public interface CommentDao {
	//�ظ����ύ�𰸣�
	int addComment(BbsComment bc);
	int editComment(String cont,int id);
	//ȡ��ĳ��Topic�����лظ�
	List<BbsCommentEX> getAllCommentByTopicId(int id);
	//ȡ��ĳ���û������лظ�
	List<BbsCommentEX> selectAllById(int id);
	//��������
	int addAgreeNum(int id,boolean flag);
	//ȡ��ĳ���ظ������лظ�
	List<BbsCommentEX> getAllCommentById(int id);
	int deleteCommentById(int id);
	int acceptCommentById(int id,int uid,int kiss);
	//==========Message========================
	List<BbsCommentEX> getMessageById(int id);
	int deleteMessageById(int id);
	int deleteAllMessageById(int id);
}
