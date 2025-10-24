<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!--  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지도를 띄워보자</title>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b6f8257dff2e6330d38f2bb53f04eb9b"></script>
</head>
<body>

	

	<jsp:include page="../include/header.jsp" />
	
	<div id="map" style="width:500px;height:400px;margin:auto;" ></div>
	
	<script>
	var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	var options = { //지도를 생성할 때 필요한 기본 옵션
		center: new kakao.maps.LatLng(37.5679275647765, 126.983120773451), //지도의 중심좌표.
		level: 3 //지도의 레벨(확대, 축소 정도)
	};
	
	var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
	
	//
	var roadviewContainer = document.getElementById('roadview'); //로드뷰를 표시할 div
	var roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
	var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체

	var position = new kakao.maps.LatLng(37.5679275647765, 126.983120773451);

	// 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
	roadviewClient.getNearestPanoId(position, 50, function(panoId) {
	    roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
	});
	</script>
	
	
	
	<jsp:include page="../include/footer.jsp" />
</body>
</html>
-->
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>로드뷰 생성하기</title>
    
</head>
<body>
<jsp:include page="../include/header.jsp" />
<!-- 로드뷰를 표시할 div 입니다 -->
<div id="roadview" style="width:100%;height:300px;"></div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b6f8257dff2e6330d38f2bb53f04eb9b"></script>
<script>
var roadviewContainer = document.getElementById('roadview'); //로드뷰를 표시할 div
var roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체

var position = new kakao.maps.LatLng(37.5679275647765, 126.983120773451);

// 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
roadviewClient.getNearestPanoId(position, 50, function(panoId) {
    roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
});
</script>

<jsp:include page="../include/footer.jsp" />
</body>
</html>