package org.exam.www.model;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class MemberVO {

	private int mem_num;//sequence.nextval로 넣을 것
	private String mem_name;//50->한글6글자,영어50자
	private String mem_id;//100->한글33자,영어100자
	private String mem_pass;//200->한글 66자,영어200자
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date mem_regdate;
	private String mem_email;//200->한글 66자,영어200자
	private String mem_pic;//null값이어도 됨
	private String authkey;//null값이어도 됨
	private int authstatus;//default=0 => 인증 후 =1
	
	public MemberVO() {}
	
	public MemberVO(String mem_name,String mem_id,String mem_pass,String mem_email) {
		this.mem_name=mem_name;
		this.mem_id=mem_id;
		this.mem_pass=mem_pass;
		this.mem_email=mem_email;
	}
	
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_pass() {
		return mem_pass;
	}
	public void setMem_pass(String mem_pass) {
		this.mem_pass = mem_pass;
	}
	
	public Date getMem_regdate() {
		return mem_regdate;
	}
	public void setMem_regdate(Date mem_regdate) {
		this.mem_regdate = mem_regdate;
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public String getMem_pic() {
		return mem_pic;
	}
	public void setMem_pic(String mem_pic) {
		this.mem_pic = mem_pic;
	}
	public String getAuthkey() {
		return authkey;
	}
	public void setAuthkey(String authkey) {
		this.authkey = authkey;
	}
	public int getAuthstatus() {
		return authstatus;
	}
	public void setAuthstatus(int authstatus) {
		this.authstatus = authstatus;
	}
	
	@Override
	public String toString() {
		return "MemberVO [mem_num=" + mem_num + ", mem_name=" + mem_name + ", mem_id=" + mem_id + ", mem_pass="
				+ mem_pass + ", mem_regdate=" + mem_regdate + ", mem_email="
				+ mem_email + ", mem_pic=" + mem_pic + ", authkey=" + authkey + ", authstatus=" + authstatus + "]";
	}
	
	
}
