<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
	<head>
		<link rel="stylesheet" type="text/css" href="${contextPath}/css/homepage.css" />
		<script defer src="${contextPath}/js/webpack/dist/index_bundle.js"></script>
	</head>
<body id="mapPage">
<div  id="mapDiv">
</div>

<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAF3dVDB9988nqO0Qo5RCqbowh03x0GBs4" defer></script>
<script type="text/javascript">
	window.addEventListener('load', function() {
		let map= document.querySelector("#mapDiv")

		if(map){
			map.removeAttribute("style");
		}
	});
</script>

</body>

</html>
