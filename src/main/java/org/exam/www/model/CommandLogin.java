package org.exam.www.model;

import org.hibernate.validator.constraints.NotEmpty;

public class CommandLogin {
	
	private int mem_num;
	private String mem_id;
	private String mem_pass;
	private String mem_name;
	private boolean rememberId;
	private int authstatus;

	public void CommandAuthInfo(int mem_num, String mem_id, String mem_name, int authstatus) {
		this.mem_num = mem_num;
		this.mem_id = mem_id;
		this.mem_name = mem_name;
		this.authstatus = authstatus;
	}
	
	public int getMem_num() {
		return mem_num;
	}

	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
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

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public boolean isRememberId() {
		return rememberId;
	}

	public void setRememberId(boolean rememberId) {
		this.rememberId = rememberId;
	}

	public int getAuthstatus() {
		return authstatus;
	}

	public void setAuthstatus(int authstatus) {
		this.authstatus = authstatus;
	}
	
}
