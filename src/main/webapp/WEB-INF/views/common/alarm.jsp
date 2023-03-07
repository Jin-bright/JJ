<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="alarm_box">
   	<img src="${pageContext.request.contextPath}/image/bell.png">
   	<img src="${pageContext.request.contextPath}/image/record.png" class="new_alarm alarm_hidden">
</div>
<div class="alarm_container alarm_hidden">
	<div class="alarm_wrap">
		<div class="alarm_head">
			<h4>새로운 알림</h4>
			<span class="alarm_close_btn">X</span>
		</div>
		<div class="alarm_log_box">
			<div class="alarm_log"></div>
		</div>
	</div>
</div>
<script>
document.querySelector(".alarm_close_btn").addEventListener('click', (e) => {
	const container = document.querySelector(".alarm_container");
	const box = document.querySelector(".alarm_box");
	
	container.classList.add("alarm_hidden");
	box.classList.remove('alarm_opacity');
});
</script>