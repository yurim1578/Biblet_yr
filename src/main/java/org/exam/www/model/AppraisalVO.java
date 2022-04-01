package org.exam.www.model;

import java.sql.Date;

public class AppraisalVO {

	private int mem_num;
	private String isbn;
	private String star;
	private String book_comment;
	private Date start_date;
	private Date end_date;
	private String co_prv;
	
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
		this.star = star;
	}
	public String getBook_comment() {
		return book_comment;
	}
	public void setBook_comment(String book_comment) {
		this.book_comment = book_comment;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getCo_prv() {
		return co_prv;
	}
	public void setCo_prv(String co_prv) {
		this.co_prv = co_prv;
	}
	
	@Override
	public String toString() {
		return "AppraisalVO [mem_num=" + mem_num + ", isbn=" + isbn + ", star=" + star + ", book_comment="
				+ book_comment + ", start_date=" + start_date + ", end_date=" + end_date + ", co_prv=" + co_prv + "]";
	}
	
	
}
