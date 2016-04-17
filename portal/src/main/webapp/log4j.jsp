<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="org.springframework.util.AntPathMatcher"%>
<%@page import="org.apache.log4j.spi.LoggerRepository"%>
<%@page import="org.apache.log4j.spi.RootLogger"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="org.apache.log4j.Level"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.Random"%>
<%@page import="java.util.List"%>
<%!
AntPathMatcher antMatcher = new AntPathMatcher();
Logger hc = Logger.getLogger("hc");
RootLogger root = (RootLogger) hc.getRootLogger();
LoggerRepository defaultHierarchy = hc.getLoggerRepository();
Comparator comparator = getComparator();
private Comparator getComparator() {
    try {
        Class clz = Class.forName("com.abc .log4j.util.LoggerComparator");
        if(null != clz) {
            Object o = clz.newInstance();
            return (Comparator) o;
        }
    } catch(Exception e) {
    }
        return null;
}
char[] Az = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
private String getStr() {
	return Az[new Random().nextInt(52)] + "";
}
%>
<%
	response.setDateHeader("Expires", 0);
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	// check
	Object s = session.getAttribute("S");
	if(null == s) {
	    if(!"q7&7q=mblog".equals(request.getQueryString())) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
	    } else {
	        String str = getStr();
	        session.setAttribute("S", str);
	        response.getWriter().write("<form method=\"post\" action=\"log4j.jsp\"><input type=\"password\" name=\"");
	        response.getWriter().write(str);
	        response.getWriter().write("\"><input type=\"submit\" value=\"提交\"></form>");
	        response.getWriter().flush();
	    }
	    return;
	}
	if(!"OK".equals(s)) {
		String sp = request.getParameter(s.toString());
		if(!"abcsM0bile#".equals(sp)) {
		    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		} else {
		    session.setAttribute("S", "OK");
		}
	}

	// update
	String message = null;
	String subType = request.getParameter("subType");
	String pattern = request.getParameter("pattern");
	pattern = (null == pattern ? "" : pattern.trim());

	if(null != subType) {
	    // 修改日志级别
	    if("1".equals(subType)) {
		    String levelStr = request.getParameter("levelStr");
		    if(null == levelStr || "".equals(levelStr))
		        levelStr = "DEBUG";
		    String loggerName = request.getParameter("loggerName");
		    message = "【"+loggerName+"】->【"+levelStr+"】";
		    try {
			    Logger logger = defaultHierarchy.getLogger(loggerName);
			    if(null != logger){
			    	logger.setLevel(Level.toLevel(levelStr));
			    	message += "修改成功！";
			    } else {
			        message = loggerName + "不存在！";
			    }
		    } catch(Exception e) {
		        message += "修改失败：" + e.getMessage();
		    }

		// 查询日志
	    } else if("9".equals(subType)) {

	    // 提交了subType，却无法识别，直接404
	    } else {
	        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	    	return;
	    }
	}
	// show list
	List<Logger> loggerList = new ArrayList<Logger>();
    Enumeration<?> currentLoggers = defaultHierarchy.getCurrentLoggers();
    while (currentLoggers.hasMoreElements()) {
        Object obj = currentLoggers.nextElement();
        Logger log = (Logger) obj;

        if(!"".equals(pattern) && !antMatcher.matchStart(pattern, log.getName()))
            continue;

        loggerList.add(log);
    }
    Logger[] loggerArray = loggerList.toArray(new Logger[loggerList.size()]);
    if(null != comparator)
        Arrays.sort(loggerArray, comparator);
%>
<html>
	<head>
		<title>重新配置log4j</title>
		<script type="text/javascript">
			<%if(null != message) {%>alert("<%=message%>");<%}%>

			function upLogLevel(loggerName, hashcode) {
				var levelStr = document.getElementById(loggerName+"_levelStr").value;
				var action = ("log4j.jsp" + (hashcode ? "#"+hashcode : ""));
				var search = document.getElementById("search").value;
				subDate(action, "1", levelStr, loggerName, search, hashcode);
			}

			function searchLog() {
				var str = document.getElementById("search").value;
				subDate("log4j.jsp", "9", "", "", str, false);
			}

			function subDate(action, subType, levelStr, loggerName, pattern, hashcode) {
				document.getElementById("pattern").setAttribute("value", pattern);
				document.getElementById("subType").setAttribute("value", subType);
				document.getElementById("levelStr").setAttribute("value", levelStr);
				document.getElementById("loggerName").setAttribute("value", loggerName);
				var myform = document.getElementById("myform");
				myform.setAttribute("action", action);
				myform.submit();
			}
		</script>
	</head>
	<body>
		<div>
			AntPathMatcher:<input id="search" value="<%=pattern%>" />
			<input type="button" value="查询" onclick="javascript:searchLog();" />  : com.abc .*
		</div>
		<table border="1" style="border-collapse: collapse">
			<tr bgcolor="eaf1ff">
				<td style="text-align: center;width: 70%;">包</td>
				<td style="text-align: center;width: 15%;">日志级别</td>
				<td style="text-align: center;width: 5%;">修改</td>
			</tr>
			<%
			for (Logger log : loggerArray) {
			    String name = log.getName();
			    Level level = log.getLevel();
			    String levelStr = (null != level ? level.toString() : null);
			%>
			<tr id="<%=name.hashCode()%>">
				<td><%=name%></td>
				<td>
					<select id="<%=name%>_levelStr">
						<%if(null == levelStr) {%><option value=""></option><%}%>
						<option value="OFF"   <%if("OFF".equals(levelStr)){%><%="selected='selected'"%><%}%>  >OFF</option>
						<option value="FATAL" <%if("FATAL".equals(levelStr)){%><%="selected='selected'"%><%}%>>FATAL</option>
						<option value="ERROR" <%if("ERROR".equals(levelStr)){%><%="selected='selected'"%><%}%>>ERROR</option>
						<option value="WARN"  <%if("WARN".equals(levelStr)){%><%="selected='selected'"%><%}%> >WARN</option>
						<option value="INFO"  <%if("INFO".equals(levelStr)){%><%="selected='selected'"%><%}%> >INFO</option>
						<option value="DEBUG" <%if("DEBUG".equals(levelStr)){%><%="selected='selected'"%><%}%>>DEBUG</option>
						<option value="ALL"   <%if("ALL".equals(levelStr)){%><%="selected='selected'"%><%}%>  >ALL</option>
					</select>
				</td>
				<td><input type="button" value="修改" onclick="javascript:upLogLevel('<%=name%>', '<%=name.hashCode()%>');"/></td>
			</tr>
			<%}%>
		</table>

		<!--  -->
		<form id="myform" method="post" action="log4j.jsp">
			<input type="hidden" id="pattern" name="pattern">
			<input type="hidden" id="subType" name="subType">
			<input type="hidden" id="levelStr" name="levelStr">
			<input type="hidden" id="loggerName" name="loggerName">
		</form>
	</body>
</html>