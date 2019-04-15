package cn.itcast.shop.categorysecond;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.category.Category;
import cn.itcast.shop.product.Product;
import cn.itcast.shop.utils.PageHibernateCallback;

public class CategorySecondDao extends HibernateDaoSupport {
	//查询二级分类的总记录数
	public Integer findCount(Integer page) {
		List<Long> list=this.getHibernateTemplate().find("select count(*) from CategorySecond");
		if(list.size()>0)
		{
			return list.get(0).intValue();//返回整数
		}
		return null;
	}
	//分页查询
	public  List<CategorySecond> findByPage(Integer begin, Integer limit) {
		String hql="from CategorySecond";
		List<CategorySecond> list=this.getHibernateTemplate().executeFind(new PageHibernateCallback<CategorySecond>(hql, null, begin, limit));
		if(list.size()>0)
		{
			return list;
		}
		return null;
	}
	//保存二级分类
	public void save(CategorySecond categorySecond) {
		this.getHibernateTemplate().save(categorySecond);		
	}
	public List<CategorySecond> findAll() {
		
		return this.getHibernateTemplate().find("from CategorySecond");
	}
	
	public void delete(CategorySecond categorySecond) {
		categorySecond = this.getHibernateTemplate().get(CategorySecond.class, categorySecond.getCsid());
		this.getHibernateTemplate().delete(categorySecond);		
	}
	



	

}
