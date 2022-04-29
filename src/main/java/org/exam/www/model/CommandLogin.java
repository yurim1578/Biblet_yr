package org.exam.www.model;

import org.hibernate.validator.constraints.NotEmpty;

public class CommandLogin {

	@NotEmpty(message="필수 입력란 입니다.")
	private String mem_id;
	@NotEmpty(message="필수 입력란 입니다.")
	private String mem_pass;
	private boolean rememberId;
	private int authstatus;

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
