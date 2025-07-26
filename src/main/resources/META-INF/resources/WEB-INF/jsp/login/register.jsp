<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>회원가입 | 메타관리시스템</title>
	<jsp:include page="/WEB-INF/jsp/include/metaHeader.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/include/cssHeader.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/include/scriptHeader.jsp"></jsp:include>
</head>
<body>
	
  <main>
    <div class="container">

      <section class="section register min-vh-100 d-flex flex-column align-items-center justify-content-center py-4">
        <div class="container">
          <div class="row justify-content-center">
            <div class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center">

              <div class="d-flex justify-content-center py-4">
                <a href="${pageContext.request.contextPath}/" class="logo d-flex align-items-center w-auto">
                  <img src="${pageContext.request.contextPath}/resources/NiceAdmin/assets/img/logo.png" alt="">
                  <span class="d-none d-lg-block">메타관리시스템</span>
                </a>
              </div><!-- End Logo -->

              <div class="card mb-3">

                <div class="card-body">

                  <div class="pt-4 pb-2">
                    <h5 class="card-title text-center pb-0 fs-4">회원가입</h5>
                    <p class="text-center small">계정정보를 입력해주세요</p>
                  </div>

                  <form class="row g-3 needs-validation" novalidate>

                    <div class="col-12">
                      <label for="username" class="form-label">아이디</label>
                      <div class="input-group has-validation">
                        <input type="text" name="username" class="form-control" id="username" data-required>
                        <div class="invalid-feedback">아이디를 입력해주세요!</div>
                      </div>
                    </div>

                    <div class="col-12">
                      <label for="password" class="form-label">비밀번호</label>
                      <input type="password" name="password" class="form-control" id="password" data-required>
                      <div class="invalid-feedback">비밀번호를 입력해주세요!</div>
                    </div>

                    <div class="col-12">
                      <button class="btn btn-primary w-100" type="button" id="btnRegister">회원가입</button>
                    </div>
                    <div class="col-12">
                      <p class="small mb-0">이미 계정이 존재하신가요? <a href="${pageContext.request.contextPath}/METLG01">로그인</a></p>
                    </div>
                  </form>

                </div>
              </div>


            </div>
          </div>
        </div>

      </section>

    </div>
  </main><!-- End #main -->

  <a href="#" class="b|ack-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>
  
  <script>
  	$(document).ready(function() {
  		$("#btnRegister").click(function() {
  			// 필수값 입력체크
  			if (!alertUtils.checkRequiredFields()) {
  				return;
  			}
  			
  			var requestMap = {
  				memId: $('#username').val()
  				, memPw: $('#password').val()
  			}
  			
  			ajax('METLG03', requestMap, function(response) {
  				if (response.header && response.header.status == '0000') {
  					alertUtils.showAlert('회원등록이 완료되었습니다.', function() {
  						gotoURL('METLG01');
  					});
  				} else {
  					alertUtils.showAlert(response.header.errorMsg);
  				}
  			});
  		});
  	});
  
  </script>
	
  <jsp:include page="/WEB-INF/jsp/include/scriptBody.jsp"></jsp:include>
</body>
</html>