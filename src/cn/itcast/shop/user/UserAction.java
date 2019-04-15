package cn.itcast.shop.user;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.utils.PageBean;

import com.ndktools.javamd5.Mademd5;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class UserAction extends ActionSupport implements ModelDriven<User> {
	//Struts2模型驱动使用的类      ModelDriven（模型驱动）
	private User user=new User();
	//注入userSerivice
	private UserService userService;
	// 接收验证码
		private String txtcheckcode;
	
	public void setCheckcode(String txtcheckcode) {
			this.txtcheckcode = txtcheckcode;
		}


	public User getModel() {		
		return user;
	}
	
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}	
	
	/**
	 *  编写一个挑转到注册页面的方法:
	 * @return
	 */
	public String registPage(){
		
		return "registPageSuccess";
	}
	
	/**
	 * 前台：注册User的方法
	 */
	@InputConfig(resultName="registInput")//?
	public String regist(){
		//从session中获得存的验证 码 (在CheckcodeAction中 已经往Session中存了一个"checkcode")
		String checkcode=(String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(txtcheckcode==null ||!txtcheckcode.equalsIgnoreCase(checkcode))
		{
			this.addActionError("验证码错误");
			return "registInput";
		}
		Mademd5 md = new Mademd5();
		user.setPassword(md.toMd5(user.getPassword()));
		userService.regist(user);
		this.addActionMessage("注册成功!请去邮箱进行激活");
		return "registSuccess";
	}
	
	/**
	 * 前台:激活用户的方法:
	 */
	public String active(){
		// 根据激活码查询用户
		User existUser = userService.findByCode(user.getCode());
		if(existUser != null){
			// 根据激活码查询到这个用户.
			existUser.setState(1);
			// 修改用户的状态
			userService.update(existUser);
			// 添加信息:
			this.addActionMessage("激活成功!请去登录!");
			return "activeSuccess";
		}else{
			this.addActionMessage("激活失败!激活码有误!");
			return "activeSuccess";
		}
	}
	
	/**
	 * 前台:跳转到登录页面的方法
	 */
	public String loginPage(){
		return "loginPageSuccess";
	}
	
	/**
	 * 前台：登錄功能
	 */
	@InputConfig(resultName="loginInput")//?
	public String login(){
		/*String checkcode=(String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(txtcheckcode==null ||!txtcheckcode.equalsIgnoreCase(checkcode))
		{
			this.addActionError("验证码错误");
			return "loginInput";
		}*/
		Mademd5 md = new Mademd5();
		user.setPassword(md.toMd5(user.getPassword()));
		System.out.println(user.getPassword());
		User existUser=userService.login(user);
		if(existUser==null)
		{
			this.addActionError("用户名或密码错误或用户未激活");
			return "loginInput";
		}
		else {
			//存入 Session中
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			
		}
		return "loginSuccess";
	}
	
	
	/**
	 * 前台:注册AJAX校验用户名.
	 * @throws IOException 
	 */
	public String checkUserName() throws IOException{
		User existUser = userService.findByUserName(user.getUsername());
		//声明一个response以备下方显示提示使用
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		if(existUser == null){
			// 用户名可以使用的
			response.getWriter().print("<font color='green'>用户名可以使用</font>");
		}else{
			// 用户名已经存在
			response.getWriter().print("<font color='red'>用户名已经存在</font>");
		}
		return NONE;
	}
	
	/**
	 * 用户退出的方法
	 */
	public String quit(){
		// 获得用户的session.
		// 销毁session --invalidate()
		ServletActionContext.getRequest().getSession().invalidate();
		return "quitSuccess";
	}
	
	
	/**
	 * 后台：查询所有用户
	 */
	//接收页面参数：
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	public String adminFindAll(){ 
		//调用Service完成查询
		PageBean<User> pageBean= userService.findByPage(page);
		//压栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "adminFindAllSuccess";
}
}
