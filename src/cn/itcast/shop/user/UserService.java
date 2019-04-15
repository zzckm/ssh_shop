package cn.itcast.shop.user;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.utils.MailUtils;
import cn.itcast.shop.utils.PageBean;
import cn.itcast.shop.utils.UUIDUtils;

/**
 * User模块：业务层代码
 * @author Kg.zz
 *
 */
@Transactional
public class UserService {
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	/**
	 * 业务层注册User的代码
	 * @param user
	 */
	public void regist(User user) {
		//保存用户
		user.setState(0);//0未激活 1已激活
		String code=UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		//发送邮件
		try {
			MailUtils.sendMail(user.getEmail(), code);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//业务层根据激活码查询用户的方法
	public User findByCode(String code) {			
		return userDao.findByCode(code);
	}
	//业务层根据激活码修改用户方法
	public void update(User existUser) {
		 userDao.update(existUser);		
	}
	
	//业务层登录的方法
	public User login(User user) {
		
		return userDao.login(user);
	}
	//业务层查询是否已有该注册的用户名
	public User findByUserName(String username) {

		return userDao.findByUserName(username);
	}
	public PageBean<User> findByPage(Integer page) {
		//封装PageBean
		PageBean<User> pageBean=new PageBean<User>();
		//封装页数
		pageBean.setPage(page);
		//每页显示10个
		Integer limit=10;
		pageBean.setLimit(limit);
		//总记录数
		Integer totalCount=userDao.findCount(page);
		pageBean.setTotalCount(totalCount);
		//总页数
		Integer totalPage=0;
		if(totalCount%limit==0)
		{
			totalPage=totalCount/limit;
		}
		else {
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//每页显示的数据
		Integer begin=(page-1)*limit;
		List<User> list=userDao.findByPage(begin,limit);		
		pageBean.setList(list);
		return pageBean;
	}
}
