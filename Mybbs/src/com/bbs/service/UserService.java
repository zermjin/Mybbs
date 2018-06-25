package com.bbs.service;

import java.util.List;

import com.bbs.bean.BbsUser;
import com.bbs.bean.BbsUserEX;
import com.bbs.dao.UserDaoImpl;

public class UserService {
	// ����û�������ֵ����0���ɹ�ִ�У�
	UserDaoImpl udi = new UserDaoImpl();

	public int addUser(BbsUser bu) {
		return udi.addUser(bu);
	}

	// �û���¼
	public BbsUser userLogin(String email, String password) {
		return udi.userLogin(email, password);
	}
	// �༭�û�������ֵ����0���ɹ�ִ�У�

	public int updateUser(BbsUser bu) {
		return udi.updateUser(bu);
	}

	// �޸����루����ֵ����0���ɹ�ִ�У�
	public int updatePassword(int id, String nowPass, String newPass) {
		return udi.updatePassword(id, nowPass, newPass);
	}

	// ����û��Ƿ��Ѵ���
	public boolean IsUserExist(String email) {
		return udi.IsUserExist(email);
	}

	// �û�ͷ���ϴ�
	public int saveUserHeaderPicPath(int id, String url) {
		return udi.saveUserHeaderPicPath(id, url);
	}

	// ͨ��ID��ѯBbsUser
	public BbsUser selectUser(int id) {
		return udi.selectUser(id);
	}

	public List<BbsUserEX> getHotUser() {
		return udi.getHotUser();
	}
}
