<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	<title>스키마 조회 | 메타관리시스템</title>
	
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
			<div class="row">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<h5 class="card-title">스키마 조회</h5>
							<form id="defaultForm">
								<p>
								<input type="hidden" id="thePageNum" name="pageNum" value="${pagingCreator.pageNum}">
								<select id="theRowAmountPerPage" name="rowAmountPerPage">
									<option value="10" ${pagingCreator.rowAmountPerPage == '10' ? 'selected' : ''}>10개</option>
									<option value="20" ${pagingCreator.rowAmountPerPage == '20' ? 'selected' : ''}>20개</option>
									<option value="50" ${pagingCreator.rowAmountPerPage == '50' ? 'selected' : ''}>50개</option>
									<option value="100" ${pagingCreator.rowAmountPerPage == '100' ? 'selected' : ''}>100개</option>
								</select>
								</p>
								<input type="hidden" id="theSchemaMetaSno" name="schemaMetaSno" value="${params.schemaMetaSno}">
								<input type="hidden" id="theSchemaName" name="schemaName" value="${params.schemaName}">
								<input type="hidden" id="theSchemaDesc" name="schemaDesc" value="${params.schemaDesc}">
							</form>
							<form id="searchForm">
							<input type="hidden" id="pageNum" name="pageNum" value="1">
							<input type="hidden" id="rowAmountPerPage" name="rowAmountPerPage" value="${pagingCreator.rowAmountPerPage}">
							<div class="row">
								<div class="col-md-4">
									<div class="input-group mb-3">
									    <span class="input-group-text">스키마메타일련번호</span>
									    <input type="text" class="form-control" id="schemaMetaSno" name="schemaMetaSno" value="${params.schemaMetaSno}">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-4">
									<div class="input-group mb-3">
									    <span class="input-group-text">스키마명</span>
									    <input type="text" class="form-control" id="schemaName" name="schemaName" value="${params.schemaName}">
									</div>
								</div>
								<div class="col-md-4">
									<div class="input-group mb-3">
									    <span class="input-group-text">스키마설명</span>
									    <input type="text" class="form-control" id="schemaDesc" name="schemaDesc" value="${params.schemaDesc}">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="text-end">
									    <button type="button" class="btn btn-sm btn-primary" id="btnKeywordSearch">검색</button>
									</div>
								</div>
							</div>
							</form> <!-- // END searchForm  -->
							<div>
								(총 ${pagingCreator.count}개, page ${pagingCreator.pageNum} of ${pagingCreator.lastPageNum})
							</div>
							<table class="table mt-3">
								<colgroup>
									<col style="width: 15%;"/>
									<col style="width: 35%;"/>
									<col style="width: 35%;"/>
									<col style="width: auto;"/>
								</colgroup>
								<thead>
									<tr>
										<th scope="col" class="text-center">스키마메타일련번호</th>
										<th scope="col" class="text-center">스키마명</th>
										<th scope="col" class="text-center">스키마설명</th>
										<th scope="col" class="text-center">스키마삭제</th>
									</tr>
								</thead>
		
								<tbody id="tbody">
									<c:forEach var="schemaMetaInfo" varStatus="domainInfoStatus" items="${schemaMetaInfoList}">
										<tr>
											<td class="text-center" >
												<c:out value="${schemaMetaInfo.schemaMetaSno}" />
											</td>
											<td class="text-center">
												<c:out value="${schemaMetaInfo.schemaName}" />
											</td>
											<td class="text-center">
												<c:out value="${schemaMetaInfo.schemaDesc}" />
											</td>
											<td class="text-center">
												<button type="button" class="btn btn-sm btn-danger" onclick="deleteSchema('${schemaMetaInfo.schemaMetaSno}')">삭제</button>
											</td>
										</tr>
	
									</c:forEach>	
								</tbody>	
							</table>
						</div>
					</div>
		
		
		
				</div>
			</div>
			<!-- ======= pagingCreator ======= -->
			<jsp:include page="/WEB-INF/jsp/include/pagingCreator.jsp"></jsp:include>
			<!-- End pagingCreator-->
			</div>
		</section>

    </main><!-- End #main -->

   
	
	<script>
	

		$(document).ready(function() {
			
			// 행수 변경 이벤트
			$("#theRowAmountPerPage").change(function() {
				
				$("#thePageNum").val(1)
				$('#defaultForm').attr('method', 'POST')
				formSubmit($('#defaultForm'), 'METSC01');
			})

			
			// 검색 이벤트
			$("#btnKeywordSearch").click(function() {
				$("#pageNum").val(1)
				$('#searchForm').attr('method', 'POST')
				formSubmit($('#searchForm'), 'METSC01');
			})
			
			

		});
		
		// 페이징 처리
		function goToPaging(pageNum) {
			$("#thePageNum").val(pageNum)
			$('#defaultForm').attr('method', 'POST')
			formSubmit($('#defaultForm'), 'METSC01');
		}
		
		// 스키마 삭제처리
		function deleteSchema(theSchemaMetaSno) {
			alertUtils.showConfirm('삭제하시겠습니까?', function() {
				var requestMap = {
					schemaMetaSno: theSchemaMetaSno
		    	};
		    	
		    	ajax('METSC04', requestMap, function(response) {
		    		if (response.header && response.header.status == '0000') {
		    			alertUtils.showAlert('삭제되었습니다', function() {
		    				gotoURL('METSC01');
		    			});
		    		} else {
		    			alertUtils.showAlert(response.header.errorMsg);
		    		}
		    	});
				
			})
		}
	</script>


	<jsp:include page="/WEB-INF/jsp/include/scriptBody.jsp"></jsp:include>

	 <!-- ======= Footer ======= -->
    <jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
	<!-- End Footer -->
	
</body>

</html>