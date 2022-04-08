package org.exam.www.model;

import java.sql.Date;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class AdministratorVO {

	private int adm_num;
	@NotEmpty(message="필수 입력란 입니다.")
	private String adm_name;
	@NotEmpty(message="필수 입력란 입니다.")
	private String adm_id;
	@NotEmpty(message="필수 입력란 입니다.")
	private String adm_pass;
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm")
	private Date adm_regdate;
	@NotEmpty(message="필수 입력란 입니다.")
	@Email(message="올바른 이메일 형식이 아닙니다.")
	private String adm_email;
	private String adm_authkey;
	private int adm_authstatus;
	@NotEmpty(message="필수 입력란 입니다.")
	private String securitycode;
	
	
	
	public AdministratorVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AdministratorVO(String adm_id, String adm_pass, String adm_email, String securitycode) {
		super();
		this.adm_id = adm_id;
		this.adm_pass = adm_pass;
		this.adm_email = adm_email;
		this.securitycode = securitycode;
	}
	public int getAdm_num() {
		return adm_num;
	}
	public void setAdm_num(int adm_num) {
		this.adm_num = adm_num;
	}
	public String getAdm_name() {
		return adm_name;
	}
	public void setAdm_name(String adm_name) {
		this.adm_name = adm_name;
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
	public Date getAdm_regdate() {
		return adm_regdate;
	}
	public void setAdm_regdate(Date adm_regdate) {
		this.adm_regdate = adm_regdate;
	}
	public String getAdm_email() {
		return adm_email;
	}
	public void setAdm_email(String adm_email) {
		this.adm_email = adm_email;
	}
	public String getAdm_authkey() {
		return adm_authkey;
	}
	public void setAdm_authkey(String adm_authkey) {
		this.adm_authkey = adm_authkey;
	}
	public int getAdm_authstatus() {
		return adm_authstatus;
	}
	public void setAdm_authstatus(int adm_authstatus) {
		this.adm_authstatus = adm_authstatus;
	}
	public String getSecuritycode() {
		return securitycode;
	}
	public void setSecuritycode(String securitycode) {
		this.securitycode = securitycode;
	}
	
	@Override
	public String toString() {
		return "AdministratorVO [adm_num=" + adm_num + ", adm_name=" + adm_name + ", adm_id=" + adm_id + ", adm_pass="
				+ adm_pass + ", adm_regdate=" + adm_regdate + ", adm_email=" + adm_email + ", adm_authkey="
				+ adm_authkey + ", adm_authstatus=" + adm_authstatus + ", securitycode=" + securitycode + "]";
	}
	
	
}
