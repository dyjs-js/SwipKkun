package test.product.service;

import org.apache.commons.lang3.builder.ToStringBuilder;

import test.example.sample.service.DefaultVO;

public class ProductVO extends DefaultVO {
	
	private static final long serialVersionUID = -3648864362909943790L;

	private int product_idx;
	
	private String product_name;
	
	private String product_eng_name;
	
	private int product_price;
	
	private String product_img;
	
	private String product_address;
	
	private String product_content;
	
	private String product_hash_tag;
	
	private Double review_score_avg;
	
	private String reg_date;
	
	private String edit_date;
	
	private String product_status;
	
	private int product_review_idx;
	
	private int review_score;
	
	private String review_content;
	
	private String review_writer;
	
	private String product_review_status;

	public int getProduct_idx() {
		return product_idx;
	}
	
	public void setProduct_idx(int product_idx) {
		this.product_idx = product_idx;
	}
	
	public String getProduct_name() {
		return product_name;
	}
	
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	
	public String getProduct_eng_name() {
		return product_eng_name;
	}

	public void setProduct_eng_name(String product_eng_name) {
		this.product_eng_name = product_eng_name;
	}

	public int getProduct_price() {
		return product_price;
	}
	
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	
	public String getProduct_img() {
		return product_img;
	}

	public void setProduct_img(String product_img) {
		this.product_img = product_img;
	}

	public String getProduct_address() {
		return product_address;
	}

	public void setProduct_address(String product_address) {
		this.product_address = product_address;
	}

	public String getProduct_content() {
		return product_content;
	}

	public void setProduct_content(String product_content) {
		this.product_content = product_content;
	}

	public String getProduct_hash_tag() {
		return product_hash_tag;
	}

	public void setProduct_hash_tag(String product_hash_tag) {
		this.product_hash_tag = product_hash_tag;
	}

	public Double getReview_score_avg() {
		return review_score_avg;
	}
	
	public void setReview_score_avg(Double review_score_avg) {
		this.review_score_avg = review_score_avg;
	}
	
	public String getReg_date() {
		return reg_date;
	}
	
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	
	public String getEdit_date() {
		return edit_date;
	}
	
	public void setEdit_date(String edit_date) {
		this.edit_date = edit_date;
	}
	
	public String getProduct_status() {
		return product_status;
	}
	
	public void setProduct_status(String product_status) {
		this.product_status = product_status;
	}
	
	public int getProduct_review_idx() {
		return product_review_idx;
	}
	
	public void setProduct_review_idx(int product_review_idx) {
		this.product_review_idx = product_review_idx;
	}
	
	public int getReview_score() {
		return review_score;
	}
	
	public void setReview_score(int review_score) {
		this.review_score = review_score;
	}
	
	public String getReview_content() {
		return review_content;
	}
	
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	
	public String getReview_writer() {
		return review_writer;
	}
	
	public void setReview_writer(String review_writer) {
		this.review_writer = review_writer;
	}
	
	public String getProduct_review_status() {
		return product_review_status;
	}
	
	public void setProduct_review_status(String product_review_status) {
		this.product_review_status = product_review_status;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
