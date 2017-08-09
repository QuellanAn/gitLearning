package com.catic.mobilehos.action;

import java.util.List;

import org.springframework.util.DigestUtils;

import com.catic.mobilehos.menu_role_authority.entity.Menu;
import com.catic.mobilehos.po.SysUsersPO;
import com.catic.mobilehos.service.SysUsersService;

/**
 * 登录
 * 
 * @author linchunda
 * 
 */
@SuppressWarnings("serial")
public class LoginAction extends BaseAction {
	private SysUsersService sysUsersService;
	private String userName;
	private String password;

	@SuppressWarnings("deprecation")
	@Override
	public String execute() throws Exception {
		SysUsersPO sysUsersPO = sysUsersService.findSysUsersPOByParas(userName);
		// 不存在该用户、密码不正确，账号无效
		if (null == sysUsersPO
				|| !sysUsersPO.getPassword().equalsIgnoreCase(
						DigestUtils.md5DigestAsHex(password.getBytes()))) {
			this.addFieldError("userName", "不存在该账号或密码不正确！");
			return ERROR;
		}

		if (sysUsersPO.getStatus() == 1) {
			this.addFieldError("userName", "该账号已经被禁用，请与管理员联系！");
			return ERROR;
		}

		request.getSession().setAttribute("userName", userName);
		request.getSession().setAttribute("userId", sysUsersPO.getUserId()+"");
		// 权限
		List<String> list = sysUsersService.getAuthorityByUserId(sysUsersPO
				.getUserId());

		if (null == list || list.isEmpty()) {
			this.addFieldError("userName", "该账号没有任何权限，请与管理员联系！");
			request.getSession().removeAttribute("userName");
			return ERROR;
		}

		request.getSession().setAttribute("authority", list);

		//加载菜单
		List<Menu> mlist = menuBiz.findMenu(1);//mid
		request.getSession().setAttribute("menu", mlist);
		
		
		
		return SUCCESS;
	}

	/**
	 * 注销
	 * 
	 * @return
	 */
	public String logout() {
		request.getSession().removeAttribute("userName");
		request.getSession().removeAttribute("authority");
		return SUCCESS;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public SysUsersService getSysUsersService() {
		return sysUsersService;
	}

	public void setSysUsersService(SysUsersService sysUsersService) {
		this.sysUsersService = sysUsersService;
	}

}
