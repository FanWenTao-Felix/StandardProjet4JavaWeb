<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>

<!--  
<script language="JavaScript">
function Request(argname)
{
var url = document.location.href;
var arrStr = url.substring(url.indexOf("?")+1).split("&");
//return arrStr;
for(var i =0;i<arrStr.length;i++)
{
   var loc = arrStr[i].indexOf(argname+"=");
   if(loc!=-1)
   {
    return arrStr[i].replace(argname+"=","").replace("?","");
    break;
   }
  
}
return "";
}

window.onload = function(){ 
	var currentPage=Request("page");
	}; 


</script>
-->
</head>
<body>
	<c:forEach items="${articles}" var="item">  
        ${item.id }--${item.title }--${item.content }<br />
	</c:forEach>
	<div style="padding:20px;">${pageStr}</div>
</body>
</html>
