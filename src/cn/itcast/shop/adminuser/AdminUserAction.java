package cn.itcast.shop.adminuser;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台管理
 * @author Kg.zz
 *
 */
public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser> {
	//模型驱动接收数据使用
	private AdminUser adminUser=new AdminUser();
	//注入service
	private AdminUserService adminUserService;
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	public AdminUser getModel() {
		
		return adminUser;
	}
	public String login(){
		AdminUser existadminUser=adminUserService.login(adminUser);
		if(existadminUser==null)
		{
			//登录失败
			this.addActionError("用户名或密码错误");
			return LOGIN;
		}
		else
		{
			//登录成功
			ServletActionContext.getRequest().getSession().setAttribute("existadminUser", existadminUser);
			return "loginSuccess";
		}
		
	}

}
