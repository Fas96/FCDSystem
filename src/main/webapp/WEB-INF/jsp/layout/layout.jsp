<%@ taglib prefix="tile" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="cache-Control" content="co-cache"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=yes"/>

    <title><spring:message code="html.title"/></title>


    <link rel="stylesheet" href="${contextPath }/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${contextPath }/css/layout.css"/>
    <link rel="stylesheet" href="${contextPath }/css/mquery.css"/>

    <script type="text/javascript" src="${contextPath }/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="${contextPath }/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${contextPath }/js/jquery-ui.js"></script>
    <script type="text/javascript" src="${contextPath }/js/jquery.scroll.pack.js"></script>
    <script type="text/javascript" src="${contextPath }/js/common.js"></script>
    <script type="text/javascript" src="${contextPath }/js/d_common.js"></script>

    <script type="text/javascript" src="${contextPath }/js/common/papernote/applicationForm.js"></script>
    <link href="${contextPath }/css/ui.fancytree.css" rel="stylesheet" type="text/css"/>
    <script src="${contextPath }/js/jquery.fancytree.js" type="text/javascript"></script>
    <!-- 서브 -->

    <!-- checkbox -->
    <link rel="stylesheet" href="${contextPath }/css/pretty-checkbox.css"></link>

    <!-- Slidebars CSS -->
    <link rel="stylesheet" href="${contextPath }/css/slidebars.css"/>

    <!-- Owl Carousel Assets -->
    <link rel="stylesheet" href="${contextPath }/css/owl.carousel.min.css"/>
    <link rel="stylesheet" href="${contextPath }/css/owl.theme.default.min.css"/>
    <script type="text/javascript" src="${contextPath }/js/owl.carousel.js"></script>

    <link rel="stylesheet" href="${contextPath }/css/custom.css"></link>

    <!-- jquery popup css -->
    <link rel="stylesheet" href="${contextPath }/css/jquery-ui.css"></link>


    <script type="text/javascript" src="${contextPath }/js/html5shiv.min.js"></script>
    <!--[if lt IE 9]>
    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->


</head>
<body style="height: 100%">

<div style="width:80%;">
    <div style="background-color: #F8C471">
        <tile:insertAttribute name="header"/>
    </div>

    <div style="float: left; width: 15%; height: 450px; background-color: #C39BD3;">
        <tile:insertAttribute name="menu"/>
    </div>

    <div style="float: left; width: 85%; height: 450px; background-color: #76D7C4;">
        <tile:insertAttribute name="main"/>
    </div>

    <div style="clear:both">
        <tile:insertAttribute name="footer"/>
    </div>
</div>


<script type="text/javascript" src="/js/webpack/dist/index_bundle.js"></script>
</body>
</html>
