package com.catic.mobilehos.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;

import com.catic.mobilehos.po.RolesPO;
import com.catic.mobilehos.service.RolesService;
import com.catic.mobilehos.service.vo.Page;
import com.catic.mobilehos.service.vo.RolesVO;
import com.catic.mobilehos.service.vo.VOPOConverter;

/**
 * 角色管理
 * 
 * @author linchunda
 * 
 */
@SuppressWarnings("serial")
public class RolesAction extends BaseAction {
	private RolesService rolesService;

	private final int DEFAULT_PAGESIZE = 10;
	private int page;
	private RolesVO rolesVO;
	private Page<RolesVO> pageBean;
	private RolesPO rolesPO;
	private int roleId;

	private String au_ids;// 权限标识,用逗号分隔

	/**
	 * 角色分页
	 * 
	 * @return
	 */
	public String getRoles() {
		pageBean = rolesService.getRolesPOByParas(page, DEFAULT_PAGESIZE);
		Map<String, String> paras = new TreeMap<String, String>();

		String baseUrl = "manager/sysUser/getRoles";
		pageBean.setQueryUrl(baseUrl, paras);
		return SUCCESS;
	}

	/**
	 * 增加角色
	 * 
	 * @return
	 */
	public String saveRole() {
		VOPOConverter<RolesVO, RolesPO> converter = new VOPOConverter<RolesVO, RolesPO>(
				RolesVO.class, RolesPO.class);
		rolesPO = converter.voToPO(rolesVO);

		rolesPO.setCreateTime(new Timestamp(new Date().getTime()));
		rolesPO.setUpdateTime(new Timestamp(new Date().getTime()));
		roleId = rolesService.createRolesPO(rolesPO);

		// 添加权限
		if (!StringUtils.isEmpty(au_ids)) {
			String[] auId_arry = au_ids.split(",");
			int[] au_id = new int[auId_arry.length];
			for (int i = 0; i < auId_arry.length; i++) {
				au_id[i] = Integer.parseInt(auId_arry[i]);
			}
			rolesService.addAuthority(roleId, au_id);
		}
		return SUCCESS;
	}

	/**
	 * 进入修改角色页面
	 * 
	 * @return
	 */
	public String updateRole() {
		rolesPO = rolesService.getRolesPOByroleId(roleId);
		VOPOConverter<RolesVO, RolesPO> converter = new VOPOConverter<RolesVO, RolesPO>(
				RolesVO.class, RolesPO.class);
		setRolesVO(converter.poToVO(rolesPO));
		setRoleId(roleId);

		return SUCCESS;

	}

	/**
	 * 修改角色
	 * 
	 * @return
	 */
	public String updateRoleAction() {
		rolesPO = rolesService.getRolesPOByroleId(roleId);

		rolesPO.setRoleName(rolesVO.getRoleName());
		rolesPO.setRemark(rolesVO.getRemark());
		rolesPO.setUpdateTime(new Timestamp(new Date().getTime()));
		rolesService.updateRolesPO(rolesPO, roleId);

		// 更新权限
		rolesService.removeAuthority(roleId);

		// 添加权限
		if (!StringUtils.isEmpty(au_ids)) {
			au_ids= au_ids.replaceAll(",,", "");
			String[] auId_arry = au_ids.split(",");
			int[] au_id = new int[auId_arry.length];
			for (int i = 0; i < auId_arry.length; i++) {
				try {
					au_id[i] = Integer.parseInt(auId_arry[i].trim());
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
			rolesService.addAuthority(roleId, au_id);
		}

		return SUCCESS;
	}

	/**
	 * 获取角色权限
	 */
	public void getAuthority() {
		List<Integer> list = rolesService.getAuthority(roleId);
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
			jsonObject.put("au_ids", value.toString());
			writeJSON(jsonObject);
		}
	}

	/**
	 * 删除角色
	 */
	public void deleteRole() {
		boolean result = rolesService.deleteRolesPO(roleId);
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

	/**
	 * 增加或修改用户时,显示所有角色
	 */
	public void findAllRoles() {
		List<RolesVO> list = rolesService.findAllRolesVO();
		if (null == list || list.isEmpty()) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", "0");
			writeJSON(jsonObject);
		} else {
			JSONArray array = JSONArray.fromObject(list);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", "1");
			jsonObject.put("roles", array);
			writeJSON(jsonObject);
		}
	}

	public RolesService getRolesService() {
		return rolesService;
	}

	public void setRolesService(RolesService rolesService) {
		this.rolesService = rolesService;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public RolesVO getRolesVO() {
		return rolesVO;
	}

	public void setRolesVO(RolesVO rolesVO) {
		this.rolesVO = rolesVO;
	}

	public Page<RolesVO> getPageBean() {
		return pageBean;
	}

	public void setPageBean(Page<RolesVO> pageBean) {
		this.pageBean = pageBean;
	}

	public RolesPO getRolesPO() {
		return rolesPO;
	}

	public void setRolesPO(RolesPO rolesPO) {
		this.rolesPO = rolesPO;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getAu_ids() {
		return au_ids;
	}

	public void setAu_ids(String au_ids) {
		this.au_ids = au_ids;
	}

}
