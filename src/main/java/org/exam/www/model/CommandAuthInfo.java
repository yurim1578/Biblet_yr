package org.exam.www.model;

//세션유지

public class CommandAuthInfo {
	private int mem_num;
	private String mem_id;
	private String mem_name;

	public CommandAuthInfo(int mem_num, String mem_id, String mem_name) {
		this.mem_num = mem_num;
		this.mem_id = mem_id;
		this.mem_name = mem_name;
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

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

}
