package test.product.service;

import test.example.sample.service.DefaultVO;

public class ProductVO extends DefaultVO {
	
	private static final long serialVersionUID = -3648864362909943790L;
	
	private int product_idx; // int
	
	private String product_name; // varchar(45)
	
	private int member_id; // int
	
	private String nickname; // int
	
	private String product_price; // int
	
	private String product_img; // text
	
	private String product_address; // text
	
	private String product_content; // text
	
	private String precaution; // text
	
	private String product_hash_tag; // text
	
	private int hit_cnt; // int
	
	private String created_date;
	
	private String updated_date;
	
	private String product_status;
	
	private int product_review_idx;
	
	private int review_cnt;
	
	private int rental_review_score;
	
	private String rental_review_content;
	
	private String rental_review_score_1_per;
	
	private String rental_review_score_2_per;
	
	private String rental_review_score_3_per;
	
	private String rental_review_score_4_per;
	
	private String rental_review_score_5_per;
	
	private int rental_review_score_avg;
	
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
	
	public int getMember_id() {
		return member_id;
	}
	
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getProduct_price() {
		return product_price;
	}
	
	public void setProduct_price(String product_price) {
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
	
	public String getPrecaution() {
		return precaution;
	}
	
	public void setPrecaution(String precaution) {
		this.precaution = precaution;
	}
	
	public String getProduct_hash_tag() {
		return product_hash_tag;
	}
	
	public void setProduct_hash_tag(String product_hash_tag) {
		this.product_hash_tag = product_hash_tag;
	}
	
	public String getCreated_date() {
		return created_date;
	}
	
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	
	public String getUpdated_date() {
		return updated_date;
	}
	
	public void setUpdated_date(String updated_date) {
		this.updated_date = updated_date;
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
	
	public int getReview_cnt() {
		return review_cnt;
	}
	
	public void setReview_cnt(int review_cnt) {
		this.review_cnt = review_cnt;
	}
	
	public int getRental_review_score() {
		return rental_review_score;
	}
	
	public void setReview_score(int rental_review_score) {
		this.rental_review_score = rental_review_score;
	}
	
	public String getRental_review_content() {
		return rental_review_content;
	}
	
	public void setReview_content(String rental_review_content) {
		this.rental_review_content = rental_review_content;
	}

	public int getHit_cnt() {
		return hit_cnt;
	}

	public void setHit_cnt(int hit_cnt) {
		this.hit_cnt = hit_cnt;
	}

	public String getRental_review_score_1_per() {
		return rental_review_score_1_per;
	}

	public void setRental_review_score_1_per(String rental_review_score_1_per) {
		this.rental_review_score_1_per = rental_review_score_1_per;
	}

	public String getRental_review_score_2_per() {
		return rental_review_score_2_per;
	}

	public void setRental_review_score_2_per(String rental_review_score_2_per) {
		this.rental_review_score_2_per = rental_review_score_2_per;
	}

	public String getRental_review_score_3_per() {
		return rental_review_score_3_per;
	}

	public void setRental_review_score_3_per(String rental_review_score_3_per) {
		this.rental_review_score_3_per = rental_review_score_3_per;
	}

	public String getRental_review_score_4_per() {
		return rental_review_score_4_per;
	}

	public void setRental_review_score_4_per(String rental_review_score_4_per) {
		this.rental_review_score_4_per = rental_review_score_4_per;
	}

	public String getRental_review_score_5_per() {
		return rental_review_score_5_per;
	}

	public void setRental_review_score_5_per(String rental_review_score_5_per) {
		this.rental_review_score_5_per = rental_review_score_5_per;
	}

	public void setRental_review_score(int rental_review_score) {
		this.rental_review_score = rental_review_score;
	}

	public void setRental_review_content(String rental_review_content) {
		this.rental_review_content = rental_review_content;
	}

	public int getRental_review_score_avg() {
		return rental_review_score_avg;
	}

	public void setRental_review_score_avg(int rental_review_score_avg) {
		this.rental_review_score_avg = rental_review_score_avg;
	}
	
	
}
