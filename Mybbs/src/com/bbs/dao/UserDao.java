package com.bbs.dao;

import java.util.List;

import com.bbs.bean.BbsUser;
import com.bbs.bean.BbsUserEX;

public interface UserDao {
	//����û�������ֵ����0���ɹ�ִ�У�
	int addUser(BbsUser bu);
	//�û���¼
	BbsUser userLogin(String email, String password);//
	//�༭�û�������ֵ����0���ɹ�ִ�У�
	int updateUser(BbsUser bu);
	//�޸����루����ֵ����0���ɹ�ִ�У�
	int updatePassword(int id, String nowPass, String newPass);
	//����û��Ƿ��Ѵ���
	boolean IsUserExist(String email);
	//�û�ͷ���ϴ�
	int saveUserHeaderPicPath(int id, String url);
	//ͨ��ID��ѯBbsUser
	BbsUser selectUser(int id);
	List<BbsUserEX> getHotUser();
}
