package com.bbs.service;

import java.util.List;

import com.bbs.bean.BbsTopic;
import com.bbs.bean.BbsTopicEX;
import com.bbs.dao.TopicDaoImpl;

public class TopicService {
	TopicDaoImpl tdi = new TopicDaoImpl();

	public List<BbsTopicEX> selectTopic() {
		return tdi.selectTopic();
	}
	public List<BbsTopicEX> selectVHotTopic(){
		return tdi.selectVHotTopic();
	}
	public List<BbsTopicEX> selectAHotTopic(){
		return tdi.selectAHotTopic();
	}
	public int addTopic(BbsTopic bt) {
		return tdi.addTopic(bt);
	}

	public BbsTopicEX getTopicByID(int id) {
		return tdi.getTopicByID(id);
	}

	public int insertTopicViewCounts(int id) {
		return tdi.insertTopicViewCounts(id);
	}

	public List<BbsTopicEX> selectTopicById(int id) {
		return tdi.selectTopicById(id);
	}

	public List<BbsTopicEX> getPagedTopics(int pageSize, int pageIndex) {
		return tdi.getPagedTopics(pageSize, pageIndex);
	}
	public List<BbsTopicEX> getPagedGoodTopics(int pageSize, int pageIndex){
		return tdi.getPagedGoodTopics(pageSize, pageIndex);
	}
	public List<BbsTopicEX> getPagedEndTopics(int pageSize, int pageIndex){
		return tdi.getPagedEndTopics(pageSize, pageIndex);
	}
	public List<BbsTopicEX> getPagedNotEndTopics(int pageSize, int pageIndex){
		return tdi.getPagedNotEndTopics(pageSize, pageIndex);
	}

	public int getTotalCount() {
		return tdi.getTotalCount();
	}

	public BbsTopicEX ViewTopicDetail(int id) {
		tdi.insertTopicViewCounts(id);
		return tdi.getTopicByID(id);
	}

	public int updateTopic(BbsTopic bt, int oldkiss) {
		return tdi.updateTopic(bt, oldkiss);
	}

	public int deleteTopicById(int id) {
		return tdi.deleteTopicById(id);
	}

	public int goodTopicById(int id, boolean flag) {
		return tdi.goodTopicById(id, flag);
	}

	public int topTopicById(int id, boolean flag) {
		return tdi.topTopicById(id, flag);
	}

	public int endTopicById(int id) {
		return tdi.endTopicById(id);
	}
	public int getTotalCount(int type){
		return tdi.getTotalCount(type);
	}
	public int collectTopic(int userid,int topicid){
		return tdi.collectTopic(userid, topicid);
	}
	public List<BbsTopicEX> getCollectTopicById(int id){
		return tdi.getCollectTopicById(id);
	}
}
