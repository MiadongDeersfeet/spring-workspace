<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부산 맛집 탐방</title>
<style>
	.food:hover{
		pointer:cursor;
		box-shadow: 1px 1px 1px 1px rgba(0, 0, 0, 0.2);
		background-color : rgba(255, 255, 255, 0.9)
	}
</style>
</head>
<body>
	<jsp:include page="../include/header.jsp" />


	<div class="innerOuter">
	
		<div id="result">
		
		</div>
		<hr />
		
		<div style="width : 100px; heigt: 60px; margin:auto;">
			<button class="btn btn-sm" style="background:darkblue; width: 100%; height:100%; 
			border:none; border-radius:15px; color:white; font-weight:800;" onclick="getBusans();">더보기▽</button>
			
		</div>
		
	</div>
	
	<script>
		pageNo = 1;
	
		$(function() {
			getBusans();
		})
		
		function detail(num) {
			location.href=`busan/forward/\${num}`;
			
			
		}
		
		function getBusans() {
			$.ajax({
				url : `api/busan`,
				data : {
					pageNo : pageNo
				},
				success : response => {
					pageNo++
					console.log(response);
					
					const foods = response.getFoodKr.item;
					console.log(foods);
					
					const result = foods.map(e => `
								<div
									onclick="detail(\${e.UC_SEQ});"
									style="width: 48%; height:auto; display:inline-block; padding: 15px; text-align:center;" class="food">
									<img src="\${e.MAIN_IMG_THUMB}" width="100%" height="30%" /> <br/>
									<h5>\${e.MAIN_TITLE}</h5>
									<p>\${e.GUGUN_NM}</p>
								</div>
							`).join('');
					
					document.getElementById('result').innerHTML += result;
				}
			})	
			
		}
	</script>
	
	<jsp:include page="../include/footer.jsp" />
</body>
</html>