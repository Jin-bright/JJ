<%@page import="com.sh.obtg.member.model.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Member member = (Member)request.getAttribute("member");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href='https://fonts.googleapis.com/css?family=Audiowide|Iceland|Monoton|Pacifico|Press+Start+2P|Vampiro+One' rel='stylesheet' type='text/css'>
<title> MEMBER PROFILE </title>
<style>
@import url(https://fonts.googleapis.com/css?family=Roboto:300,400,600);
.snip1336 {
  font-family: 'Roboto', Arial, sans-serif;
  position: relative;
  overflow: hidden;
  margin: 10px;
  min-width: 230px;
  max-width: 610px;
  height :670px;
  width: 100%;
  color: #ffffff;
  text-align: left;
  line-height: 1.4em;
  background-color: #141414;
}
.snip1336 * {
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  -webkit-transition: all 0.25s ease;
  transition: all 0.25s ease;
}
.snip1336 img {
  <%--max-width: 100%;--%>
  width: 700px;
  height : 300px;
  vertical-align: top;
  opacity: 0.85;
}
.snip1336 figcaption {
  width: 100%;
  background-color: #141414;
  padding: 25px;
  position: relative;
}
.snip1336 figcaption:before {
  position: absolute;
  content: '';
  bottom: 100%;
  left: 0;
  width: 0;
  height: 0;
  border-style: solid;
  border-width: 55px 0 0 400px;
  border-color: transparent transparent transparent #141414;
}
.snip1336 figcaption a {
  padding: 5px;
  border: 2px solid #ffffff;
  color: #ffffff;
  font-size: 0.9em;
  text-transform: uppercase;
  margin: 40px 0;
  display: inline-block;
  opacity: 0.65;
  width: 47%;
  text-align: center;
  text-decoration: none;
  font-weight: 600;
  letter-spacing: 1px;
}
.snip1336 figcaption a:hover {
  opacity: 1;
}
.snip1336 .profile {
  border-radius: 50%;
  position: absolute;
  bottom: 100%;
  left: 25px;
  z-index: 1;
  max-width: 90px;
  opacity: 1;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.3);
}
.snip1336 .follow {
	margin-top : 10px;
  margin-right: 4%;
  border-color: #2980b9;
  color: #2980b9;
}
.snip1336 h2 {
  margin: 0 0 5px;
  font-weight: 300;
}
.snip1336 h2 span {
  display: block;
  font-size: 0.5em;
  color: #2980b9;
}
.snip1336 p {
  margin: 0 0 10px;
  font-size: 0.8em;
  letter-spacing: 1px;
  opacity: 0.8;
}

#commentDiv{
	border : 1px solid white; 
	height : 85px;
}

</style>
</head>
<body>



<figure class="snip1336" style="margin-left : 20px">
<!--   <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/331810/sample69.jpg"  />  -->
  <img id="profileimg" src="<%=request.getContextPath()%>/image/profileback3.png" alt="profileimg" />
  
  <figcaption>
    <img src="<%=request.getContextPath()%>/uploadootds/profile.png"  style= "width : 90px; height : 90px; z-index :12;"  class="profile" />
    <h2 style="font-size:33px; margin-top:-5px"><b><%=member.getNickname()%></b>
    		<span style="font-size:16px; padding-top:10px; padding-bottom:20px; padding-left:3px" ><%=member.getMemberId()%></span></h2>
    <p style="font-size:20px;"><b> COMMENT </b></p>
    <div id="commentDiv">
    	<p style="padding-bottom:0px; padding-top:20px"  ><b><%=member.getIntroduce()%></b></p>
    </div> 
    
    <p>■ E-MAIL : <%=member.getEmail()%></p>
    <p style="font-size:16px; font-weight:bolder; color:hotpink; padding-top:10px; margin-bottom:-10px">#<%=member.getStyle()%></p>
    <a href="#" class="info" style="margin-left: 120px" onclick="closepop()";> 닫기 </a>
  </figcaption>
</figure>


<script>
const closepop = () => {
	window.close();
}

$(".hover").mouseleave(
		  function () {
		    $(this).removeClass("hover");
		  }
);
</script>

</body>
</html>