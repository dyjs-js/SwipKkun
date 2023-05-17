package test.example.sample.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class DefaultVO implements Serializable {

	/**
	 *  serialVersion UID
	 */
	private static final long serialVersionUID = -858838578081269359L;

	/** 검색조건 */
	private String searchCondition;

	/** 검색word */
	private String searchWord;
	
	/** 검색Keyword */
	private String searchKeyword;
	
	/** 검색Keyword Encode */
	private String searchKeywordEncode;
	
	/** 정렬 검색 */
	private String searchOrderBy;
	
	/** 정렬 검색 */
	private String searchOrderGb;
	
	/** 지역 선택 */
	private String searchRagion;

	/** 검색사용여부 */
	private String searchUseYn;

	/** 현재페이지 */
	private int pageIndex = 1;

	/** 페이지갯수 */
	private int pageUnit = 10;

	/** 페이지사이즈 */
	private int pageSize = 10;

	/** firstIndex */
	private int firstIndex = 1;

	/** lastIndex */
	private int lastIndex = 1;
	
	/** 정렬옵션 */
	private String searchOrder;
	
	/** 정렬타입 */
	private String typeFilter = "no";
	
	/** recordCountPerPage */
	private int recordCountPerPage = 10;

	/** page url*/
	private String pageurl;
	
	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	
	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public String getSearchKeywordEncode() {
		return searchKeywordEncode;
	}

	public void setSearchKeywordEncode(String searchKeywordEncode) {
		this.searchKeywordEncode = searchKeywordEncode;
	}

	public String getSearchOrderBy() {
		return searchOrderBy;
	}

	public void setSearchOrderBy(String searchOrderBy) {
		this.searchOrderBy = searchOrderBy;
	}
	
	public String getSearchOrderGb() {
		return searchOrderGb;
	}

	public void setSearchOrderGb(String searchOrderGb) {
		this.searchOrderGb = searchOrderGb;
	}
	
	public String getSearchRagion() {
		return searchRagion;
	}
	
	public void setSearchRagion(String searchRagion) {
		this.searchRagion = searchRagion;
	}

	public String getSearchUseYn() {
		return searchUseYn;
	}

	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageUnit() {
		return pageUnit;
	}

	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
	public String getSearchOrder() {
		return searchOrder;
	}

	public void setSearchOrder(String searchOrder) {
		this.searchOrder = searchOrder;
	}
	

	public String getTypeFilter() {
		return typeFilter;
	}

	public void setTypeFilter(String typeFilter) {
		this.typeFilter = typeFilter;
	}
	
	public String getPageurl() {
		return pageurl;
	}

	public void setPageurl(String pageurl) {
		this.pageurl = pageurl;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
