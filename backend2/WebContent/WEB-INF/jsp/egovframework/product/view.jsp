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
</style>

<script>
$(document).ready(function(){
	$("#editBtn").on('click', function(){
		location.href = "${pageContext.request.contextPath}/product/edit.do?product_idx=" + $("#product_idx").val();
		return;
	});
	
	$("#delBtn").on('click', function(){
		if(confirm("삭제하시겠습니까?")) {
			location.href = "${pageContext.request.contextPath}/product/delete_proc.do?product_idx=" + $("#product_idx").val();
			return;
		}
	});
});
</script>

</head>
<body>
	<div class="container" style="padding-top:30px;">
		<input type="hidden" id="product_idx" name="product_idx" value="${param.product_idx}" />
	
		<div class="productViewArea01">
			<div class="lf">
				<img src="${result.product_img}" />
			</div>
			<div class="rf">
				<div class="first">${result.product_name}</div>
				<div class="second">${result.product_eng_name}</div>
				<div class="third">${result.product_day_bak}박/<fmt:formatNumber type="number" maxFractionDigits="3" value="${result.product_price}" />원</div>
			</div>
		</div>
		
		<div style="clear:both; height:50px;">&nbsp;</div>
		
		<div class="productViewArea02">
			<div class="lf" style="float:left; width:576px; padding:40px;">
				<div style="border-bottom:1px solid #FFB876; padding-bottom:20px; font-size:18pt;">상품설명</div>
				<div style="padding-top:20px; padding-bottom:20px;">${result.product_content}</div>
				<div style="padding-top:20px;">${result.product_hash_tag}</div>
			</div>
			<div class="rf" style="padding-top:20px; float:right; width:476px; text-align:right; padding-right:100px; padding-bottom:50px;">
				<img src="${pageContext.request.contextPath}/assets/img/product_description_02.png" />
			</div>
		</div>
		
		<div style="clear:both;"></div>
		<div style="padding-top:30px; text-align:center;">
			<input type="button" id="editBtn" value="수정" />
			<input type="button" id="delBtn" value="삭제" />
		</div>
		
		<div class="productViewArea03">
			<div class="lf">
				<div style="padding-bottom:20px; font-size:18pt;">리뷰</div>
				<div style="padding-top:20px; padding-bottom:20px;">
					<div style="padding-top:10px; padding-bottom:10px;">
						<c:if test="${result.review_score_avg == 5}">
							<img src="${pageContext.request.contextPath}/assets/img/product/review_info_5.png" /> ${result.review_score_avg}
						</c:if>
						<c:if test="${result.review_score_avg >= 4 && result.review_score_avg < 5}">
							<img src="${pageContext.request.contextPath}/assets/img/product/review_info_4.png" /> ${result.review_score_avg}
						</c:if>
						<c:if test="${result.review_score_avg >= 3 && result.review_score_avg < 4}">
							<img src="${pageContext.request.contextPath}/assets/img/product/review_info_3.png" /> ${result.review_score_avg}
						</c:if>
						<c:if test="${result.review_score_avg >= 2 && result.review_score_avg < 3}">
							<img src="${pageContext.request.contextPath}/assets/img/product/review_info_2.png" /> ${result.review_score_avg}
						</c:if>
						<c:if test="${result.review_score_avg >= 1 && result.review_score_avg < 2}">
							<img src="${pageContext.request.contextPath}/assets/img/product/review_info_1.png" /> ${result.review_score_avg}
						</c:if>
						<c:if test="${result.review_score_avg == '' || result.review_score_avg == null || result.review_score_avg < 1}">
							<img src="${pageContext.request.contextPath}/assets/img/product/review_info_0.png" /> ${result.review_score_avg}
						</c:if>
					</div>
					<div style="padding-top:10px; padding-bottom:10px;">
						<fmt:formatNumber type="number" maxFractionDigits="3" value="${result.review_cnt}" /> Reviews
						
						<table style="width:300px; ">
						<tr height="30px">
							<td style="width:30px;">5점</td>
							<td style="width:240px;">
								<c:choose>
									<c:when test="${result.review_score_5_per > '0'}">
										<table style="width:100%;">
										<tr>
											<td style="width:${result.review_score_5_per}%; background:#FF7B54;">&nbsp;</td>
											<td style="width:${(100 - result.review_score_5_per)}%; background:#E9E9E9;">&nbsp;</td>
										</tr>
										</table>
									</c:when>
									<c:otherwise>
										<table style="width:100%;">
										<tr>
											<td style="width:100%; background:#E9E9E9;">&nbsp;</td>
										</tr>
										</table>
									</c:otherwise>
								</c:choose>
							</td>
							<td style="width:30px; padding-left:5px; text-align:right;">${result.review_score_5_per}%</td>
						</tr>
						<tr height="30px">
							<td style="width:30px;">4점</td>
							<td style="width:240px;">
								<c:choose>
									<c:when test="${result.review_score_4_per > '0'}">
										<table style="width:100%;">
										<tr>
											<td style="width:${result.review_score_4_per}%; background:#FF7B54;">&nbsp;</td>
											<td style="width:${(100 - result.review_score_4_per)}%; background:#E9E9E9;">&nbsp;</td>
										</tr>
										</table>
									</c:when>
									<c:otherwise>
										<table style="width:100%;">
										<tr>
											<td style="width:100%; background:#E9E9E9;">&nbsp;</td>
										</tr>
										</table>
									</c:otherwise>
								</c:choose>
							</td>
							<td style="width:30px; padding-left:5px; text-align:right;">${result.review_score_4_per}%</td>
						</tr>
						<tr height="30px">
							<td style="width:30px;">3점</td>
							<td style="width:240px;">
								<c:choose>
									<c:when test="${result.review_score_3_per > '0'}">
										<table style="width:100%;">
										<tr>
											<td style="width:${result.review_score_3_per}%; background:#FF7B54;">&nbsp;</td>
											<td style="width:${(100 - result.review_score_3_per)}%; background:#E9E9E9;">&nbsp;</td>
										</tr>
										</table>
									</c:when>
									<c:otherwise>
										<table style="width:100%;">
										<tr>
											<td style="width:100%; background:#E9E9E9;">&nbsp;</td>
										</tr>
										</table>
									</c:otherwise>
								</c:choose>
							</td>
							<td style="width:30px; padding-left:5px; text-align:right;">${result.review_score_3_per}%</td>
						</tr>
						<tr height="30px">
							<td style="width:30px;">2점</td>
							<td style="width:240px;">
								<c:choose>
									<c:when test="${result.review_score_2_per > '0'}">
										<table style="width:100%;">
										<tr>
											<td style="width:${result.review_score_2_per}%; background:#FF7B54;">&nbsp;</td>
											<td style="width:${(100 - result.review_score_2_per)}%; background:#E9E9E9;">&nbsp;</td>
										</tr>
										</table>
									</c:when>
									<c:otherwise>
										<table style="width:100%;">
										<tr>
											<td style="width:100%; background:#E9E9E9;">&nbsp;</td>
										</tr>
										</table>
									</c:otherwise>
								</c:choose>
							</td>
							<td style="width:30px; padding-left:5px; text-align:right;">${result.review_score_2_per}%</td>
						</tr>
						<tr height="30px">
							<td style="width:30px;">1점</td>
							<td style="width:240px;">
								<c:choose>
									<c:when test="${result.review_score_1_per > '0'}">
										<table style="width:100%;">
										<tr>
											<td style="width:${result.review_score_1_per}%; background:#FF7B54;">&nbsp;</td>
											<td style="width:${(100 - result.review_score_1_per)}%; background:#E9E9E9;">&nbsp;</td>
										</tr>
										</table>
									</c:when>
									<c:otherwise>
										<table style="width:100%;">
										<tr>
											<td style="width:100%; background:#E9E9E9;">&nbsp;</td>
										</tr>
										</table>
									</c:otherwise>
								</c:choose>
							</td>
							<td style="width:30px; padding-left:5px; text-align:right;">${result.review_score_1_per}%</td>
						</tr>
						</table>
						
					</div>
				</div>
			</div>
			<div class="rf">
				<ul>
					<c:forEach var="review" items="${reviewList}" varStatus="status">
						<li class="indexOdd" style="padding-top:50px; padding-bottom:50px; border-bottom:1px solid #e9e9e9;">
							<div style="float:left">${review.review_writer}<br/>
								<c:if test="${review.review_score == 5}">
									<img src="${pageContext.request.contextPath}/assets/img/small_review_05.png" />
								</c:if>
								<c:if test="${review.review_score >= 4 && review.review_score < 5}">
									<img src="${pageContext.request.contextPath}/assets/img/small_review_04.png" />
								</c:if>
								<c:if test="${review.review_score >= 3 && review.review_score < 4}">
									<img src="${pageContext.request.contextPath}/assets/img/small_review_03.png" />
								</c:if>
								<c:if test="${review.review_score >= 2 && review.review_score < 3}">
									<img src="${pageContext.request.contextPath}/assets/img/small_review_02.png" />
								</c:if>
								<c:if test="${review.review_score >= 1 && review.review_score < 2}">
									<img src="${pageContext.request.contextPath}/assets/img/small_review_01.png" />
								</c:if>
								<c:if test="${review.review_score == '' || review.review_score == null || review.review_score < 1}">
									<img src="${pageContext.request.contextPath}/assets/img/small_review_00.png" />
								</c:if>
							</div>
							<div style="float:right">${review.reg_date}</div>
							<div style="clear:both;"></div>
							<div>${review.review_content}</div>
						</li>
					</c:forEach>
					
					<c:if test="${fn:length(reviewList) == 0}">
						<li style="padding-top:100px; padding-bottom:100px; text-align:center;">
							등록된 리뷰가 없습니다.
						</li>
					</c:if>
				</ul>
				
				<c:if test="${fn:length(reviewList) > 0}">
					<div style="padding-top:50px;">
						<u>See oll review</u>
					</div>
				</c:if>
			</div>
		</div>
		
		<div class="productViewArea04">
			<h2 style="float:left; width:500px;">비슷한 상품</h2>
			<div style="float:right; padding-right:100px;">
				<img src="${pageContext.request.contextPath}/assets/img/product_view_arrow.png" />
			</div>
			<div id="gall_ul_top" style="height:450px !important; overflow:hidden;">
				<ul id="gall_ul" class="gall_row">
					<c:forEach var="resultNot" items="${resultNotList}" varStatus="status">
						<c:set var="indexOdd" value="${status.index+1}" />
						<li class="indexOdd" <c:if test="${status.index+1 > 1 && indexOdd % 4 == 1}">style="clear:both;"</c:if>>
							<a href="${pageContext.request.contextPath}/product/view.do?product_idx=${resultNot.product_idx}">
								<div><img src="${resultNot.product_img}" /></div>
								<div>${result.product_name}</div>
								<div>${result.product_day_bak}박/<fmt:formatNumber type="number" maxFractionDigits="3" value="${resultNot.product_price}" />원</div>
							</a>
						</li>
					</c:forEach>
				</ul>
			</div>
			
			<div style="clear:both; padding-top:100px; padding-right:100px; padding-bottom:100px; text-align:right;">
				<img src="${pageContext.request.contextPath}/assets/img/product_view_arrow.png" />
			</div>
		</div>
	</div>
</body>
</html>
