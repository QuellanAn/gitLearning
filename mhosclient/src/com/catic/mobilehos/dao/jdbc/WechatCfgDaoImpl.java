package com.catic.mobilehos.dao.jdbc;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.util.StringUtils;

import com.catic.mobilehos.dao.WechatCfgDao;
import com.catic.mobilehos.po.WechatCfgPO;

public class WechatCfgDaoImpl extends JdbcDaoSupport implements WechatCfgDao {

	@Override
	public List<WechatCfgPO> getWechatCfgs() {
		String sql = "SELECT * FROM cfg_datas WHERE data_name LIKE 'wechat_msg_%'";
		return getJdbcTemplate().query(sql, new WechatCfgPO());
	}

	@Override
	public void saveWechatCfgs(List<WechatCfgPO> cfgs) {
		String sql = null;
		for (WechatCfgPO cfg : cfgs) {
			sql = "DELETE FROM cfg_datas WHERE data_name = ?";
			getJdbcTemplate().update(sql, cfg.getDataName());
			if (!StringUtils.isEmpty(cfg.getDataValue())) {
				sql = "INSERT INTO cfg_datas (data_name, data_value) VALUES (?, ?)";
				getJdbcTemplate().update(sql, cfg.getDataName(), cfg.getDataValue());
			}
		}
	}
}
