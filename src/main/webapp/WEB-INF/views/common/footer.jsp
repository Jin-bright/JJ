<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		</section>

		<footer>
            <strong id="footer_iogo">OBTG</strong>
            <hr>
			 <div id="footer-container">
            <div id="footer-wrap">
                <div id="footer_info">
                    <a class="footer_a" href="<%=request.getContextPath()%>/common/personalInformation">개인정보취급방안</a>
                    |
                    <a class="footer_a" href="<%=request.getContextPath()%>/common/usepolicy"  >이용약관</a>
                    |
                    <a class="footer_a" href="<%= request.getContextPath() %>/faq/faqAccordion">FAQ</a>
                    |
                    <a class="footer_a" href="<%= request.getContextPath() %>/faq/faqList">INFO</a>
                </div>
                <div id="footer_sns">
                    <img src="<%=request.getContextPath()%>/image/youtube.png" alt="" class="youtube">
                    <img src="<%=request.getContextPath()%>/image/instagram.png" alt="" class="instagram">
                    <a href="https://github.com/incheol789/OBTG-semi-project"  style="display:inline-block; width=10px; height:10px" ><img src="<%=request.getContextPath()%>/image/github.png" alt="" style="width:35px; height:35px; margin-left : 16px" class="github"></a>
                    <img src="<%=request.getContextPath()%>/image/facebook.png" class="facebook">
                </div>
                <div id="footer_txt">
                    <span id="footer_span">
                        서울특별시 강남구 테헤란로10길 9 그랑프리 빌딩 5F<br>
                        사업자등록번호: 851-87-006222<br>
                        전화: 1544-9970 이메일 : obtg2023@gmail.com<br>
                        Copyright 2023 <strong></strong>. All rights reserved.
                    </span>
                </div>  
            </div>
      </div>
	</footer>
		
</body>
</html>
