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
	$("#insertBtn").on('click', function(){
		$("#insertFrm").submit();
	});
	
	$("#fileDelBtn").on('click', function(){
		if($(this).attr("fileVal") != "") {
			if(confirm("정말 이미지 파일을 삭제하시겠습니까?")) {
				$.ajax({
					type : "POST",
		            url : "${pageContext.request.contextPath}/product/fileDel.do", 
		            data : { 
		            	"product_idx" : $("#product_idx").val().replace(/,/g, "").trim() 
		            }, 
		            dataType : 'json', 
		            success : function(data) {
		            	if(data.success == "true") {
		            		$("#productImg, #fileDelBtn").remove();
		            		return;
		            	} else {
		            		alert("이미 삭제되었거나 삭제시 에러가 발생했습니다.");
		            		return;
		            	}
		            }, 
		            error : function(request, status, error) {
		            	alert("이미지 파일 삭제 처리시 에러가 발생했습니다.");
	            		return;
		            }
				});
			}
		} else {
			alert("첨부된 이미지가 없습니다.");
			return;
		}
	});
});
</script>
</head>
<body>
	<div class="container" style="padding-top:30px;">
		<form id="insertFrm" name="insertFrm" action="${pageContext.request.contextPath}/product/insert_proc.do" method="post" enctype="multipart/form-data">
		
		<div class="productViewArea01">
			<div class="lf">
				<input type="file" id="product_img" name="product_img" />
			</div>
			<div class="rf">
				<div class="first"><input type="text" id="product_name" name="product_name" /></div>
				<div class="second"><input type="text" id="product_eng_name" name="product_eng_name" /></div>
				<div class="third"><input type="text" id="product_day_bak" name="product_day_bak" />박 / <input type="text" id="product_price" name="product_price" />원</div>
			</div>
		</div>
		
		<div style="clear:both; height:50px;">&nbsp;</div>
		
		<div class="productViewArea02">
			<div class="lf" style="float:left; width:576px; padding:40px;">
				<div style="border-bottom:1px solid #FFB876; padding-bottom:20px; font-size:18pt;">상품설명</div>
				<div style="padding-top:20px; padding-bottom:20px;">
					<textarea name="product_content" id="product_content"></textarea>
				</div>
				<div style="padding-top:20px;">
					<input type="text" name="product_hash_tag" style="width:100%;" />
				</div>
			</div>
			<div class="rf" style="padding-top:20px; float:right; width:476px; text-align:right; padding-right:100px; padding-bottom:50px;">
				<img src="${pageContext.request.contextPath}/assets/img/product_description_02.png" />
			</div>
		</div>
		
		<div style="clear:both;"></div>
		<div style="padding-top:30px; text-align:center;">
			<input type="button" id="insertBtn" value="등록" />
		</div>
		</form>
	</div>
</body>
</html>
