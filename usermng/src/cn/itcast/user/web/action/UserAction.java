package cn.itcast.user.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.channels.SelectableChannel;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import cn.itcast.user.domain.User;
import cn.itcast.user.service.UserService;
import cn.itcast.utils.CommonUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

/**
 * UserAction，一个User模型只有这么一个Action
 * 
 * @author Administrator
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {
	/**
	 * 记录类版本
	 */
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserService();

	private User user = new User();// 模型驱动需要实例化属性

	private File upload;
	private String uploadFileName;
	private String uploadPath;

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public void setUploadFileName(String uploadFileName) {
		int i = uploadFileName.lastIndexOf("\\");
		if (i != -1) {
			this.uploadFileName = uploadFileName.substring(i + 0);
		} else {
			this.uploadFileName = uploadFileName;
		}
	}
	
	public String download(){
		return "donwloadSucc";
	}
	
	public String getContType(){
		return ServletActionContext.getServletContext().getMimeType(user.getFilepath());
	}
	
	public String getFilename() throws UnsupportedEncodingException{
		//处理GET请求的中文编码问题
		String name = new String(user.getFilename().getBytes("ISO-8859-1"),"UTF-8");
		
		//处理浏览器下载框显示中文问题
		name = new String(name.getBytes("GBK"),"ISO-8859-1");
		return name;
	}
	
	public InputStream getInputStream() throws FileNotFoundException, UnsupportedEncodingException{
		String filepath = user.getFilepath();
		filepath = new String(filepath.getBytes("ISO-8859-1"),"UTF-8");
		
		String realPath = ServletActionContext.getServletContext().getRealPath(filepath);
		return new FileInputStream(realPath);
	}
	
	
	public String doView(){
		ServletActionContext.getContext().getValueStack().push(userService.findByUid(user.getUid()));
		return "doViewSucc";
	}

	public String edit() throws IOException {
		if (upload != null) {// 上传了新的简历
			String filepath = user.getFilepath();
			if (filepath != null && !filepath.trim().isEmpty()) {// 如果原来也不是空的
				/**
				 * 删除原来的，更新user的filepath and filename
				 */
				new File(ServletActionContext.getServletContext().getRealPath(
						filepath)).delete();
			}
			user.setFilename(uploadFileName);

			user.setFilepath(this.uploadPath + "\\" + CommonUtils.uuid() + "_"
					+ this.uploadFileName);

			// 上传到服务器
			File destFile = new File(ServletActionContext.getServletContext()
					.getRealPath(user.getFilepath()));
			FileUtils.copyFile(upload, destFile);

		}

		userService.edit(user);
		return "editSucc";
	}

	public String doEdit() {
		User currUser = userService.findByUid(user.getUid());
		ActionContext.getContext().getValueStack().push(currUser);
		return "doEditSucc";
	}

	/**
	 * 添加用户
	 * 
	 * @return
	 * @throws IOException
	 */
	public String addUser() throws IOException {
		user.setUid(CommonUtils.uuid());

		if (this.uploadFileName != null) {
			user.setFilename(uploadFileName);

			user.setFilepath(this.uploadPath + "\\" + CommonUtils.uuid() + "_"
					+ this.uploadFileName);
		}

		userService.addUser(user);

		// 保存文件到服务器
		if (this.uploadFileName != null) {
			// 获得绝对路径
			String realPath = ServletActionContext.getServletContext()
					.getRealPath(user.getFilepath());

			File destFile = new File(realPath);

			FileUtils.copyFile(upload, destFile);

		}
		return "addSucc";
	}

	public String doAdd() {
		return "doAddSucc";
	}

	/**
	 * 删除用户
	 * 
	 * @return
	 */
	public String del() {
		User currUser = userService.findByUid(user.getUid());

		userService.delUser(user.getUid());
		return "delSucc";

	}

	/**
	 * 条件查询用户
	 * 
	 * @return
	 */
	public String conditionsQuery() {
		List<User> list = userService.findByAllUser(user);
		ActionContext.getContext().getValueStack().push(list);
		return "cquerySucc";
	}

	/**
	 * 查询所有员工
	 * 
	 * @return
	 */
	public String list() {
		/*
		 * 1. 使用userService查询所有数据 2. 把数据压入栈顶 3. 返回到list.jsp显示数据。
		 */
		List<User> list = userService.findByAllUser(user);
		ActionContext.getContext().getValueStack().push(list);
		return "listSucc";
	}

	/**
	 * 登录的请求处理方法
	 * 
	 * @return
	 */
	@InputConfig(resultName = "loginInput")
	public String login() {
		/*
		 * 1. 获取表单数据：模型驱动！ 2. 调用service方法完成登录，service方法返回一个currUser，如果为null表示失败
		 * 如果不为null，说明成功了。保存到session中。转发到loginSucc成功结果。
		 */
		User currUser = userService.login(user.getLoginname(),
				user.getLoginpass());
		if (currUser == null) {
			// 获取国际化资源信息，添加到错误中。
			this.addActionError(this.getText("loginError"));
			return "loginError";
		}
		ServletActionContext.getRequest().getSession()
				.setAttribute("user", currUser);
		return "loginSucc";
	}

	@Override
	public User getModel() {
		return user;
	}

	public Boolean getlsi() {
		return true;
	}
}
