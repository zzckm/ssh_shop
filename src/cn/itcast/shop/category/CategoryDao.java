package cn.itcast.shop.category;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 继承HibernateDaoSupport  才可以在Spring配置文件中的声明的Dao中注入sessionFactory
 * @author Kg.zz
 *
 */
public class CategoryDao extends HibernateDaoSupport{

	//后台：DAO层的查询所有的一级分类
	public List<Category> findAll() {
		return this.getHibernateTemplate().find("from Category");
	}
	//后台：DAO层添加一级分类
	public void save(Category category) {
	this.getHibernateTemplate().save(category);	
	}
	//后台：DAO层删除一级分类  (级联删除 需要先查询后删除)**************一定得先查询再删除，这样删除时category里才有数据
	public void delete(Category category) {
		category = this.getHibernateTemplate().get(Category.class, category.getCid());
		this.getHibernateTemplate().delete(category);
		
	}
	//后台：查询指定cid的一级分类
	public Category findByCid(Integer cid) {		
		return this.getHibernateTemplate().get(Category.class, cid);
	}
	//后台：修改指定cid的一级分类
	public void update(Category category) {
		this.getHibernateTemplate().update(category);
		
	}
	
}
