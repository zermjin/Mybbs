package com.bbs.dao;

import java.util.List;

import com.bbs.bean.BbsUser;
import com.bbs.bean.BbsUserEX;

public interface UserDao {
	//添加用户（返回值大于0：成功执行）
	int addUser(BbsUser bu);
	//用户登录
	BbsUser userLogin(String email, String password);//
	//编辑用户（返回值大于0：成功执行）
	int updateUser(BbsUser bu);
	//修改密码（返回值大于0：成功执行）
	int updatePassword(int id, String nowPass, String newPass);
	//检查用户是否已存在
	boolean IsUserExist(String email);
	//用户头像上传
	int saveUserHeaderPicPath(int id, String url);
	//通过ID查询BbsUser
	BbsUser selectUser(int id);
	List<BbsUserEX> getHotUser();
}
