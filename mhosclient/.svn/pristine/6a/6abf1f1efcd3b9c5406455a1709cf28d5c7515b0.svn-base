package com.catic.mobilehos.dao.jdbc;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.catic.mobilehos.dao.StatisticsDao;
import com.catic.mobilehos.po.AnswerPO;
import com.catic.mobilehos.po.FeedBackPO;
import com.catic.mobilehos.po.ShowQuesPO;
import com.catic.mobilehos.po.TitlePO;

public class StatisticsDaoImpl extends JdbcDaoSupport implements StatisticsDao {

	public List<Object> getMemberSeries(String start, String end) {
		List<Object> series = new ArrayList<Object>();

		// 1.获取安卓用户的统计数据
		String sql0 = "SELECT create_date, count(0) FROM users WHERE client = 0 AND create_date BETWEEN ? AND ? GROUP BY DATE(create_date) ORDER BY create_date;";
		List<String> data0 = getJdbcTemplate().query(sql0, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int i) throws SQLException {
				Date d = rs.getDate("create_date");
				return "[Date.UTC(" + (d.getYear() + 1900) + "," + (d.getMonth() + 1) + "," + d.getDate() + "), " + rs.getInt("count(0)") + "]";
			}
		}, start, end);
		if (data0 != null && !data0.isEmpty()) {
			Map<String, Object> s0 = new HashMap<String, Object>();
			s0.put("\"name\"", "\"安卓\"");
			s0.put("\"data\"", data0);
			series.add(s0);
		}

		// 2.获取苹果用户的统计数据
		String sql1 = "SELECT create_date, count(0) FROM users WHERE client = 1 AND create_date BETWEEN ? AND ? GROUP BY DATE(create_date) ORDER BY create_date;";
		List<String> data1 = getJdbcTemplate().query(sql1, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int i) throws SQLException {
				Date d = rs.getDate("create_date");
				return "[Date.UTC(" + (d.getYear() + 1900) + "," + (d.getMonth() + 1) + "," + d.getDate() + "), " + rs.getInt("count(0)") + "]";
			}
		}, start, end);
		if (data1 != null && !data1.isEmpty()) {
			Map<String, Object> s1 = new HashMap<String, Object>();
			s1.put("\"name\"", "\"苹果\"");
			s1.put("\"data\"", data1);
			series.add(s1);
		}

		// 3.获取微信用户的统计数据
		String sql2 = "SELECT create_date, count(0) FROM users WHERE client = 2 AND create_date BETWEEN ? AND ? GROUP BY DATE(create_date) ORDER BY create_date;";
		List<String> data2 = getJdbcTemplate().query(sql2, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int i) throws SQLException {
				Date d = rs.getDate("create_date");
				return "[Date.UTC(" + (d.getYear() + 1900) + "," + (d.getMonth()) + "," + d.getDate() + "), " + rs.getInt("count(0)") + "]";
			}
		}, start, end);
		if (data2 != null && !data2.isEmpty()) {
			Map<String, Object> s2 = new HashMap<String, Object>();
			s2.put("\"name\"", "\"微信\"");
			s2.put("\"data\"", data2);
			series.add(s2);
		}
		String sql3 = "SELECT count(0) FROM users WHERE create_date BETWEEN ? AND ?";
		Integer count = getJdbcTemplate().queryForObject(sql3, Integer.class, start, end);
		series.add(count);
		return series;
	}

	@Override
	public List<Object> getRegistrationSeries(String start, String end, String type) {
		String sql0 = "SELECT department_id, department_name, COUNT(0) FROM app_reg_orders WHERE type= ? AND (create_date BETWEEN ? AND ?) GROUP BY department_id ORDER BY department_id;";
		SqlRowSet rs = getJdbcTemplate().queryForRowSet(sql0, type, start, end);
		String sql1 = "SELECT department_id, department_name, doctor_id, doctor_name, COUNT(0) FROM app_reg_orders WHERE type= ? AND (create_date BETWEEN ? AND ?) GROUP BY doctor_id, department_id ORDER BY department_id, doctor_id;";
		SqlRowSet rs2 = getJdbcTemplate().queryForRowSet(sql1, type, start, end);
		int i = 0;
		List<Object> data = new ArrayList<Object>();
		List<String> categories = new ArrayList<String>();
		while (rs.next()) {
			String department_name = rs.getString("department_name");
			categories.add("\"" + department_name + "\"");
			Map<String, Object> modality = new HashMap<String, Object>();
			modality.put("y", rs.getString("count(0)"));
			modality.put("color", "colors[" + i + "]");
			Map<String, Object> drilldown = new HashMap<String, Object>();
			if("0".equals(type)){//0为预约
				drilldown.put("name", "\"[" + department_name + "] 医生预约统计\"");
			}else {
				drilldown.put("name", "\"[" + department_name + "] 医生挂号统计\"");
			}
			drilldown.put("color", "colors[" + i + "]");
			List<String> drilldown_categories = new ArrayList<String>();
			List<Integer> drilldown_data = new ArrayList<Integer>();
			while (rs2.next()) {
				String child_department_name = rs2.getString("department_name");
				if (department_name.equalsIgnoreCase(child_department_name)) {
					String body_part = rs2.getString("doctor_name");
					drilldown_categories.add("\"" + body_part + "\"");
					drilldown_data.add(rs2.getInt("count(0)"));
				}
			}
			drilldown.put("categories", drilldown_categories);
			drilldown.put("data", drilldown_data);
			modality.put("drilldown", drilldown);
			data.add(modality);
			rs2.beforeFirst();
			i++;
			if (i >= 10) {
				i = 0;
			}
		}
		data.add(categories);
		String sql2 = "SELECT COUNT(0) FROM app_reg_orders WHERE type = ? AND (create_date BETWEEN ? AND ?)";
		int count = getJdbcTemplate().queryForObject(sql2, Integer.class, type, start, end);
		data.add(count);
		return data;
	}

	@Override
	public List<Object> getRegistrationException(String start, String end) {
		String sql = "SELECT `status`, COUNT(0) FROM (SELECT RIGHT(`status`, 3) AS `status` FROM app_reg_orders WHERE `status` IS NOT NULL AND create_date BETWEEN ? AND ?) tmp GROUP BY `status`";
		SqlRowSet rs = getJdbcTemplate().queryForRowSet(sql, start, end);
		Map<String, String> cate = new HashMap<String, String>();
		cate.put("011", "已提交");
		cate.put("021", "已取消");
		cate.put("031", "已停诊");
		cate.put("041", "已取号");
		cate.put("051", "已爽约");
		cate.put("061", "已逾期");
		List<Object> data = new ArrayList<Object>();
		while (rs.next()) {
			List<Object> row = new ArrayList<Object>(2);
			row.add("\"" + cate.get(rs.getString("status")) + "\"");
			row.add(rs.getInt("COUNT(0)"));
			data.add(row);
		}
		String sql2 = "SELECT COUNT(0) FROM app_reg_orders WHERE `status` IS NOT NULL AND create_date BETWEEN ? AND ?";
		Integer count = getJdbcTemplate().queryForObject(sql2, Integer.class, start, end);
		data.add(count);
		return data;
	}

	@Override
	public Map<String, Object> chartTableView() {
		String sql = "SELECT b.department_id, b.department_name, c.doctor_id, c.doctor_name FROM department_doctors a LEFT JOIN departments b ON a.department_id = b.department_id LEFT JOIN doctors c ON a.doctor_id = c.doctor_id ORDER BY b.department_id, c.doctor_id";
		SqlRowSet rs = getJdbcTemplate().queryForRowSet(sql);
		Map<String, Object> data = new HashMap<String, Object>();
		List<Object> departments = new LinkedList<Object>();
		List<Object> doctors = new LinkedList<Object>();
		String lastDepartmentId = null;
		String thisDepartmentId = null;
		while (rs.next()) {
			thisDepartmentId = rs.getString("department_id");
			if (!thisDepartmentId.equals(lastDepartmentId)) {
				Map<String, Object> department = new HashMap<String, Object>();
				department.put("department_id", thisDepartmentId);
				department.put("department_name", rs.getString("department_name"));
				departments.add(department);
			}
			Map<String, Object> doctor = new HashMap<String, Object>();
			doctor.put("department_id", thisDepartmentId);
			doctor.put("doctor_id", rs.getString("doctor_id"));
			doctor.put("doctor_name", rs.getString("doctor_name"));
			doctors.add(doctor);

			lastDepartmentId = thisDepartmentId;
		}
		data.put("departments", departments);
		data.put("doctors", doctors);

		return data;
	}

	@Override
	public List<Map<String, Object>> chartTableData(String dep, String doc, String start, String end) {
		String tempsql = "";
		List<Object> args = new LinkedList<Object>();
		args.add(start);
		args.add(end);
		if (!dep.equals("all")) {
			tempsql += " AND department_id = ? ";
			args.add(dep);
		}
		if (!doc.equals("all")) {
			tempsql += " AND doctor_id = ? ";
			args.add(doc);
		}
		String sql = "SELECT department_id, department_name, doctor_id, doctor_name, SUM(IF(`STATUS` = '011', 1, 0)) AS '011',SUM(IF(`STATUS` = '021', 1, 0)) AS '021',SUM(IF(`STATUS` = '031', 1, 0)) AS '031',SUM(IF(`STATUS` = '041', 1, 0)) AS '041', SUM(IF(`STATUS` = '051', 1, 0)) AS '051',SUM(IF(`STATUS` = '061', 1, 0)) AS '061', COUNT(`status`) AS 'count' FROM (SELECT department_id, department_name, doctor_id, doctor_name, RIGHT(`status`, 3) AS `status` FROM app_reg_orders WHERE `status` IS NOT NULL AND create_date BETWEEN ? AND ? "
				+ tempsql + " ORDER BY create_date)tmp GROUP BY department_id, doctor_id;";
		return getJdbcTemplate().queryForList(sql, args.toArray());
	}

	@Override
	public List<TitlePO> getTitle() {
		try{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql = "SELECT id,title FROM questionnaire";
			List<TitlePO> lst = jt.query(sql, new TitlePO());
			return lst;
		}catch(DataAccessException ex){
			//log.error(null, ex);
			throw ex;
		}
	}

	@Override
	public List<ShowQuesPO> findQuestionsById(String qnId) {
		try {
			String sql ="SELECT qn.title,  q.* FROM question q LEFT JOIN questionnaire qn ON q.questionnaire_id = qn.id WHERE q.questionnaire_id = ?";
			return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<ShowQuesPO>(ShowQuesPO.class),qnId);
		} catch (DataAccessException ex) {
			// log.error(null, ex);
			return null;
		}
		
	}

	@Override
	public List<AnswerPO> findFeedBackById(String qnId, String start,
			String end) {
		try {
			String sql = "SELECT question_id, sum(IF(suggest = 0, 1 ,0)) AS suggest0,sum(IF(suggest = 1, 1 ,0)) AS suggest1,sum(IF(suggest = 2, 1 ,0)) AS suggest2,"
					+ "sum(IF(suggest = 3, 1 ,0)) AS suggest3,sum(IF(suggest = 4, 1 ,0)) AS suggest4,sum(IF(suggest = 5, 1 ,0)) AS suggest5, "
					+ "sum(IF(suggest = 6, 1 ,0)) AS suggest6,sum(IF(suggest = 7, 1 ,0)) AS suggest7,sum(IF(suggest = 8, 1 ,0)) AS suggest8, "
					+ "sum(IF(suggest = 9, 1 ,0)) AS suggest9, COUNT(suggest) AS suggest_count FROM  feedback WHERE createtime >=? AND createtime <=? AND questionnaire_id = ? GROUP BY question_id ORDER BY question_id ASC;";
			
			return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<AnswerPO>(AnswerPO.class),start,end,qnId);
		} catch (DataAccessException ex) {
			// log.error(null, ex);
			return null;
		}
	}
}
