<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

 
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/about.css" />


<section class="about-section">
    	<div class="container">
        	<div class="row clearfix">
            	
                <!--Content Column-->
                <div class="content-column col-md-6 col-sm-12 col-xs-12">
                	<div class="inner-column">
                    	<div class="sec-title">
                    		<div class="title">About Us</div>
                        	<h2>더 나은 패션 문화를 위해<br> OBTG가 앞장서겠습니다. </h2>
                        </div>
                        <div class="text"> 자신의 개성을 존중 받는 시대가 도래하면서 많은 사람들이 패션에 관심을 갖게 되었습니다. 저희 OBTG가</div>
                        <div class="email"><span class="theme_color">OBTG</span></div>
<!--                         <a href="about.html" class="theme-btn btn-style-three">Read More</a> -->
                    </div>
                </div>
                
                <!--Image Column-->
                <div class="image-column col-md-6 col-sm-12 col-xs-12">
                	<div class="inner-column " data-wow-delay="0ms" data-wow-duration="1500ms">
                    	<div class="image">
                        	<img src="https://i.ibb.co/vQbkKj7/about.jpg" alt="">
                            <div class="overlay-box">

                            </div>
                        </div>
                    </div>
                </div>
                
            </div>
        </div>
    </section>
      
      
<%@ include file="/WEB-INF/views/common/footer.jsp" %>