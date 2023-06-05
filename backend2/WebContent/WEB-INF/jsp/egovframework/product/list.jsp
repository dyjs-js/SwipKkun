<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt"     uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
	Date todaySelf = new Date();
	SimpleDateFormat formatSelf = new SimpleDateFormat ( "yyyy_MM_dd_HH_mm_ss");
	
	String searchKeyword = request.getParameter("searchKeyword");
	if(searchKeyword == null) searchKeyword = "";
%>
<!DOCTYPE HTML>
<!--[if lt IE 7 ]>
    <html class="ie6 no-js oldie">
<![endif]-->
<!--[if IE 7 ]>
    <html class="ie7 no-js oldie">
<![endif]-->
<!--[if IE 8 ]>
    <html class="ie8 no-js oldie">
<![endif]-->
<!--[if IE 9 ]>
    <html class="ie9 no-js">
<![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html class="no-js" lang="ko">
<!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<meta name="Description" content="상품목록" />
<meta name="format-detection" content="telephone=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" />
<title>상품목록</title>
<link href="${pageContext.request.contextPath}/assets/css/common.css?dt=<%=formatSelf.format(todaySelf)%>" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/assets/js/jquery-3.6.0.min.js?dt=<%=formatSelf.format(todaySelf)%>"></script>
<link href="${pageContext.request.contextPath}/assets/css/sample.css?dt=<%=formatSelf.format(todaySelf)%>" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/assets/css/product.css?dt=<%=formatSelf.format(todaySelf)%>" rel="stylesheet" />
<style type="text/css">
.select2-container {
	top: -4px;
	left: -6px;
}

.select2-container .select2-selection--single {
	height: 26px;
}

#searchKeyword {
	border:0px !important;
}

.searchRagion {
	vertical-align:middle;
}

#paging strong {
	color:red;
}

#paging strong, #paging a {
	padding-left:5px;
	padding-right:5px; 
}
</style>

<script type="text/javascript">
	$(document).ready(function(){
		$("#regionBtn").on('click', function(){
			if($("#regionBtn2").css("display") == "none") {
				$("#regionBtn2").css("display", "block");
				$(".searchProductArea").css("height", "100px");
			} else {
				$("#regionBtn2").css("display", "none");
				$(".searchProductArea").css("height", "60px");
			}
		});
	});

	function searchSubmit() {
		$("#searchForm").submit();
	}
	
	function searchMike() {
		
	}
	
	// 페이징 이동
	function fn_egov_link_page(pageNo){
		$("#pageIndex").val(pageNo);
		$("#searchForm").submit();
	}
</script>

</head>
<body>
	<div class="container" style="padding-top:30px;">
		<form name="searchForm" id="searchForm" action="${pageContext.request.contextPath}/product/list.do">
			<input type="hidden" name="searchOrderBy" value="${param.searchOrderBy}" />
			<input type="hidden" name="searchOrderGb" value="${param.searchOrderGb}" />
			<input type="hidden" id="pageIndex" name="pageIndex" value="1" />
			
			<div class="searchProductBigArea">
				<div>
					<div class="lf_big">
						<div>
							<img src="${pageContext.request.contextPath}/assets/img/list_top.png" />
						</div>
						<c:if test="${param.searchRagion != '' && param.searchRagion != null}">
						<div class="searchProductArea" style="height:100px;">
						</c:if>
						<c:if test="${param.searchRagion == '' || param.searchRagion == null}">
						<div class="searchProductArea">
						</c:if>
							<div class="lf">
								<input type="button" id="regionBtn" value="지역" style="width:80px; height:30px;" />
								<input type="text" id="searchKeyword" name="searchKeyword" class="searchKeyword" value="${param.searchKeyword}" />
								<c:if test="${param.searchRagion == '' || param.searchRagion == null}">
								<div id="regionBtn2" style="display:none;">
									<input type="radio" id="searchRagion1" class="searchRagion" name="searchRagion" value="" <c:if test="${param.searchRagion == ''}"> checked</c:if> /> <label for="searchRagion1">전체</label>
									<input type="radio" id="searchRagion2" class="searchRagion" name="searchRagion" value="서울" <c:if test="${param.searchRagion == '서울'}"> checked</c:if> /> <label for="searchRagion2">서울</label>
									<input type="radio" id="searchRagion3" class="searchRagion" name="searchRagion" value="경기도" <c:if test="${param.searchRagion == '경기도'}"> checked</c:if> /> <label for="searchRagion3">경기도</label>
									<input type="radio" id="searchRagion4" class="searchRagion" name="searchRagion" value="전라북도" <c:if test="${param.searchRagion == '전라북도'}"> checked</c:if> /> <label for="searchRagion4">전라북도</label>
									<input type="radio" id="searchRagion5" class="searchRagion" name="searchRagion" value="전라남도" <c:if test="${param.searchRagion == '전라남도'}"> checked</c:if> /> <label for="searchRagion5">전라남도</label>
									<input type="radio" id="searchRagion6" class="searchRagion" name="searchRagion" value="경상남도" <c:if test="${param.searchRagion == '경상남도'}"> checked</c:if> /> <label for="searchRagion6">경상남도</label>
									<input type="radio" id="searchRagion7" class="searchRagion" name="searchRagion" value="경상북도" <c:if test="${param.searchRagion == '경상북도'}"> checked</c:if> /> <label for="searchRagion7">경상북도</label>
									<input type="radio" id="searchRagion8" class="searchRagion" name="searchRagion" value="제주도" <c:if test="${param.searchRagion == '제주도'}"> checked</c:if> /> <label for="searchRagion8">제주도</label>
									<input type="radio" id="searchRagion9" class="searchRagion" name="searchRagion" value="충청남도" <c:if test="${param.searchRagion == '충청남도'}"> checked</c:if> /> <label for="searchRagion9">충청남도</label>
									<input type="radio" id="searchRagion10" class="searchRagion" name="searchRagion" value="충청북도" <c:if test="${param.searchRagion == '충청북도'}"> checked</c:if> /> <label for="searchRagion10">충청북도</label>
									<input type="radio" id="searchRagion11" class="searchRagion" name="searchRagion" value="울릉도" <c:if test="${param.searchRagion == '울릉도'}"> checked</c:if> /> <label for="searchRagion11">울릉도</label>
									<input type="radio" id="searchRagion12" class="searchRagion" name="searchRagion" value="인천광역시" <c:if test="${param.searchRagion == '인천광역시'}"> checked</c:if> /> <label for="searchRagion12">인천광역시</label>
									<input type="radio" id="searchRagion13" class="searchRagion" name="searchRagion" value="광주광역시" <c:if test="${param.searchRagion == '광주광역시'}"> checked</c:if> /> <label for="searchRagion13">광주광역시</label>
								</div>
								</c:if>
								<c:if test="${param.searchRagion != '' && param.searchRagion != null}">
								<div id="regionBtn2">
									<input type="radio" id="searchRagion1" class="searchRagion" name="searchRagion" value="" <c:if test="${param.searchRagion == ''}"> checked</c:if> /> <label for="searchRagion1">전체</label>
									<input type="radio" id="searchRagion2" class="searchRagion" name="searchRagion" value="서울" <c:if test="${param.searchRagion == '서울'}"> checked</c:if> /> <label for="searchRagion2">서울</label>
									<input type="radio" id="searchRagion3" class="searchRagion" name="searchRagion" value="경기도" <c:if test="${param.searchRagion == '경기도'}"> checked</c:if> /> <label for="searchRagion3">경기도</label>
									<input type="radio" id="searchRagion4" class="searchRagion" name="searchRagion" value="전라북도" <c:if test="${param.searchRagion == '전라북도'}"> checked</c:if> /> <label for="searchRagion4">전라북도</label>
									<input type="radio" id="searchRagion5" class="searchRagion" name="searchRagion" value="전라남도" <c:if test="${param.searchRagion == '전라남도'}"> checked</c:if> /> <label for="searchRagion5">전라남도</label>
									<input type="radio" id="searchRagion6" class="searchRagion" name="searchRagion" value="경상남도" <c:if test="${param.searchRagion == '경상남도'}"> checked</c:if> /> <label for="searchRagion6">경상남도</label>
									<input type="radio" id="searchRagion7" class="searchRagion" name="searchRagion" value="경상북도" <c:if test="${param.searchRagion == '경상북도'}"> checked</c:if> /> <label for="searchRagion7">경상북도</label>
									<input type="radio" id="searchRagion8" class="searchRagion" name="searchRagion" value="제주도" <c:if test="${param.searchRagion == '제주도'}"> checked</c:if> /> <label for="searchRagion8">제주도</label>
									<input type="radio" id="searchRagion9" class="searchRagion" name="searchRagion" value="충청남도" <c:if test="${param.searchRagion == '충청남도'}"> checked</c:if> /> <label for="searchRagion9">충청남도</label>
									<input type="radio" id="searchRagion10" class="searchRagion" name="searchRagion" value="충청북도" <c:if test="${param.searchRagion == '충청북도'}"> checked</c:if> /> <label for="searchRagion10">충청북도</label>
									<input type="radio" id="searchRagion11" class="searchRagion" name="searchRagion" value="울릉도" <c:if test="${param.searchRagion == '울릉도'}"> checked</c:if> /> <label for="searchRagion11">울릉도</label>
									<input type="radio" id="searchRagion12" class="searchRagion" name="searchRagion" value="인천광역시" <c:if test="${param.searchRagion == '인천광역시'}"> checked</c:if> /> <label for="searchRagion12">인천광역시</label>
									<input type="radio" id="searchRagion13" class="searchRagion" name="searchRagion" value="광주광역시" <c:if test="${param.searchRagion == '광주광역시'}"> checked</c:if> /> <label for="searchRagion13">광주광역시</label>
								</div>
								</c:if>
							</div>
							<div class="rf_1">
								<a href="javascript:;" onclick="javascript:searchSubmit();"><img src="${pageContext.request.contextPath}/assets/img/center_search_btn.png" /></a>
							</div>
							<div class="rf_2">
								<a href="javascript:;" onclick="javascript:searchMike();"><img src="${pageContext.request.contextPath}/assets/img/mike_btn.png" /></a>
							</div>
						</div>
					</div>
					<div class="rf_big">
						<div>
							<img src="${pageContext.request.contextPath}/assets/img/search_btn.png" />
						</div>
						<div>
							<img src="${pageContext.request.contextPath}/assets/img/mypage_btn.png" />
						</div>
						<div>
							<img src="${pageContext.request.contextPath}/assets/img/order_btn.png" />
						</div>
						<div>
							<img src="${pageContext.request.contextPath}/assets/img/order2_btn.png" />
						</div>
					</div>
					<div style="clear:both;"></div>
				</div>
				
				<div id="sortListArea">
					<ul id="sortListUL">
						<c:if test="${param.searchOrderBy == 'created_date' || param.searchOrderBy == '' || param.searchOrderBy == null}">
							<c:choose>
								<c:when test="${param.searchOrderGb == '' || param.searchOrderGb == null || param.searchOrderGb == 'asc'}">
									<c:set var="searchOrderGb1" value="DESC" />
								</c:when>
								<c:otherwise>
									<c:set var="searchOrderGb1" value="ASC" />
								</c:otherwise>
							</c:choose>
						</c:if>
						<c:if test="${param.searchOrderBy == 'product_price'}">
							<c:choose>
								<c:when test="${param.searchOrderGb == 'ASC' || param.searchOrderGb == '' || param.searchOrderGb == null}">
									<c:set var="searchOrderGb2" value="DESC" />
								</c:when>
								<c:otherwise>
									<c:set var="searchOrderGb2" value="ASC" />
								</c:otherwise>
							</c:choose>
						</c:if>
						<c:if test="${param.searchOrderBy == 'hit_cnt'}">
							<c:choose>
								<c:when test="${param.searchOrderGb == 'ASC' || param.searchOrderGb == '' || param.searchOrderGb == null}">
									<c:set var="searchOrderGb3" value="DESC" />
								</c:when>
								<c:otherwise>
									<c:set var="searchOrderGb3" value="ASC" />
								</c:otherwise>
							</c:choose>
						</c:if>
						<c:if test="${param.searchOrderBy == 'rental_review_score'}">
							<c:choose>
								<c:when test="${param.searchOrderGb == 'ASC' || param.searchOrderGb == '' || param.searchOrderGb == null}">
									<c:set var="searchOrderGb4" value="DESC" />
								</c:when>
								<c:otherwise>
									<c:set var="searchOrderGb4" value="ASC" />
								</c:otherwise>
							</c:choose>
						</c:if>
						
						<c:choose>
							<c:when test="${param.searchOrderBy != 'created_date' && param.searchOrderBy != 'product_price' && param.searchOrderBy != 'hit_cnt' && param.searchOrderBy != 'rental_review_score'}">
								<c:set var="searchOrderGb1" value="DESC" />
								<c:set var="searchOrderGb2" value="DESC" />
								<c:set var="searchOrderGb3" value="DESC" />
								<c:set var="searchOrderGb4" value="DESC" />
							</c:when>
						</c:choose>
						
						<li><a href="${pageContext.request.contextPath}/product/list.do?searchOrderBy=created_date&searchKeyword=${param.searchKeyword}&searchRagion=${param.searchRagion}&searchOrderGb=${searchOrderGb1}" <c:if test="${param.searchOrderBy == 'created_date'}">class="active"</c:if>>최신순</a></li>
						<li><a href="${pageContext.request.contextPath}/product/list.do?searchOrderBy=product_price&searchKeyword=${param.searchKeyword}&searchRagion=${param.searchRagion}&searchOrderGb=${searchOrderGb2}" <c:if test="${param.searchOrderBy == 'product_price'}">class="active"</c:if>>가격순</a></li>
						<li><a href="${pageContext.request.contextPath}/product/list.do?searchOrderBy=hit_cnt&searchKeyword=${param.searchKeyword}&searchRagion=${param.searchRagion}&searchOrderGb=${searchOrderGb3}" <c:if test="${param.searchOrderBy == 'hit_cnt'}">class="active"</c:if>>조회순</a></li>
						<li><a href="${pageContext.request.contextPath}/product/list.do?searchOrderBy=rental_review_score&searchKeyword=${param.searchKeyword}&searchRagion=${param.searchRagion}&searchOrderGb=${searchOrderGb4}" <c:if test="${param.searchOrderBy == 'rental_review_score'}">class="active"</c:if>>평점순</a></li>
					</ul>
				</div>
			</div>
			
			<div id="gall_ul_top">
				<ul id="gall_ul" class="gall_row">
					<c:forEach var="result" items="${resultList}" varStatus="status">
						<c:set var="indexOdd" value="${status.index+1}" />
						<li class="indexOdd" <c:if test="${status.index+1 > 1 && indexOdd % 4 == 1}">style="clear:both;"</c:if>>
							<a href="${pageContext.request.contextPath}/product/view.do?product_idx=${result.product_idx}">
								<div><img src="${result.product_img}" onerror="this.src='https://s.pstatic.net/static/www/img/uit/2019/sp_search.svg';" /></div>
								<div>${result.product_name}</div>
								<div>1박/<fmt:formatNumber type="number" maxFractionDigits="3" value="${result.product_price}" />원</div>
								<div>&nbsp;&nbsp;</div>
								<div>${result.product_address}</div>
								<div>
									<c:if test="${result.rental_review_score_avg == 5}">
										<img src="${pageContext.request.contextPath}/assets/img/review_score_05.png" />
									</c:if>
									<c:if test="${result.rental_review_score_avg >= 4 && result.rental_review_score_avg < 5}">
										<img src="${pageContext.request.contextPath}/assets/img/review_score_04.png" />
									</c:if>
									<c:if test="${result.rental_review_score_avg >= 3 && result.rental_review_score_avg < 4}">
										<img src="${pageContext.request.contextPath}/assets/img/review_score_03.png" />
									</c:if>
									<c:if test="${result.rental_review_score_avg >= 2 && result.rental_review_score_avg < 3}">
										<img src="${pageContext.request.contextPath}/assets/img/review_score_02.png" />
									</c:if>
									<c:if test="${result.rental_review_score_avg >= 1 && result.rental_review_score_avg < 2}">
										<img src="${pageContext.request.contextPath}/assets/img/review_score_01.png" />
									</c:if>
									<c:if test="${result.rental_review_score_avg == '' || result.rental_review_score_avg == null || result.rental_review_score_avg < 1}">
										<img src="${pageContext.request.contextPath}/assets/img/review_score_00.png" />
									</c:if>
								</div>
							</a>
						</li>
					</c:forEach>
					<c:if test="${fn:length(resultList) == 0}">
						<li style="text-align:center; padding-top:100px; padding-bottom:100px;">등록된 자료가 없습니다.</li>
					</c:if>
				</ul>
			</div>
			
			<div class="clboth" style="text-align:right; width:1200px; margin:0px auto;">
				<input type="button" value="등록" onclick="javascript:location.href = '${pageContext.request.contextPath}/product/insert.do';" />
			</div>
			
			<div class="clboth">
				<div id="paging" role="navigation" class="pagination" aria-live="polite" aria-label="pagination">
					<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_link_page" />
				</div>
			</div>
		</form>
	
	</div>
</body>
</html>
