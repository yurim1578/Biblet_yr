package org.exam.www.model;

import org.hibernate.validator.constraints.NotEmpty;

public class CommandAdminLogin {
	private int adm_num;
	private String adm_id;
	private String adm_pass;
	private String adm_name;
	private boolean rememberAdmId;
	private int adm_authstatus;
	
	public void CommandAdminAuthInfo(int adm_num, String adm_id, String adm_name, int adm_authstatus) {
		this.adm_num = adm_num;
		this.adm_id = adm_id;
		this.adm_name = adm_name;
		this.adm_authstatus = adm_authstatus;
	}

	public int getAdm_num() {
		return adm_num;
	}

	public void setAdm_num(int adm_num) {
		this.adm_num = adm_num;
	}

	public String getAdm_id() {
		return adm_id;
	}

	public void setAdm_id(String adm_id) {
		this.adm_id = adm_id;
	}

	public String getAdm_pass() {
		return adm_pass;
	}

	public void setAdm_pass(String adm_pass) {
		this.adm_pass = adm_pass;
	}

	public String getAdm_name() {
		return adm_name;
	}

	public void setAdm_name(String adm_name) {
		this.adm_name = adm_name;
	}

	public boolean isRememberAdmId() {
		return rememberAdmId;
	}

	public void setRememberAdmId(boolean rememberAdmId) {
		this.rememberAdmId = rememberAdmId;
	}

	public int getAdm_authstatus() {
		return adm_authstatus;
	}

	public void setAdm_authstatus(int adm_authstatus) {
		this.adm_authstatus = adm_authstatus;
	}


	
	

}
