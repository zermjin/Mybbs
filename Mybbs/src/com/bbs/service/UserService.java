package com.bbs.service;

import java.util.List;

import com.bbs.bean.BbsUser;
import com.bbs.bean.BbsUserEX;
import com.bbs.dao.UserDaoImpl;

public class UserService {
	// 添加用户（返回值大于0：成功执行）
	UserDaoImpl udi = new UserDaoImpl();

	public int addUser(BbsUser bu) {
		return udi.addUser(bu);
	}

	// 用户登录
	public BbsUser userLogin(String email, String password) {
		return udi.userLogin(email, password);
	}
	// 编辑用户（返回值大于0：成功执行）

	public int updateUser(BbsUser bu) {
		return udi.updateUser(bu);
	}

	// 修改密码（返回值大于0：成功执行）
	public int updatePassword(int id, String nowPass, String newPass) {
		return udi.updatePassword(id, nowPass, newPass);
	}

	// 检查用户是否已存在
	public boolean IsUserExist(String email) {
		return udi.IsUserExist(email);
	}

	// 用户头像上传
	public int saveUserHeaderPicPath(int id, String url) {
		return udi.saveUserHeaderPicPath(id, url);
	}

	// 通过ID查询BbsUser
	public BbsUser selectUser(int id) {
		return udi.selectUser(id);
	}

	public List<BbsUserEX> getHotUser() {
		return udi.getHotUser();
	}
}
