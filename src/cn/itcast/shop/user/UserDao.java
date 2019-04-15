package cn.itcast.shop.user;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.utils.PageHibernateCallback;

/**
 * User模块Dao层代码
 * @author Kg.zz
 *
 */
public class UserDao extends HibernateDaoSupport {

	/**
	 * Dao层保存用户的代码
	 * @param user
	 */
	public void save(User user) {
	this.getHibernateTemplate().save(user);
		
	}

	/**
	 * Dao层根据激活码查询用户
	 * @param code
	 * @return
	 */
	public User findByCode(String code) {
		List<User> list= this.getHibernateTemplate().find("from User where code=?", code);
		if(list.size()!=0)
		{
			return list.get(0);
		}
		return null;
	}

	/**
	 * Dao层修改用户的办法
	 * @param existUser
	 */
	public void update(User existUser) {
		this.getHibernateTemplate().update(existUser);
		
		
	}

	/**
	 * Dao层的登录方法
	 * @param user
	 * @return
	 */
	public User login(User user) {
		List<User> list=this.getHibernateTemplate().find("from User where username=? " +
				"and password=? and state=?"
				, user.getUsername(),user.getPassword(),1);
		if(list.size()!=0)
		{
			return list.get(0);
		}
		return null;
	}

	public User findByUserName(String username) {
		List<User> list=this.getHibernateTemplate().find("from User where username=?",username);
		if(list.size()!=0)
		{
			return list.get(0);
		}
		return null;
	}	
	//查询所有用户的总记录数
	public Integer findCount(Integer page) {
		List<Long> list=this.getHibernateTemplate().find("select count(*) from User");
		if(list.size()>0)
		{
			return list.get(0).intValue();//返回整数
		}
		return null;
	}
	//分页查询
	public List<User> findByPage(Integer begin, Integer limit) {
		String hql="from User";
		List<User> list=this.getHibernateTemplate().executeFind(new PageHibernateCallback<User>(hql, null, begin, limit));
		if(list.size()>0)
		{
			return list;
		}
		return null;
	}
}
