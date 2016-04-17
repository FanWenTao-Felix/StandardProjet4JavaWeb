<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <form action="<%=basePath %>article/searchResult" method="post">   <br /> 
      用户id:<br /><input type="text" name="user.id"/><p/>
   标题:<br />   <input type="text" name="title" /> <p/>
 内容:<br />   <input type="text" name="content"/><p/>
      <br />
      <input type="submit" value="查询" />
    </form>
</body>
</html>