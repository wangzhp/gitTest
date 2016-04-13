<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="w"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
         
         
         <%
         Map<String,String> map=new HashMap<String,String>();
         List<String> list=new ArrayList<String>();
           list.add("1111");
           list.add("2222");
           list.add("3333");
           map.put("wang1","吱吱吱1");
           map.put("wang2","吱吱吱2");
           map.put("wang3","222");
           request.setAttribute("list", list);
           request.setAttribute("map", map);
           request.setAttribute("hello", "你好,jstl");
           request.setAttribute("v1", 12);
           request.setAttribute("v2", 13);
           
         %>
           <w:forEach items="${list}" var="m">
         ${m}<br/>
       </w:forEach>
         
       <w:forEach items="${map}" var="m">
         ${m.key}<br/>
       </w:forEach>
       
       <w:forTokens items="${map}" delims="*">
           ${m.key}<br/>
       </w:forTokens>
       
        <w:out value="${hello}"></w:out>
       <w:if test="${v1 lt v2}">
             <h2>这是if的逻辑</h2>
       </w:if>
       <w:choose >
         <w:when test="${v1 gt v2}">
          <w:out value="大于"></w:out>
       </w:when>
       <w:otherwise>
         <w:out value="小于"></w:out>
       </w:otherwise>
       </w:choose>
     
    

       
       
</body>
</html>