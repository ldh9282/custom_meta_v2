<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	<title>스키마 생성 | 메타관리시스템</title>
	
	<jsp:include page="/WEB-INF/jsp/include/metaHeader.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/include/cssHeader.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/include/scriptHeader.jsp"></jsp:include>

</head>

<body>

	<!-- ======= Header ======= -->
	<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
	<!-- End Header -->
	
	<!-- ======= Sidebar ======= -->
	<jsp:include page="/WEB-INF/jsp/include/sidebar.jsp"></jsp:include>
	<!-- End Sidebar-->
	
	<main id="main" class="main">
        <section class="section">
            <div class="container mt-5">
            	<div class="card">
				    <div class="card-body">
				        <h5 class="card-title">스키마 생성</h5>
				        <div class="row g-3">
				            <div class="col-md-4">
				                <div class="form-floating">
				                    <input type="text" class="form-control" id="schemaName" name="schemaName" oninput="this.value = this.value.toUpperCase()" data-required>
				                    <label for="schemaName">스키마명</label>
				                </div>
				            </div>
				            <div class="col-md-4">
				                <div class="form-floating">
				                    <input type="text" class="form-control" id="schemaDesc" name="schemaDesc" data-required>
				                    <label for="schemaDesc">스키마설명</label>
				                </div>
				            </div>
				            
				            
				        </div>
				        
			            <div class="row g-3 mt-3">
				            <div class="text-end">
						        <button type="button" class="btn btn-sm btn-primary" id="btnRegister">등록</button>
						        <button type="button" class="btn btn-sm btn-secondary mx-3" id="btnList" onclick="gotoURL('METSC01')">목록</button>
				            </div>
			            </div>
				        
				    </div>
				</div>
			</div>
			
        </section>

    </main><!-- End #main -->
	
	
	
	<script>
	
		$(document).ready(function () {
		    // 등록버튼
		    $('#btnRegister').click(function() {
		    	// 필수값 입력체크
	  			if (!alertUtils.checkRequiredFields()) {
	  				return;
	  			}
		    	
		    	var requestMap = {
	    			schemaName: $('#schemaName').val()
	    			, schemaDesc: $('#schemaDesc').val()
		    	};
		    	
		    	ajax('METSC03', requestMap, function(response) {
		    		if (response.header && response.header.status == '0000') {
		    			alertUtils.showAlert('등록되었습니다', function() {
		    				gotoURL('METSC01');
		    			});
		    		} else {
		    			alertUtils.showAlert(response.header.errorMsg);
		    		}
		    	});
		    	
		    });
		});
	
	
	
	</script>

	<jsp:include page="/WEB-INF/jsp/include/scriptBody.jsp"></jsp:include>
	
	<!-- ======= Footer ======= -->
	<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
	<!-- End Footer -->
</body>

</html>