package com.catic.mobilehos.enterprize.dao;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;

public class BaseDao {

	@Resource
	protected JdbcTemplate jdbc;
	
}
