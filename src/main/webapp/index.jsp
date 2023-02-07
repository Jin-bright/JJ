<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" />
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>        
<%@ taglib uri ="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<%@ taglib uri ="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    


<link rel="stylesheet" href="${pageContext.request.contextPath}/css/about.css" />

<section class="swiper-container loading">
  <div class="swiper-wrapper">
    <div class="swiper-slide" >
     	<video autoplay muted controls playsinline height = "350" width="100%" style="background: black;">
        <source src="https://img.marieclairekorea.com/2021/09/mck_61359e7bc5073.mp4?_=1" >
    	</video>
      <img src="https://images.unsplash.com/photo-1483985988355-763728e1935b?crop=entropy&cs=srgb&fm=jpg&ixid=MnwxNDU4OXwwfDF8cmFuZG9tfHx8fHx8fHx8MTY0MDE1NTg2Ng&ixlib=rb-1.2.1&q=85" class="entity-img" />
      <div class="content">
      </div>
    </div>
    <div class="swiper-slide" style="background-image:url(https://cdn.dribbble.com/users/388811/screenshots/5698969/media/e20cf82a36e8c055bec99620aad3c450.jpg?compress=1&resize=800x600&vertical=top)">
      <img src="<%=request.getContextPath()%>/image/indexpic.jpg" class="entity-img" />
      <div class="content">
        <p class="title" data-swiper-parallax="-30%" data-swiper-parallax-scale=".7"> JOIN THE OBTG </p>
      </div>
    </div>
    <div class="swiper-slide" style="background-image:url(https://images.unsplash.com/photo-1465408953385-7c4627c29435?crop=entropy&cs=srgb&fm=jpg&ixid=MnwxNDU4OXwwfDF8cmFuZG9tfHx8fHx8fHx8MTY0MDE1NTg2Ng&ixlib=rb-1.2.1&q=85)">
      <img src="https://images.unsplash.com/photo-1465408953385-7c4627c29435?crop=entropy&cs=srgb&fm=jpg&ixid=MnwxNDU4OXwwfDF8cmFuZG9tfHx8fHx8fHx8MTY0MDE1NTg2Ng&ixlib=rb-1.2.1&q=85" class="entity-img" />
      <div class="content">
        <p class="title" data-swiper-parallax="-30%" data-swiper-parallax-scale=".7"> MAKE EACH DAY COLORFUL </p>
        <span class="caption" data-swiper-parallax="-20%"> OBTG는 패션을 다양한 사람들과 소통이 가능한 공간입니다. OBTG를 통해 매일매일 컬러풀한 하루를 보내보세요! </span>
      </div>
    </div>
    <div class="swiper-slide" style="background-image:url(https://images.unsplash.com/photo-1538329972958-465d6d2144ed?crop=entropy&cs=srgb&fm=jpg&ixid=MnwxNDU4OXwwfDF8cmFuZG9tfHx8fHx8fHx8MTY0MDE1NTkzNg&ixlib=rb-1.2.1&q=85)">
      <img src="https://images.unsplash.com/photo-1538329972958-465d6d2144ed?crop=entropy&cs=srgb&fm=jpg&ixid=MnwxNDU4OXwwfDF8cmFuZG9tfHx8fHx8fHx8MTY0MDE1NTkzNg&ixlib=rb-1.2.1&q=85" class="entity-img" />
      <div class="content">
        <p class="title" data-swiper-parallax="-30%" data-swiper-parallax-scale=".7"> MAKE EACH DAY COLORFUL </p>
        <span class="caption" data-swiper-parallax="-20%"> OBTG는 패션을 다양한 사람들과 소통이 가능한 공간입니다.</span>
      </div>
    </div>
    <div class="swiper-slide" style="background-image:url(https://images.unsplash.com/photo-1583292650898-7d22cd27ca6f?crop=entropy&cs=srgb&fm=jpg&ixid=MnwxNDU4OXwwfDF8cmFuZG9tfHx8fHx8fHx8MTY0MDE1NTk1OQ&ixlib=rb-1.2.1&q=85)">
      <img src="https://images.unsplash.com/photo-1583292650898-7d22cd27ca6f?crop=entropy&cs=srgb&fm=jpg&ixid=MnwxNDU4OXwwfDF8cmFuZG9tfHx8fHx8fHx8MTY0MDE1NTk1OQ&ixlib=rb-1.2.1&q=85" class="entity-img" />
      <div class="content">
        <p class="title" data-swiper-parallax="-30%" data-swiper-parallax-scale=".7">FIND YOUR OWN STYLE WITH US</p>
        <span class="caption" data-swiper-parallax="-20%">OBTG 사이트를 통해 여러분의 패션 스타일을 찾아보세요!  </span>
      </div>
    </div>
  </div>
  
  <!-- If we need pagination -->
  <div class="swiper-pagination"></div>
  <!-- If we need navigation buttons -->
  <div class="swiper-button-prev swiper-button-white"></div>
  <div class="swiper-button-next swiper-button-white"></div>
</section>
<style>
.theme_color2 :hover{
	background-color : black;
}

.theme_color :hover {
	background-color : black;
}

</style>
<section class="about-section">
    	<div class="container">
        	<div class="row clearfix">
            	
                <!--Content Column-->
                <div class="content-column col-md-6 col-sm-12 col-xs-12">
                	<div class="inner-column">
                    	<div class="sec-title">
                    		<div class="title" style="color:black; font-size:20px; font-weight:900; " >About <span style="color:RED"> OBTG</span> </div>
                        	<h2 style="color:black; font-size:26px; "> OBTG는 패션을 매개로 <br> 다양한 사람들과 소통할 수 있는 공간입니다.</h2>
                        </div>
                        <div class="text">패션을 통해 자신을 드러내는 세상. <br/>쇼핑몰 사이트는 넘쳐나는데 오늘 입은 옷을 공유하고 나의 소소한 생각을 공유하는 사이트는 다소 부족합니다. <br />
                        	저희 OBTG는 이러한 생각에 착안하여 누구나 쉽게 관심가지고 즐길 수 있는 패션을 다양한 사람들과 공유할 수 있는 사이트를 개발하였습니다. </div>
                        <div class="email"><span class="theme_color" style="font-size:20px; color:orange"><a style = "color:orange" href="<%=request.getContextPath()%>/ootd/ootdWholeList"><b>OOTD</b></a></span>로 이동</div>
<!--                         <a href="about.html" class="theme-btn btn-style-three">Read More</a> -->
                    </div>
                </div>
                
                <!--Image Column-->
                <div class="image-column col-md-6 col-sm-12 col-xs-12">
                	<div class="inner-column " data-wow-delay="0ms" data-wow-duration="1500ms">
                    	<div class="image">
                        	<img src="<%=request.getContextPath()%>/image/index1.png">
                            <div class="overlay-box"></div>
                        </div>
                    </div>
                    <br /><br /><br /><br /><br />
                </div>
                
                
                <!-- 두번째문단  --><br /><br /><br /><br />
              
                 <div class="sec-title" style="text-align: right ">
                    		<div class="title" style="color:black; font-weight:900; " >About <span style="color:orange">SHARE<span> </div>
                        	<h2 style="color:black; font-size:26px;   font-family: 'Varela Round', sans-serif;"> 더이상 입지 않는 옷들 <br> 어떻게 처리하고 계신가요 ?</h2>
                 </div>               
                 <div class="text" style="margin-left : 400px; color:gray; font-family: 'Varela Round', sans-serif;">옷을 생산하는 과정에는 많은 천연 자원이 사용되고 기후 변화에 영향을 미치는 상당한 양의 온실 가스 배출도 발생합니다.<br/>
                 유엔(UN)에 따르면 패션 산업은 전 세계 탄소 배출량의 8~10%를 차지한다고 하며 이는 항공과 해운 분야를 합친 것보다 더 많습니다.<br />
                 더 이상 입지않는 옷은 다른 사람에게 양도하여 환경 보존에 앞장서는 패션PEOPLE이 되어보는건 어떨까요 ?</br></br>
                 
                 SHARE 페이지를 통해 <span  style="color:red; font-weight:900";><b>#ActNow Fashion Challenge</b></span> 를 시작해보세요!
	                 <br /><br />
	                 <p>※ ActNow Fashion Challenge 란? <br />
	                 산업계와 개인이 어떻게 패션의 환경적 영향을 개선하는데 도움을 줄 수 있는지를 강조하기 위해 유엔이 만든 친환경 캠페인입니다.
	                 </p>
                 </div>
                 <div class="email"><span class="theme_color2" style="margin-left : 400px; width:200px">
                 	<a href="<%=request.getContextPath()%>/share/shareWholeList" style="color:orange"><b>&nbsp;&nbsp;SHARE&nbsp; </b></a></span> 로 이동
                 </div><br /><br />
       </div>
  </div>
 <style>
 @import url(https://fonts.googleapis.com/css?family=Varela+Round);

.slides {
    padding: 0;
    width: 609px;
    height: 420px;
    display: block;
    margin: 0 auto;
    position: relative;
}

.slides * {
    user-select: none;
    -ms-user-select: none;
    -moz-user-select: none;
    -khtml-user-select: none;
    -webkit-user-select: none;
    -webkit-touch-callout: none;
}

.slides input { display: none; }

.slide-container { display: block; }

.slide {
    top: 0;
    opacity: 0;
    width: 609px;
    height: 420px;
    display: block;
    position: absolute;

    transform: scale(0);

    transition: all .7s ease-in-out;
}

.slide img {
    width: 100%;
    height: 100%;
}

.nav label {
    width: 200px;
    height: 100%;
    display: none;
    position: absolute;
	margin: 0 -80px;
	opacity: 0;
    z-index: 9;
    cursor: pointer;

    transition: opacity .2s;

    color: #FFF;
    font-size: 90px;
    text-align: center;
    line-height: 380px;
    font-family: "Varela Round", sans-serif;
    background-color: rgba(255, 255, 255, .3);
    text-shadow: 0px 0px 15px rgb(119, 119, 119);
}

.slide:hover + .nav label { opacity: 0.5; }

.nav label:hover { opacity: 1; }

.nav .next { right: 0; }

input:checked + .slide-container  .slide {
    opacity: 1;

    transform: scale(1);

    transition: opacity 1s ease-in-out;
}

input:checked + .slide-container .nav label { display: block; }

.nav-dots {
	width: 100%;
	bottom: 9px;
	height: 11px;
	display: block;
	position: absolute;
	text-align: center;
}

.nav-dots .nav-dot {
	top: -5px;
	width: 11px;
	height: 11px;
	margin: 0 4px;
	position: relative;
	border-radius: 100%;
	display: inline-block;
	background-color: rgba(0, 0, 0, 0.6);
}

.nav-dots .nav-dot:hover {
	cursor: pointer;
	background-color: rgba(0, 0, 0, 0.8);
}

input#img-1:checked ~ .nav-dots label#img-dot-1,
input#img-2:checked ~ .nav-dots label#img-dot-2,
input#img-3:checked ~ .nav-dots label#img-dot-3,
 {
	background: rgba(0, 0, 0, 0.8);
}
 
 </style> 
  <ul class="slides" style="margin-left: 500px; margin-top : 50px; bolor: 2px solid black">
  
    <input type="radio" name="radio-btn" id="img-1" checked />
    <li class="slide-container">
		<div class="slide" style="border: 2px solid orange">
			<img src="<%=request.getContextPath()%>/image/indexchange1.png" />
        </div>
		<div class="nav">
			<label for="img-3" class="prev" >&#x2039;</label>
			<label for="img-2" class="next">&#x203a;</label>
		</div>
    </li>

    <input type="radio" name="radio-btn" id="img-2" />
    <li class="slide-container">
        <div class="slide">
          <img src="<%=request.getContextPath()%>/image/indexchange2.png" />
        </div>
		<div class="nav">
			<label for="img-1" class="prev">&#x2039;</label>
			<label for="img-3" class="next">&#x203a;</label>
		</div>
    </li>

    <input type="radio" name="radio-btn" id="img-3" />
    <li class="slide-container">
        <div class="slide">
          <img src="<%=request.getContextPath()%>/image/indexchange3.png" />
        </div>
		<div class="nav">
			<label for="img-2" class="prev">&#x2039;</label>
			<label for="img-1" class="next">&#x203a;</label>
		</div>
    </li>

    <li class="nav-dots">
      <label for="img-1" class="nav-dot" id="img-dot-1"></label>
      <label for="img-2" class="nav-dot" id="img-dot-2"></label>
      <label for="img-3" class="nav-dot" id="img-dot-3"></label>
    </li>
</ul><br /><br /><br /><br /><br /><br /><br /><br /><br />

<!--Image Column-->
<div class="image"  style="display:flex; margin-left:-200px;justify-content: center; padding-left :20px;   ">
   	<div class="image" style=" margin-left : 360px; margin-right : 50px; justify-content: center; ">
       	<img src="<%=request.getContextPath()%>/image/ootd_sample18.png" style="width : 400px; height:700px " >
           <div class="overlay-box"></div>
       </div>
       <div class="image" style="margin-right : 50px; justify-content: center; " >
       	<img src="<%=request.getContextPath()%>/image/ootd_sample21.png" style="width : 400px; height:700px" >
           <div class="overlay-box"></div>
       </div>
       <div class="image" style="margin-right : 40px;   width : 500px ; justify-content: center; " >
       	<img src="<%=request.getContextPath()%>/image/ootd_sample24.png" style="width : 400px; height:700px">
           <div class="overlay-box"></div>
       </div>
</div><br /><br /><br /><br /><br />
</section>



<script>

//Params
var sliderSelector = '.swiper-container',
    options = {
      init: false,
      loop: true,
      speed:800,
      slidesPerView: 2, // or 'auto'
      // spaceBetween: 10,
      centeredSlides : true,
      effect: 'coverflow', // 'cube', 'fade', 'coverflow',
      coverflowEffect: {
        rotate: 50, // Slide rotate in degrees
        stretch: 0, // Stretch space between slides (in px)
        depth: 100, // Depth offset in px (slides translate in Z axis)
        modifier: 1, // Effect multipler
        slideShadows : true, // Enables slides shadows
      },
      grabCursor: true,
      parallax: true,
      pagination: {
        el: '.swiper-pagination',
        clickable: true,
      },
      navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
      },
      breakpoints: {
        1023: {
          slidesPerView: 1,
          spaceBetween: 0
        }
      },
      // Events
      on: {
        imagesReady: function(){
          this.el.classList.remove('loading');
        }
      }
    };
var mySwiper = new Swiper(sliderSelector, options);

// Initialize slider
mySwiper.init();
</script>

			
<%@ include file="/WEB-INF/views/common/footer.jsp" %>