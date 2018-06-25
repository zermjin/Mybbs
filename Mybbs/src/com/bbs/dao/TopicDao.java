package com.bbs.dao;

import java.util.List;

import com.bbs.bean.BbsTopic;
import com.bbs.bean.BbsTopicEX;

public interface TopicDao {
	// 取得首10条topic（首页显示）
	List<BbsTopicEX> selectTopic();
	List<BbsTopicEX> selectVHotTopic();
	List<BbsTopicEX> selectAHotTopic();
	// 发布问题
	int addTopic(BbsTopic bt);

	// 编辑问题
	int updateTopic(BbsTopic bt, int oldkiss);

	// 通过ID取得Topic
	BbsTopicEX getTopicByID(int id);

	// 通过ID增加ViewCounts数
	int insertTopicViewCounts(int id);

	// 通过用户ID取得所有该用户发布的Topic
	List<BbsTopicEX> selectTopicById(int id);

	// 分页功能
	List<BbsTopicEX> getPagedTopics(int pageSize, int pageIndex);
	List<BbsTopicEX> getPagedGoodTopics(int pageSize, int pageIndex);
	List<BbsTopicEX> getPagedEndTopics(int pageSize, int pageIndex);
	List<BbsTopicEX> getPagedNotEndTopics(int pageSize, int pageIndex);

	// Topic总数
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
