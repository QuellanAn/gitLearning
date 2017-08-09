package com.catic.mobilehos.pay.action;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.tools.ant.types.CommandlineJava.SysProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.catic.mobilehos.pay.entity.PayLog;

/*
 * 执行日志
 */
@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class PayLogAction extends BaseAction {

	private List<PayLog> plList;
	private PayLog pl;
	private String orderId;
	private String out_trade_no;
	private String payStatus;

	@SuppressWarnings("finally")
	public String findLog() {

		try {
			pl = new PayLog();
			pl.setOut_trade_no(out_trade_no);
			plList = payLogBiz.findPayLog(pl);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return SUCCESS;
		}
	}

	public static void main(String[] args) {
		String json = "json={\"virtualCustId\":\"292744\"},{\"totalFee\":0,\"status\":\"1\",\"provOrderId\":\"5017051257016995\",\"virtualUserId\":\"5017051267090019\",\"virtualAcctId\":\"5017051211684073\",\"numberInfo\":[{\"numberState\":\"0\",\"number\":\"0898XN1267090019\",\"subOrderId\":\"5017051257016995\"},{\"numberState\":\"0\",\"number\":\"13016298011\",\"subOrderId\":\"5017051257016996\"}]}";
		JSONObject js = JSONObject.fromObject(json);
		System.out.println(js.toString());
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public List<PayLog> getPlList() {
		return plList;
	}

	public void setPlList(List<PayLog> plList) {
		this.plList = plList;
	}

	public PayLog getPl() {
		return pl;
	}

	public void setPl(PayLog pl) {
		this.pl = pl;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

}
