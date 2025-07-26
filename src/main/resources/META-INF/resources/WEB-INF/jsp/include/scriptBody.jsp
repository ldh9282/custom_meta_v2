<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

  <jsp:include page="/WEB-INF/jsp/include/alertModal.jsp"></jsp:include>
  
  <script>
  	function gotoURL(url) {
  		window.location.href = '${pageContext.request.contextPath}/' + url;
  	}
  	
  	function formSubmit(form, url) {
  		$(form).attr('action', '${pageContext.request.contextPath}/' + url)
  				.submit();
  	}
  	
  	function logout() {
  		alertUtils.showConfirm('로그아웃 하시겠습니까?', function() {
  			gotoURL('logout')
  		});
  	}
  	const ajax = function(url, data, successCb, errorCb) {
  		$.ajax({
  			type: 'POST',
  			url: '${pageContext.request.contextPath}/' + url,
  			data: JSON.stringify(data),
  			contentType: 'application/json',
  			async: false,
  			success: function(result) {
  				if (successCb) {
  					successCb(result);
  				}
  			},
  			error: function(a, b, c) {
  				if (errorCb) {
  					errorCb(a, b, c)
  				} else {
  					alertUtils.showAlert('시스템 접속 에러가 발생했습니다.');
  				}
  			}
  		})
  	}
  </script>
  
  <!-- Vendor JS Files -->
  <script src="${pageContext.request.contextPath}/resources/NiceAdmin/assets/vendor/apexcharts/apexcharts.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/NiceAdmin/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/NiceAdmin/assets/vendor/chart.js/chart.umd.js"></script>
  <script src="${pageContext.request.contextPath}/resources/NiceAdmin/assets/vendor/echarts/echarts.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/NiceAdmin/assets/vendor/quill/quill.js"></script>
  <script src="${pageContext.request.contextPath}/resources/NiceAdmin/assets/vendor/simple-datatables/simple-datatables.js"></script>
  <script src="${pageContext.request.contextPath}/resources/NiceAdmin/assets/vendor/tinymce/tinymce.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/NiceAdmin/assets/vendor/php-email-form/validate.js"></script>

  <!-- Template Main JS File -->
  <script src="${pageContext.request.contextPath}/resources/NiceAdmin/assets/js/main.js"></script>
  
  <!-- TimerElement.js -->
  <script type="module" src="${pageContext.request.contextPath}/resources/cmmn/js/element/TimerElement.js?2025021023"></script>