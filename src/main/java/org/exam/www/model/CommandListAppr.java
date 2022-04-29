package org.exam.www.model;

public class CommandListAppr {

	private int appraisal_num;
	private String mem_id;
	private String book_comment;
	private int star;
	private String isbn;
	private String coKeyword;
	private String coOption;
	
	
	public int getAppraisal_num() {
		return appraisal_num;
	}
	public void setAppraisal_num(int appraisal_num) {
		this.appraisal_num = appraisal_num;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getBook_comment() {
		return book_comment;
	}
	public void setBook_comment(String book_comment) {
		this.book_comment = book_comment;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public String getCoKeyword() {
		return coKeyword;
	}
	public void setCoKeyword(String coKeyword) {
		this.coKeyword = coKeyword;
	}
	public String getCoOption() {
		return coOption;
	}
	public void setCoOption(String coOption) {
		this.coOption = coOption;
	}
	@Override
	public String toString() {
		return "CommandListAppr [mem_id=" + mem_id + ", book_comment=" + book_comment + ", star=" + star+
				", isbn=" + isbn + ", CoKeyword=" + coKeyword + ", CoOption=" + coOption + "]";
	}
	
	
	
}
