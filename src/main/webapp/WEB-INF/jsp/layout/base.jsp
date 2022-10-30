<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<c:set var="uri"><%=request.getAttribute("javax.servlet.forward.request_uri")%></c:set>

<tiles:insertAttribute name="content"/>