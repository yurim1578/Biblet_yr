package org.exam.www.model;

public class BookshelfVO {

	private int book_status_num;
	private int mem_num;
	private String isbn;
	private String book_status;
	
	public BookshelfVO() {}
	
	public BookshelfVO(int mem_num, String isbn, String book_status) {
		
		this.mem_num = mem_num;
		this.isbn = isbn;
		this.book_status = book_status;
	}
	
	public int getBook_status_num() {
		return book_status_num;
	}
	public void setBook_status_num(int book_status_num) {
		this.book_status_num = book_status_num;
	}
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
	public String getBook_status() {
		return book_status;
	}
	public void setBook_status(String book_status) {
		this.book_status = book_status;
	}
	
	@Override
	public String toString() {
		return "BookshelfVO [mem_num=" + mem_num + ", isbn=" + isbn + ", book_status=" + book_status + "]";
	}
	
	
}
