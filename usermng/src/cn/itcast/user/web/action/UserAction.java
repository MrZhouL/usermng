package cn.itcast.user.web.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.itcast.user.domain.User;
import cn.itcast.user.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

/**
 * UserAction，一个User模型只有这么一个Action
 * @author Administrator
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserService();
	
	private User user = new User();//模型驱动需要实例化属性
	
	public String doAdd(){
		return "doAddSucc";
	}
	
	public String del(){
		User currUser = userService.findByUid(user.getUid());
		
		userService.delUser(user.getUid());
		return "delSucc";
		
	}
	
	/**
	 * 条件查询用户
	 * @return
	 */
	public String conditionsQuery(){
		List<User> list = userService.findByAllUser(user);
		ActionContext.getContext().getValueStack().push(list);
		return "cquerySucc";
	}
	
	/**
	 * 查询所有员工
	 * @return
	 */
	public String list() {
		/*
		 * 1. 使用userService查询所有数据
		 * 2. 把数据压入栈顶
		 * 3. 返回到list.jsp显示数据。
		 */
		List<User> list = userService.findByAllUser(user);
		ActionContext.getContext().getValueStack().push(list);
		return "listSucc";
	}
	
	
	/**
	 * 登录的请求处理方法
	 * @return
	 */
	@InputConfig(resultName="loginInput")
	public String login() {
		/*
		 * 1. 获取表单数据：模型驱动！
		 * 2. 调用service方法完成登录，service方法返回一个currUser，如果为null表示失败
		 * 如果不为null，说明成功了。保存到session中。转发到loginSucc成功结果。
		 */
		User currUser = userService.login(user.getLoginname(), user.getLoginpass());
		if(currUser == null) {
			//获取国际化资源信息，添加到错误中。
			this.addActionError(this.getText("loginError"));
			return "loginError";
		}
		ServletActionContext.getRequest().getSession().setAttribute("user", currUser);
		return "loginSucc";
	}


	@Override
	public User getModel() {
		return user;
	}
	
	
	public Boolean getlsi(){
		return true;
	}
}
