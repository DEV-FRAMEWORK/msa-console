<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.msa.console.enums.CommonEnums" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="useCd" value="<%=CommonEnums.USE_CD.getCode()%>"/>
<c:set var="useValue" value="<%=CommonEnums.USE_CD.getValue()%>"/>
<c:set var="NUseCd" value="<%=CommonEnums.NUSE_CD.getCode()%>"/>
<c:set var="NUseValue" value="<%=CommonEnums.NUSE_CD.getValue()%>"/>

<script type="text/javascript">

$(document).ready(function(){
	
 	// 달력 초기화
    $('input[name=serviceStartDt]').each(function(){
    	$(this).datepicker({
    		 "format" :'yyyy-mm-dd',
             "autoclose": true,
             "todayHighlight":true
    	});
    });
 	
 	// 달력 초기화
    $('input[name=serviceEndDt]').each(function(){
    	$(this).datepicker({
    		 "format" :'yyyy-mm-dd',
             "autoclose": true,
             "todayHighlight":true
    	});
    });
 	
	//colorpicker 초기화
	$('div[name=colorGroup]').each(function(){
		$(this).colorpicker({
	        format: 'hex'
		});
	});
	
	/* 서비스 선택 checkbox 체크 */
	$("input[name=serviceChkBox]").each(function(i,e){
		<c:forEach items="${selServiceApply}" var="list">
			if($(this).val() == "${list.serviceCd}"){
				$(this).attr('checked', true);
			}
		</c:forEach>
	});
	
	
	$("select[name=systemCd]").each(function(i,e){
		var serviceCd = $("select[name=serviceCd]")[i].value;
		var selectSystemCd = $(this).val();
		var selObj = $(this);

		if(selectSystemCd != 'default'){
			selObj.find('option').remove();
			
			$.ajax({
				type : 'POST',
				url : '/selServicebySytem',
				//파리미터 변수 이름 : 값
				data : {
					serviceId : serviceCd
				},
				cache:true,
				success : function(data){
					if(data.length > 0){
						for(var i=0; i <data.length; i++){
							if(data[i].cdId == selectSystemCd){
								selObj.append("<option value='"+data[i].cdId+"' selected>"+data[i].cdNm+"</option>");
							}
							else{
								selObj.append("<option value='"+data[i].cdId+"'>"+data[i].cdNm+"</option>");
							}
						}	
					} 
				},
				error:function(){
					alert('서비스에 문제가 발생되었습니다. 관리자에게 문의 하시기 바랍니다.');
				}
			});
		}
	});
	
	
	//취소(목록으로)
	$("#btnCancel").click(function(){
		location.href="/selListServiceApply";
	});
	
	//검색어 입력
	$("#searchValue").keyup(function(){
		$.getJSON('searchCompetitionService', 
				{"searchType" : $('#searchType').val(), "searchValue" : $('#searchValue').val()}, function(data){
			var html;
			$("#competition option").remove();
			
			var obj = $("#searchValue").offset();
			
			$(data).each(function(entryIndex, entry){
				$('#competition').append('<option value="'+entry.tenantId+'.'+entry.cpCd+'">'+entry.cpNm+' [ tenant id : '+entry.tenantId+' 대회코드 : '+entry.cpCd+' ]</option>');
			});
			
		});
	});
	
    // 서비스 추가 버튼 클릭시
    $("#addRowBtn").click(function(){
    	if($('tbody[name=serviceTbody] > tr').length == 0){
    		alert("사용하실 서비스를 선택해주세요");
    		return;
    	}
    	addServiceTbl('btn');
    	setDatepicker();
    });
    
			
 	// 서비스 삭제버튼 클릭시
    $("#delRowBtn").click(function(){  
    	delServiceTbl('', 'btn');
    });
 	
 	
 	//수정 등록
	$("#btnRegister").on("click", function(e){

		console.log($("#frm").serializeArray());
		
		if($("input[name=serviceUrlAddr]").length == 1 && $("input[name=serviceUrlAddr]").val()==''){
			$("input[name=serviceUrlAddr]").val(' ');
		}
		if($("input[name=testLabRemarkDesc]").length == 1 && $("input[name=testLabRemarkDesc]").val()==''){
			$("input[name=testLabRemarkDesc]").val(' ');
		}
		if($("input[name=testEventRemarkDesc]").length == 1 && $("input[name=testEventRemarkDesc]").val()==''){
			$("input[name=testEventRemarkDesc]").val(' ');
		}
		
		//disabled 설정 해제
		$("select[name=serviceCd]").removeAttr("disabled");
		$("select[name=systemCd]").removeAttr("disabled");
		
		$("input[name=testLabCheck]:checked").each(function(){
			$(this).next().val('Y');
		});
		$("input[name=testLabCheck]").not(":checked").each(function(){
			$(this).next().val('N');
		});
		$("input[name=testEventCheck]:checked").each(function(){
			$(this).next().val('Y');
		});
		$("input[name=testEventCheck]").not(":checked").each(function(){
			$(this).next().val('N');
		});
		
 		$.ajax({
			type : "POST",
			url  : "/upServiceApply", 
			dataType : "json",
			data : $("#frm").serializeArray(),
			success : function(data, status) {
				try{
					if( data.result == '1'){
						alert("등록 성공!");
						redirectList();
					} else {
						alert("RETURN CODE : "+ data.result+' , '+"등록 실패!");
					}
				}catch(e) {	
					alert('서비스에 문제가 발생되었습니다. 관리자에게 문의 하시기 바랍니다.');
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				if(XMLHttpRequest.status == '901'){
					sessionTimeOut();			
				} else {
					//console.log(XMLHttpRequest.code + ":" + textStatus + ":" + errorThrown);
					alert('서비스에 문제가 발생되었습니다. 관리자에게 문의 하시기 바랍니다.');
				}
				return;
			}
		});  
	});
 	

});
</script>

<!-- dynamic object event binding -->
<script>
	//상위서비스 변경 이벤트
 	$(document).on("change","select[name='serviceCd']",function(){
 		var html="";
		var serviceId = this.value;
	 	var serviceCd = $(this);
	 	var systemCd = serviceCd.parent().next().children();

	 	//하위서비스 clear
	 	systemCd.find('option').remove();

		$.ajax({
			type : 'POST',
			url : '/selServicebySytem',
			//파리미터 변수 이름 : 값
			data : {
				serviceId : serviceId
			},
			cache:true,
			success : function(data){
				
				if(data.length>0){
					for(var i=0; i <data.length; i++){
						systemCd.append("<option value='"+data[i].cdId+"'>"+data[i].cdNm+"</option>");
					}	
				} 
			},
			error:function(){
				alert('서비스에 문제가 발생되었습니다. 관리자에게 문의 하시기 바랍니다.');
			}
		});
		
	});
 	
	//체크박스 서비스 선택
	$(document).on("change","input[name='serviceChkBox']",function(){
		//상위서비스 사용 체크시
		if($(this).is(":checked")){
			var servicdCd="";
			var systemCd="";

			addServiceTbl('chk');
			setDatepicker();
			
			servicdCd = $('tbody[name=serviceTbody]:last > tr:last > td:eq(0) >select');
			systemCd = $('tbody[name=serviceTbody]:last > tr:last > td:eq(1) >select');
			
			servicdCd.val($(this).val());
			servicdCd.attr('disabled','disabled');
			
		 	systemCd.find('option').remove();
		 	systemCd.append("<option value='default'>대표서비스</option>");
		 	systemCd.attr('disabled','disabled');
		 	
		 	addOptionTbl($(this).val()); //서비스별 설정 row 추가
		 	
		}
		//상위서비스 체크 해제
		else{
			var checkService = $(this).val();
			
			delServiceTbl(checkService, 'chk'); //서비스 상세정보 입력 row 삭제
			
			delOptionTbl(checkService); //서비스별 설정 row 삭제
		}
	});
 	
</script>

<script>
function redirectList(){
	$("#frm").attr("action", "/selListServiceApply");

	$("#frm").submit();
}

function addServiceTbl(flag){
	var html ="";
    html += "<tr>"; 
	html += "<td> <select name='serviceCd' class='form-control form-control-sm'> <option value='0'>선택</option>"+
			 " <c:forEach items='${serviceList}' var='list'> "+
			 " <option value='${list.cdId}'>${list.cdNm}</option></c:forEach> </select> </td>";
	html += "<td> <select name='systemCd' class='form-control form-control-sm'> <option value='0'>서비스선택</option> </select> </td>";
	html += "<td> <input name='serviceStartDt' type='text' class='form-control form-control-sm'/> </td>";
	html += "<td> <input name='serviceEndDt' type='text' class='form-control form-control-sm' /> </td>";
	html += "<td> <input name='serviceUrlAddr' type='text' class='form-control form-control-sm'/> </td>";
	
	if(flag == 'chk'){ //체크박스로 서비스 선택 및 추가
		html += "<td > <input name='testLabCheck' type='checkbox' class='form-check-input'>";
		html +=	"<input type='hidden' name='testLabUseYn' value='N'/>";
		html += "<input name='testLabRemarkDesc' type='text' class='form-control form-control-sm' placeholder='비고(용도)' /> </td>";
		html += "<td><input name='testEventCheck' type='checkbox' class='form-check-input'>";
		html += "<input type='hidden' name='testEventAddYn' value='N'/>";
		html += "<input name='testEventRemarkDesc' type='text' class='form-control form-control-sm' placeholder='비고(용도)'/></td>";
	}
	else if(flag == 'btn'){ //추가 버튼 클릭으로 하위서비스 추가
		html += "<td><input name='testLabRemarkDesc' type='text' class='form-control form-control-sm' readonly='true'/> <input type='hidden' name='testLabUseYn' value='N'/></td>";
		html += "<td><input name='testEventRemarkDesc' type='text' class='form-control form-control-sm' readonly='true'/> <input type='hidden' name='testEventAddYn' value='N'/></td>";
	}

	html += "</tr>"; 

	$('tbody[name=serviceTbody]').append(html); 
	
}

function addOptionTbl(serviceCd){
 	var html ="";

	    html += "<tr>"; 
	    html += "<td>"+serviceCd+"</td>";
	    html += "<input type='hidden' name='serviceCdD' value='"+serviceCd+"'/>";
 		html += "<td> <div name='colorGroup' class='input-group colorpicker-component'>"+
 				"<input name='repColorValue' type='text' class='form-control form-control-sm' />"+
 				"<span class='input-group-addon'><i></i></span></div></td>";
 		html += "<td> <select name='fstLangCd' class='form-control form-control-sm'> <option value='0'>사용안함</option>" +
 			 	" <c:forEach items='${languageList}' var='list'> "+
 				" <option value='${list.cdId}'>${list.cdNm}</option></c:forEach> </select></td>";
		html += "<td><select name='scndLangCd' class='form-control form-control-sm'> <option value='0'>사용안함</option>" +
			 	" <c:forEach items='${languageList}' var='list'> "+
				" <option value='${list.cdId}'>${list.cdNm}</option></c:forEach> </select></td>";
		html += "<td> <select name='thrdLangCd' class='form-control form-control-sm'> <option value='0'>사용안함</option>" +
		 		" <c:forEach items='${languageList}' var='list'> "+
				" <option value='${list.cdId}'>${list.cdNm}</option></c:forEach> </select></td>";
		html += "<td> <select name='fothLangCd' class='form-control form-control-sm'> <option value='0'>사용안함</option>" +
		 		" <c:forEach items='${languageList}' var='list'> "+
				" <option value='${list.cdId}'>${list.cdNm}</option></c:forEach> </select></td>";
		html += "<td> <select name='fithLangCd' class='form-control form-control-sm'> <option value='0'>사용안함</option>" +
		 		" <c:forEach items='${languageList}' var='list'> "+
				" <option value='${list.cdId}'>${list.cdNm}</option></c:forEach> </select></td>";		
		html += "</tr>"; 

		$('tbody[name=configTbody]').append(html); 

	   //colorpicker 초기화
		$('div[name=colorGroup]').each(function(){
			$(this).colorpicker({
		        color: '#AAAAAA',
		        format: 'hex'
			});
		});
}

function delServiceTbl(checkService , flag){
	var rowNum = $('tbody[name=serviceTbody] tr').length;
	var removeRow ="";

	for( i=0; i< rowNum; i++){
		removeRow = $('tbody[name=serviceTbody] > tr:eq('+i+')');
			
		if(flag=='chk'){//체크해제
			if(removeRow.find('td:eq(1) select').val() == 'default'){
				if( removeRow.find('td:eq(0) select').val() == checkService){
					removeRow.remove();
				}
			}
		}
	}

	for( i=rowNum-1; i>=0; i--){
		if(flag=='btn'){//삭제버튼
			removeRow = $('tbody[name=serviceTbody] > tr:eq('+i+')');
		
			if(removeRow.find('td:eq(1) select').val() != 'default'){ 
					removeRow.remove();
					return;
			}
		}
	}
	
}

function delOptionTbl(checkService){
	var rowNum = $('#configTable > tbody > tr').length;
	
	for( i=0; i< rowNum; i++){
		var removeRow = $('tbody[name=configTbody] > tr:eq('+i+')');
		
		if( removeRow.find('td:eq(0)').text() == checkService){
			removeRow.remove();
		}
	}
}

function setDatepicker(){
	
	// 달력 초기화
    $('input[name=serviceStartDt]').datepicker({
    		"format" :'yyyy-mm-dd',
            "autoclose": true,
            "todayHighlight":true
    });
 	
 	// 달력 초기화
    $('input[name=serviceEndDt]').datepicker({
    		"format" :'yyyy-mm-dd',
            "autoclose": true,
            "todayHighlight":true
    });
	
}


</script>

 <div class="breadcrumb-holder">
	<div class="container-fluid">
		<ul class="breadcrumb">
			<li class="breadcrumb-item"><a href="/selListServiceApply">서비스관리</a></li>
			<li class="breadcrumb-item">서비스상세</li>
			<li class="breadcrumb-item active">서비스수정</li>
		</ul>
	</div>
</div>

<section class="forms">
<div class="container-fluid">
	<header>
		<h1 class="h3 display">서비스수정</h1>
	</header>
<form class="form-horizontal" id="frm" name="frm" method="POST">
	<div class="col-lg-12">
		<div class="card">
			<div class="card-header d-flex align-items-center">
				<h2 class="h5 display">대회서비스정보</h2>
			</div>
			<div class="card-body">
				<div class="form-group">
					<div class="row">
						<label class="col-sm-2 form-control-label">테넌트ID</label>
						<p>${competition.tenantId}</p>
					</div>
				</div>
				
				<div class="line"></div>
				
				<div class="form-group">
					<div class="row">
						<label class="col-sm-2 form-control-label">대회정보</label>
						<p>[ ${competition.cpCd} ] ${competition.cpNm}</p>
					</div>
				</div>   
                 <input type="hidden" id="tenantId" name="tenantId" value="${competition.tenantId}"/>
                 <input type="hidden" id="cpCd" name="cpCd" value="${competition.cpCd}"/>
             
             <div class="line"></div>

			<div class="row">
				<label class="col-sm-2 form-control-label">* 서비스선택</label>
				<div class="form-group col-sm-8">
                   	<c:forEach items="${serviceList}" var="list" varStatus="status">
              			<input type="checkbox" id="serviceChkBox+${status.index}" name="serviceChkBox" value="${list.cdId}" class="form-control-custom">
               			<label for="serviceChkBox+${status.index}">${list.cdNm}</label>
                   	</c:forEach>
				</div>	
			</div>

		</div>
		</div>
	</div>
	
	<div class="col-lg-12">
           <div class="card">
           	<div class="card-header d-flex align-items-center">
				<h2 class="h5 display">서비스별 설정</h2>
			</div>
             <div class="card-body">	
					<table  id="configTable" name="configTable" class="table">
						<thead>
	                      <tr>
	                        <th>서비스명</th>
	                        <th>컬러선택</th>
	                        <th>1차언어</th>
	                        <th>2차언어</th>
	                        <th>3차언어</th>
	                        <th>4차언어</th>
	                        <th>5차언어</th>
	                      </tr>
	                    </thead>
						<tbody name="configTbody">
						  <c:forEach items="${selServiceApply}" var="selList">
							<tr>
							 <td>${selList.serviceCd}</td>
							  <input type='hidden' name='serviceCdD' value="${selList.serviceCd}"/>
						 	    <td> <div name='colorGroup' class='input-group colorpicker-component'>
					 				<input name='repColorValue' type='text' class='form-control form-control-sm' value="${selList.repColorValue}"/>
					 				<span class='input-group-addon'><i></i></span></div>
					 			</td>
						 	 	<td><select name='fstLangCd' class='form-control form-control-sm'> 
						 				<option value='0'>사용안함</option>
						 			 	<c:forEach items='${languageList}' var='list'>
						 			 		<c:if test="${selList.fstLangCd == list.cdId}">
						 						<option value='${list.cdId}' selected>${list.cdNm}</option>
						 					</c:if>
						 					<c:if test="${selList.fstLangCd != list.cdId}">
						 						<option value='${list.cdId}'>${list.cdNm}</option>
						 					</c:if>
						 				</c:forEach> 
						 			</select>
						 		</td> 
								<td><select name='scndLangCd' class='form-control form-control-sm'> 
										<option value='0'>사용안함</option>
										<c:forEach items='${languageList}' var='list'>
						 			 		<c:if test="${selList.scndLangCd == list.cdId}">
						 						<option value='${list.cdId}' selected>${list.cdNm}</option>
						 					</c:if>
						 					<c:if test="${selList.scndLangCd != list.cdId}">
						 						<option value='${list.cdId}'>${list.cdNm}</option>
						 					</c:if>
						 				</c:forEach> 
									</select>
								</td>
								<td><select name='thrdLangCd' class='form-control form-control-sm'> 
										<option value='0'>사용안함</option>
										<c:forEach items='${languageList}' var='list'>
						 			 		<c:if test="${selList.thrdLangCd == list.cdId}">
						 						<option value='${list.cdId}' selected>${list.cdNm}</option>
						 					</c:if>
						 					<c:if test="${selList.thrdLangCd != list.cdId}">
						 						<option value='${list.cdId}'>${list.cdNm}</option>
						 					</c:if>
						 				</c:forEach> 
									</select>
								</td>
								<td> <select name='fothLangCd' class='form-control form-control-sm'>
										 <option value='0'>사용안함</option>
								 		<c:forEach items='${languageList}' var='list'>
						 			 		<c:if test="${selList.fothLangCd == list.cdId}">
						 						<option value='${list.cdId}' selected>${list.cdNm}</option>
						 					</c:if>
						 					<c:if test="${selList.fothLangCd != list.cdId}">
						 						<option value='${list.cdId}'>${list.cdNm}</option>
						 					</c:if>
						 				</c:forEach> 
									</select>
								</td>
								<td> <select name='fithLangCd' class='form-control form-control-sm'>
										 <option value='0'>사용안함</option>
								 		<c:forEach items='${languageList}' var='list'>
						 			 		<c:if test="${selList.fithLangCd == list.cdId}">
						 						<option value='${list.cdId}' selected>${list.cdNm}</option>
						 					</c:if>
						 					<c:if test="${selList.fithLangCd != list.cdId}">
						 						<option value='${list.cdId}'>${list.cdNm}</option>
						 					</c:if>
						 				</c:forEach>  
									</select>
								</td> 
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

	</div>

	<div class="col-lg-12">
              <div class="card">
               <div class="card-header d-flex align-items-center">
				<h2 class="h5 display">서비스별 상세정보 입력</h2>
				</div>
                <div class="card-body">	
					<div style="float:right;">
						<input type="button" name="addRowBtn" id="addRowBtn" value="추가" class="btn btn-primary"/>
	                	<input type="button" name="delRowBtn" id="delRowBtn" value="삭제" class="btn btn-primary"/>
                	</div>
					<table id="serviceTbl" name="serviceTbl" class="table">
						<thead>
	                      <tr>
	                        <th>서비스명</th>
	                        <th>하위서비스</th>
	                        <th>시작일자</th>
	                        <th>종료일자</th>
	                        <th>서비스URL</th>
	                        <th>테스트랩 사용여부</th>
	                        <th>테스트이벤트 사용여부</th>
	                      </tr>
	                    </thead>
						<tbody name="serviceTbody">
						
						<c:forEach items="${selServiceApply}" var="selList">
							<tr>
							<td> <select name='serviceCd' class='form-control form-control-sm' readOnly='true'> 
								<option value='${selList.serviceCd}'>${selList.serviceCd}</option>
								</select> 
							</td>
							<td> <select name='systemCd' class='form-control form-control-sm' readOnly='true'> 
									<option value="default" selected>대표서비스</option>
								</select> 
							</td>
							<td> <input name='serviceStartDt' type='text' class='form-control form-control-sm' value="${selList.serviceStartDt}"/> </td>
							<td> <input name='serviceEndDt' type='text' class='form-control form-control-sm' value="${selList.serviceEndDt}"/> </td>
							<td> <input name='serviceUrlAddr' type='text' class='form-control form-control-sm' value="${selList.serviceUrlAddr}"/> </td>
	
							<td> 
								<c:if test="${selList.testLabUseYn == useCd}">
									<input name='testLabCheck' type='checkbox' class='form-check-input' checked="checked">
								</c:if>
								<c:if test="${selList.testLabUseYn == NUseCd}">
									<input name='testLabCheck' type='checkbox' class='form-check-input'>
								</c:if>
								<input type='hidden' name='testLabUseYn' value="${selList.testLabUseYn}"/>
								<input name='testLabRemarkDesc' type='text' class='form-control form-control-sm' placeholder='비고(용도)' value="${selList.testLabRemarkDesc}" /> </td>
							<td>
								<c:if test="${selList.testEventAddYn == useCd}">
									<input name='testEventCheck' type='checkbox' class='form-check-input' checked="checked">
								</c:if>
								<c:if test="${selList.testEventAddYn == NUseCd}">
									<input name='testEventCheck' type='checkbox' class='form-check-input'>
								</c:if>
							<input type='hidden' name='testEventAddYn' value="${selList.testEventAddYn}"/>
							<input name='testEventRemarkDesc' type='text' class='form-control form-control-sm' placeholder='비고(용도)' value="${selList.testEventRemarkDesc}"/></td>
							</tr>
						</c:forEach>
						
						<c:forEach items="${selServiceApplyDetail}" var="selList">
							<tr>
							<td> 
							<select name='serviceCd' class='form-control form-control-sm'> 
								<c:forEach items='${serviceList}' var='list'> 
									<c:choose>
									<c:when test='${selList.serviceCd == list.cdId}'>
									 	<option value='${list.cdId}' selected>${list.cdNm}</option>
									</c:when>
									<c:otherwise>
										<option value='${list.cdId}'>${list.cdNm}</option>
									</c:otherwise>
									</c:choose>
								</c:forEach> 
							</select> 
							</td>
							<td> 
							<select name='systemCd' class='form-control form-control-sm'> 
									<option value="${selList.systemCd}"></option>
							</select> 
							</td>
							<td> <input name='serviceStartDt' type='text' class='form-control form-control-sm' value="${selList.serviceStartDt}"/> </td>
							<td> <input name='serviceEndDt' type='text' class='form-control form-control-sm' value="${selList.serviceEndDt}"/> </td>
							<td> <input name='serviceUrlAddr' type='text' class='form-control form-control-sm' value="${selList.serviceUrlAddr}"/> </td>
							<td><input name='testLabRemarkDesc' type='text' class='form-control form-control-sm' readonly='true'/> <input type='hidden' name='testLabUseYn' value='N'/></td>
							<td><input name='testEventRemarkDesc' type='text' class='form-control form-control-sm' readonly='true'/> <input type='hidden' name='testEventAddYn' value='N'/></td>
							</tr>
						</c:forEach>
					</tbody>
					</table>
				</div>
              </div>
	</div>
</form>
		<div class="form-group">
			<div class="col-sm-4 offset-sm-2">
				<input type="button" id="btnCancel" class="btn btn-secondary" value="취소" />
				<input type="button" id="btnRegister" class="btn btn-primary" value="등록"></input>
			</div>
		</div>	

</div>

</section>