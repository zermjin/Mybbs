package com.bbs.dao;

import java.util.List;

import com.bbs.bean.BbsTopic;
import com.bbs.bean.BbsTopicEX;

public interface TopicDao {
	// ȡ����10��topic����ҳ��ʾ��
	List<BbsTopicEX> selectTopic();
	List<BbsTopicEX> selectVHotTopic();
	List<BbsTopicEX> selectAHotTopic();
	// ��������
	int addTopic(BbsTopic bt);

	// �༭����
	int updateTopic(BbsTopic bt, int oldkiss);

	// ͨ��IDȡ��Topic
	BbsTopicEX getTopicByID(int id);

	// ͨ��ID����ViewCounts��
	int insertTopicViewCounts(int id);

	// ͨ���û�IDȡ�����и��û�������Topic
	List<BbsTopicEX> selectTopicById(int id);

	// ��ҳ����
	List<BbsTopicEX> getPagedTopics(int pageSize, int pageIndex);
	List<BbsTopicEX> getPagedGoodTopics(int pageSize, int pageIndex);
	List<BbsTopicEX> getPagedEndTopics(int pageSize, int pageIndex);
	List<BbsTopicEX> getPagedNotEndTopics(int pageSize, int pageIndex);

	// Topic����
	int getTotalCount();
	int getTotalCount(int type);


	int deleteTopicById(int id);

	int goodTopicById(int id, boolean flag);

	int topTopicById(int id, boolean flag);
	
	int endTopicById(int id);
	//===============
	int collectTopic(int userid,int topicid);
	List<BbsTopicEX> getCollectTopicById(int id);
}
