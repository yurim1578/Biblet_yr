package org.exam.www.model;

import java.sql.Date;

public class BookinfoVO {

	private String isbn;
	private String book_name;
	private String publisher;
	private String author;
	private Date produc_year;
	private int book_page;
	private String book_category;
	private String age_grade;
	private String book_sum;
	private String book_cover;
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getProduc_year() {
		return produc_year;
	}
	public void setProduc_year(Date produc_year) {
		this.produc_year = produc_year;
	}
	public int getBook_page() {
		return book_page;
	}
	public void setBook_page(int book_page) {
		this.book_page = book_page;
	}
	public String getBook_category() {
		return book_category;
	}
	public void setBook_category(String book_category) {
		this.book_category = book_category;
	}
	public String getAge_grade() {
		return age_grade;
	}
	public void setAge_grade(String age_grade) {
		this.age_grade = age_grade;
	}
	public String getBook_sum() {
		return book_sum;
	}
	public void setBook_sum(String book_sum) {
		this.book_sum = book_sum;
	}
	public String getBook_cover() {
		return book_cover;
	}
	public void setBook_cover(String book_cover) {
		this.book_cover = book_cover;
	}
	
	@Override
	public String toString() {
		return "BookinfoVO [isbn=" + isbn + ", book_name=" + book_name + ", publisher=" + publisher + ", author="
				+ author + ", produc_year=" + produc_year + ", book_page=" + book_page + ", book_category="
				+ book_category + ", age_grade=" + age_grade + ", book_sum=" + book_sum + ", book_cover=" + book_cover
				+ "]";
	}
	
	
}
