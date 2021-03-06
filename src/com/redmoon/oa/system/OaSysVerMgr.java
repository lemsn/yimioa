package com.redmoon.oa.system;

import com.cloudwebsoft.framework.db.JdbcTemplate;
import com.redmoon.oa.post.PostDb;

import java.sql.SQLException;
import java.util.Date;


/**
 * @Description:
 * @author:
 * @Date: 2016-2-23下午03:13:09
 */

public class OaSysVerMgr {
	private int id;
	private String dd_accesstoken;
	private Date  dd_accesstoken_time;
	private String weixin_accesstoken;
	private Date weixin_accesstoken_time;
	private String dd_jspapi_ticket;
	private Date dd_jspapi_ticket_time;

	public String getDd_jspapi_ticket() {
		return dd_jspapi_ticket;
	}

	public void setDd_jspapi_ticket(String dd_jspapi_ticket) {
		this.dd_jspapi_ticket = dd_jspapi_ticket;
	}

	public Date getDd_jspapi_ticket_time() {
		return dd_jspapi_ticket_time;
	}

	public void setDd_jspapi_ticket_time(Date dd_jspapi_ticket_time) {
		this.dd_jspapi_ticket_time = dd_jspapi_ticket_time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDd_accesstoken() {
		return dd_accesstoken;
	}

	public void setDd_accesstoken(String dd_accesstoken) {
		this.dd_accesstoken = dd_accesstoken;
	}

	public Date getDd_accesstoken_time() {
		return dd_accesstoken_time;
	}

	public void setDd_accesstoken_time(Date dd_accesstoken_time) {
		this.dd_accesstoken_time = dd_accesstoken_time;
	}

	public String getWeixin_accesstoken() {
		return weixin_accesstoken;
	}

	public void setWeixin_accesstoken(String weixin_accesstoken) {
		this.weixin_accesstoken = weixin_accesstoken;
	}

	public Date getWeixin_accesstoken_time() {
		return weixin_accesstoken_time;
	}

	public void setWeixin_accesstoken_time(Date weixin_accesstoken_time) {
		this.weixin_accesstoken_time = weixin_accesstoken_time;
	}

	public OaSysVerMgr getOaSysVer() {
		OaSysVerMgr oaSysVerMgr = null;
		OaSysVerDb db = new OaSysVerDb();
		db = db.getOaSysVerDb(1);
		if (db != null) {
			oaSysVerMgr = new OaSysVerMgr();
			oaSysVerMgr.dd_accesstoken = db.getString("dd_accesstoken");
			oaSysVerMgr.dd_accesstoken_time = db.getDate("dd_accesstoken_time");
			oaSysVerMgr.weixin_accesstoken = db.getString("weixin_accesstoken");
			oaSysVerMgr.weixin_accesstoken_time = db.getDate("weixin_accesstoken_time");
			oaSysVerMgr.dd_jspapi_ticket = db.getString("dd_jspapi_ticket");
			oaSysVerMgr.dd_jspapi_ticket_time =db.getDate("dd_jspapi_ticket_time");
		}
		return oaSysVerMgr;
	}

	public boolean updateDingDingAccToken(String dd_accesstoken,Date dd_accesstoken_time){
		boolean flag = false;
		try {
			JdbcTemplate jt = new JdbcTemplate();
			String sql = "update oa_sys_ver set dd_accesstoken = ?,dd_accesstoken_time =? where id = 1";
			int _res = jt.executeUpdate(sql, new Object[] { dd_accesstoken, dd_accesstoken_time });
			flag  = _res>0?true:false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 更新钉钉jsp 页面临时 票据单号
	 * @param dd_jspapi_ticket
	 * @param dd_jspapi_ticket_time
	 * @return
	 */
	public boolean updateDingDingTicket(String dd_jspapi_ticket,Date dd_jspapi_ticket_time){
		boolean flag = false;
		try {
			JdbcTemplate jt = new JdbcTemplate();
			String sql = "update oa_sys_ver set dd_jspapi_ticket = ?,dd_jspapi_ticket_time =? where id = 1";
			int _res = jt.executeUpdate(sql, new Object[] { dd_accesstoken, dd_accesstoken_time });
			flag  = _res>0?true:false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

}
