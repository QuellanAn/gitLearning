package com.catic.mobilehos.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import com.catic.mobilehos.po.SysUsersPO;
import com.catic.mobilehos.service.SysUsersService;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.SysUsersVO;
import com.catic.mobilehos.service.vo.VOPOConverter;

/**
 * 系统用户
 * 
 * @author linchunda
 * 
 */
@SuppressWarnings("serial")
public class SysUsersAction extends BaseAction {
	private int userId;
	private SysUsersPO sysUsersPO;
	private SysUsersService sysUsersService;

	private final int DEFAULT_PAGESIZE = 10;

	private int page;

	private SysUsersVO sysUsersVO;

	private Page<SysUsersVO> pageBean;

	private String realName; // 姓名
	private int status = -1;
	private int roleId = -1;

	private int[] role_ids;// 角色标识

	private String userName;// 账号名

	/**
	 * 系统用户管理页面,根据分布获取系统用户数据
	 * 
	 * @return
	 */
	public String getSysUsers() {
		try {
			pageBean = sysUsersService.getSysUsersPOsbyParas(realName, status,
					roleId, page, DEFAULT_PAGESIZE);

			Map<String, String> paras = new TreeMap<String, String>();
			if (!StringUtils.isEmpty(realName)) {
				paras.put("realName", realName);
			}
			paras.put("status", Integer.toString(status));
			paras.put("roleId", Integer.toString(roleId));

			String baseUrl = "manager/sysUser/geSystUsers";
			pageBean.setQueryUrl(baseUrl, paras);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("获取系统用户数据出错:", e);
			return ERROR;
		}
	}

	/**
	 * 获取用户拥有的角色
	 */
	public void getRoles() {
		List<Integer> list = sysUsersService.getRoles(userId);
		if (null == list || list.isEmpty()) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", "1");
			writeJSON(jsonObject);
		} else {
			StringBuffer value = new StringBuffer();
			for (Integer integer : list) {
				value.append(integer.intValue()).append(",");
			}

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", "0");
			jsonObject.put("role_ids", value.toString());
			writeJSON(jsonObject);
		}
	}

	/**
	 * 检查是否已经存在相同的账号名
	 */
	public void existsUsername() {
		boolean result = sysUsersService.existsUsername(userName);
		if (result) {// 存在相同的账号名
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", "0");
			writeJSON(jsonObject);
		} else {// 不存在相同的账号名
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", "1");
			writeJSON(jsonObject);
		}
	}

	/**
	 * 增加系统用户
	 * 
	 * @return
	 */
	public String saveSysUser() {
		sysUsersPO = sysUsersVO;
		sysUsersPO.setPassword(DigestUtils.md5DigestAsHex(sysUsersVO
				.getPassword().getBytes()));
		sysUsersPO.setCreateTime(new Timestamp(new Date().getTime()));
		sysUsersPO.setUpdateTime(new Timestamp(new Date().getTime()));
		userId = sysUsersService.createSysUsersPO(sysUsersPO);

		// 添加角色
		sysUsersService.addRoles(userId, role_ids);
		return SUCCESS;
	}

	/**
	 * 进入修改系统用户页面
	 * 
	 * @return
	 */
	public String updateSysUser() {
		this.setUserId(userId);
		sysUsersPO = sysUsersService.getUser(userId);
		VOPOConverter<SysUsersVO, SysUsersPO> converter = new VOPOConverter<SysUsersVO, SysUsersPO>(
				SysUsersVO.class, SysUsersPO.class);
		sysUsersVO = converter.poToVO(sysUsersPO);
		return SUCCESS;
	}

	/**
	 * 修改系统用户
	 * 
	 * @return
	 */
	public String updateSysUserAction() {
		sysUsersPO = sysUsersService.getUser(userId);

		sysUsersPO.setRealName(sysUsersVO.getRealName());
		if (!StringUtils.isEmpty(sysUsersVO.getPassword())) {
			sysUsersPO.setPassword(DigestUtils.md5DigestAsHex(sysUsersVO
					.getPassword().getBytes()));
		}

		sysUsersPO.setSex(sysUsersVO.getSex());

		sysUsersPO.setTel(sysUsersVO.getTel());
		sysUsersPO.setDepartment(sysUsersVO.getDepartment());
		sysUsersPO.setRemark(sysUsersVO.getRemark());
		sysUsersPO.setStatus(sysUsersVO.getStatus());

		sysUsersPO.setUpdateTime(new Timestamp(new Date().getTime()));
		sysUsersService.updateSysUsersPO(sysUsersPO, userId);

		// 移除角色
		sysUsersService.removeRoles(userId);
		// 重新添加角色
		sysUsersService.addRoles(userId, role_ids);

		return SUCCESS;
	}

	/**
	 * 删除系统用户
	 */
	public void deleteSysUser() {
		boolean result = sysUsersService.deleteSysUser(userId);
		if (result) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", "0");
			writeJSON(jsonObject);
		} else {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", "1");
			writeJSON(jsonObject);
		}
	}

	public SysUsersPO getSysUsersPO() {
		return sysUsersPO;
	}

	public void setSysUsersPO(SysUsersPO sysUsersPO) {
		this.sysUsersPO = sysUsersPO;
	}

	public SysUsersService getSysUsersService() {
		return sysUsersService;
	}

	public void setSysUsersService(SysUsersService sysUsersService) {
		this.sysUsersService = sysUsersService;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Page<SysUsersVO> getPageBean() {
		return pageBean;
	}

	public void setPageBean(Page<SysUsersVO> pageBean) {
		this.pageBean = pageBean;
	}

	public SysUsersVO getSysUsersVO() {
		return sysUsersVO;
	}

	public void setSysUsersVO(SysUsersVO sysUsersVO) {
		this.sysUsersVO = sysUsersVO;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int[] getRole_ids() {
		return role_ids;
	}

	public void setRole_ids(int[] role_ids) {
		this.role_ids = role_ids;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
