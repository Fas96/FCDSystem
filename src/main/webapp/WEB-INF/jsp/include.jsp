<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>
<c:set var="lang">${empty param.lang ? cookie.clientlanguage.value : param.lang }</c:set>
<%
	pageContext.setAttribute("cr", "\r");		// Space
	pageContext.setAttribute("cn", "\n");		// Enter
	pageContext.setAttribute("crcn", "\r\n");	// SpaceEnter
	pageContext.setAttribute("br", "<br>");		// br 태그
%>