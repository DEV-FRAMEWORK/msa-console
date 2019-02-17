package com.msa.console.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.msa.console.common.Pagination;
import com.msa.console.enums.CommonEnums;
import com.msa.console.model.CodeModel;
import com.msa.console.model.ContractExtModel;
import com.msa.console.model.ContractModel;
import com.msa.console.model.CustomerModel;
import com.msa.console.service.CommonService;
import com.msa.console.service.ContractService; 

@Controller
public class ContractController { 

    //private final Logger logger = LoggerFactory.getLogger(ContController.class);
	private Integer rowPerPage = 10;  
	private Integer rowPerPagePop = 5;
	
    @Autowired
    ContractService contractService;
    
    @Autowired
    CommonService commonService;

    @RequestMapping("/insContractForm") 
    public String insContract(@RequestParam Map<String, String> param, ContractExtModel contractExtModel,Model model) {
    	String tid = commonService.selTenantIdSeq();
    	//코드 조회
    	List<CodeModel> contStatCdList = commonService.selCode(CommonEnums.CONT_STAT_CD.getValue());
    	List<CodeModel> networkFgCdList = commonService.selCode(CommonEnums.NETWORK_FG_CD.getValue());
    	List<CodeModel> passwordLodCdList = commonService.selCode(CommonEnums.PASSWORD_LOD_CD.getValue());
    	List<CodeModel> rnwlCyclCd = commonService.selCode(CommonEnums.RNWL_CYCL_CD.getValue());
    	
    	model.addAttribute("contStatCdList", contStatCdList);         //계약상태코드
    	model.addAttribute("networkFgCdList", networkFgCdList);		  //네트워크구분코드
    	model.addAttribute("passwordLodCdList", passwordLodCdList);	  //비밀번호난이도코드
    	model.addAttribute("rnwlCyclCd", rnwlCyclCd);				  //비밀번호갱신주기코드
    	model.addAttribute("tid", tid);
    	
    	//아이디조회 모달 팝업용 
    	// 페이징 처리
		String page = StringUtils.defaultIfEmpty(param.get("page"), "1");
		if(NumberUtils.toInt(page) < 1) page = "1";
		
		int rows = StringUtils.isNotEmpty( param.get("rowPerPagePop"))? NumberUtils.toInt(param.get("rowPerPagePop")) : rowPerPagePop;
		
		contractExtModel.setRowPerPage(rows);
		contractExtModel.setPage(NumberUtils.toInt(page));
		contractExtModel.setSkipCount(rows * (NumberUtils.toInt(page) - 1));
    	
		
        List<ContractExtModel> list = contractService.selListCust(contractExtModel);  
        
        System.out.println("list : "+ list.get(0));
        //페이징 처리
        Pagination pagination = new Pagination();
		if(list != null && !list.isEmpty() ){
			pagination.setTotalRow(list.get(0).getTotalCount()).setRowPerPage(rows).setCurrentPage(page);
		} else {
			pagination.setTotalRow(0);
		}
		
		System.out.println("addate : "+list.get(0).getAdDate());
		
		model.addAttribute("list", list);
        model.addAttribute("adminModel", contractExtModel); 
		model.addAttribute("pagination", pagination);
    	
		
		//모달팝업 페이징을 위한 플레그 처리 
		String mf = StringUtils.defaultIfEmpty(param.get("modalFlag"), "N");   
		if(mf.equals("Y")){
			model.addAttribute("modalFlag", "popshow");
		}else {
			model.addAttribute("modalFlag", "pophide");
		}
    	
    	return "/contract/insContract";
    }
    
	@RequestMapping("/selListContract")
    public String selectListContract(@RequestParam Map<String, String> param, ContractExtModel contractExtModel, Model model) {
		// 페이징 처리
		String page = StringUtils.defaultIfEmpty(param.get("page"), "1");
		if(NumberUtils.toInt(page) < 1) page = "1";
		
		int rows = StringUtils.isNotEmpty( param.get("rowPerPage"))? NumberUtils.toInt(param.get("rowPerPage")) : rowPerPage;
		
		contractExtModel.setRowPerPage(rows);
		contractExtModel.setPage(NumberUtils.toInt(page));
		contractExtModel.setSkipCount(rows * (NumberUtils.toInt(page) - 1));
    	
		
        List<ContractExtModel> list = contractService.selListContract(contractExtModel);  
        
        System.out.println("list : "+ list.get(0));
        //페이징 처리
        Pagination pagination = new Pagination();
		if(list != null && !list.isEmpty() ){
			pagination.setTotalRow(list.get(0).getTotalCount()).setRowPerPage(rows).setCurrentPage(page);
		} else {
			pagination.setTotalRow(0);
		}
		
		System.out.println("addate : "+list.get(0).getAdDate());
		
		model.addAttribute("list", list);
        model.addAttribute("contractExtModel", contractExtModel); 
		model.addAttribute("pagination", pagination);
    	
    	return "/contract/selListContract";
    	
    }
	
	@RequestMapping("/selContractView")
    public String selContractView(@RequestParam Map<String, String> param, ContractExtModel contractExtModel, Model model) {
		
		ContractExtModel cem = contractService.selContract(contractExtModel);  
        
		cem.setContStatCd(commonService.selCodeByCdId(CommonEnums.CONT_STAT_CD.getValue(), cem.getContStatCd()));
		cem.setNetworkFgCd(commonService.selCodeByCdId(CommonEnums.NETWORK_FG_CD.getValue(), cem.getNetworkFgCd()));
		cem.setPasswordLodCd(commonService.selCodeByCdId(CommonEnums.PASSWORD_LOD_CD.getValue(), cem.getPasswordLodCd()));
		cem.setPasswordRnwlCyclCd(commonService.selCodeByCdId(CommonEnums.RNWL_CYCL_CD.getValue(), cem.getPasswordRnwlCyclCd()));
		
		model.addAttribute("cem", cem); 
       return "/contract/selContract";
    	
    }
	
	@RequestMapping("/upContractForm")
    public String upContractForm(@RequestParam Map<String, String> param, ContractExtModel contractExtModel, Model model) {
		//코드 조회
    	List<CodeModel> contStatCdList = commonService.selCode(CommonEnums.CONT_STAT_CD.getValue());
    	List<CodeModel> networkFgCdList = commonService.selCode(CommonEnums.NETWORK_FG_CD.getValue());
    	List<CodeModel> passwordLodCdList = commonService.selCode(CommonEnums.PASSWORD_LOD_CD.getValue());
    	List<CodeModel> rnwlCyclCd = commonService.selCode(CommonEnums.RNWL_CYCL_CD.getValue());
    	
    	model.addAttribute("contStatCdList", contStatCdList);         //계약상태코드
    	model.addAttribute("networkFgCdList", networkFgCdList);		  //네트워크구분코드
    	model.addAttribute("passwordLodCdList", passwordLodCdList);	  //비밀번호난이도코드
    	model.addAttribute("rnwlCyclCd", rnwlCyclCd);				  //비밀번호갱신주기코드
    	
    	// 페이징 처리
		String page = StringUtils.defaultIfEmpty(param.get("page"), "1");
		if(NumberUtils.toInt(page) < 1) page = "1";
		
		int rows = StringUtils.isNotEmpty( param.get("rowPerPagePop"))? NumberUtils.toInt(param.get("rowPerPagePop")) : rowPerPagePop;
		
		contractExtModel.setRowPerPage(rows);
		contractExtModel.setPage(NumberUtils.toInt(page));
		contractExtModel.setSkipCount(rows * (NumberUtils.toInt(page) - 1));
    	
		
        List<ContractExtModel> list = contractService.selListCust(contractExtModel);  
        
        System.out.println("list : "+ list.get(0));
        //페이징 처리
        Pagination pagination = new Pagination();
		if(list != null && !list.isEmpty() ){
			pagination.setTotalRow(list.get(0).getTotalCount()).setRowPerPage(rows).setCurrentPage(page);
		} else {
			pagination.setTotalRow(0);
		}
		
		System.out.println("addate : "+list.get(0).getAdDate());
		
		model.addAttribute("list", list);
        model.addAttribute("adminModel", contractExtModel); 
		model.addAttribute("pagination", pagination);
    	
		
		//모달팝업 페이징을 위한 플레그 처리 
		String mf = StringUtils.defaultIfEmpty(param.get("modalFlag"), "N");   
		if(mf.equals("Y")){
			model.addAttribute("modalFlag", "popshow");
		}else {
			model.addAttribute("modalFlag", "pophide");
		}
    			
		ContractExtModel cem = contractService.selContract(contractExtModel);  
        
		cem.setContStatCd(commonService.selCodeByCdId(CommonEnums.CONT_STAT_CD.getValue(), cem.getContStatCd()));
		cem.setNetworkFgCd(commonService.selCodeByCdId(CommonEnums.NETWORK_FG_CD.getValue(), cem.getNetworkFgCd()));
		cem.setPasswordLodCd(commonService.selCodeByCdId(CommonEnums.PASSWORD_LOD_CD.getValue(), cem.getPasswordLodCd()));
		cem.setPasswordRnwlCyclCd(commonService.selCodeByCdId(CommonEnums.RNWL_CYCL_CD.getValue(), cem.getPasswordRnwlCyclCd()));
		
		model.addAttribute("cem", cem); 
       return "/contract/upContract";
    	
    }
	
	@RequestMapping("/upContract")
    public String upContract(@RequestParam Map<String, String> param, ContractExtModel contractExtModel, Model model,@RequestParam("validStartDt") String validStartDt,
    		@RequestParam("validEndDt") String validEndDt) {
		
		//contractExtModel.getCustId();
		//contractExtModel.getTenantId();
		System.out.println("map : "+param.toString());
		
		//유효일 처리
    	validStartDt = validStartDt.replaceAll("-", "");
    	validEndDt = validEndDt.replaceAll("-", "");
		
    	contractExtModel.setValidStartDt(validStartDt);
    	contractExtModel.setValidEndDt(validEndDt);
    	
		//고객 업데이트
		contractService.upCust(contractExtModel);
		
		//계약 업데이트
		contractService.upContract(contractExtModel);
		//업데이트 완료 플레그 
    	model.addAttribute("result", "1");
    	
    	return "jsonView";
    }
	
	@RequestMapping("/delContract")
    public String delContract(@RequestParam Map<String, String> param, ContractExtModel contractExtModel, Model model) {
		
		//contractExtModel.getCustId();
		//contractExtModel.getTenantId();
		
		//계약 삭제
		contractService.delContract(contractExtModel);
		System.out.println("계약 삭제 완료!");
		
		List<ContractExtModel> list = contractService.selListCustCnt(contractExtModel);
		System.out.println("list.size : " + list.size());
		
		if(list.size() <1) {
			//고객 삭제
			contractService.delCust(contractExtModel);
		}
		
		//등록 완료 플레그 
    	model.addAttribute("result", "1");
    	
    	return "jsonView";
    }
	
    @PostMapping("/insContract")
    @Transactional(rollbackFor=Exception.class)
    public String insContract(Model model ,ContractExtModel contractExtModel,
    		@RequestParam(value="custId", required=true) String custId, 
    		@RequestParam("custNm") String custNm, 
    		@RequestParam("repFaxNo") String repFaxNo,
    		@RequestParam("repTelNo") String repTelNo,
    		@RequestParam("corpAdNo") String corpAdNo,
    		@RequestParam("mgrNm") String mgrNm,
    		@RequestParam("mgrEmailAddr") String mgrEmailAddr,
    		@RequestParam("mgrTelNo") String mgrTelNo,
    		@RequestParam("tenantId") String tenantId,
    		@RequestParam("contNm") String contNm,
    		@RequestParam("validStartDt") String validStartDt,
    		@RequestParam("validEndDt") String validEndDt,
    		@RequestParam("contStatCd") String contStatCd,
    		@RequestParam("networkFgCd") String networkFgCd,
    		@RequestParam("passwordLodCd") String passwordLodCd,
    		@RequestParam("passwordMinLen") String passwordMinLen,
    		@RequestParam("passwordRnwlCyclCd") String passwordRnwlCyclCd,
    		@RequestParam("passwordUseLmtYn") String passwordUseLmtYn,
    		@RequestParam("passwordPoseYn") String passwordPoseYn,
    		HttpServletRequest req, HttpServletResponse res) throws Exception {
		
    	
    		//유효일 처리
        	validStartDt = validStartDt.replaceAll("-", "");
        	validEndDt = validEndDt.replaceAll("-", "");
        	
        	System.out.println(custId+" "+custNm+" "+repFaxNo+" "+repTelNo +" "+corpAdNo +" "+mgrNm +" "+mgrEmailAddr +" "+mgrTelNo +" "+contNm +" "+
        			validStartDt+" "+ validEndDt+" "+contStatCd+" "+ networkFgCd+" "+ passwordLodCd +" "+passwordMinLen+" "+ passwordRnwlCyclCd +" "+passwordUseLmtYn+" "+ passwordPoseYn
        			);
        	
        	//고객 정보 체크 
        	contractExtModel.setCustId(custId);
        	List<ContractExtModel> list = contractService.selListContractCnt(contractExtModel);
        	System.out.println("list.size : " + list.size());
        	
        	//로그인정보 
        	User principal = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        	String userId = principal.getUsername();
        	
        	//계약 등록
        	ContractModel contractModel = new ContractModel();
        	
        	if(list.size() <1) {
    	    	CustomerModel customerModel = new CustomerModel();
    	    	customerModel.setCustId(custId);
    	    	customerModel.setCustNm(custNm);
    	    	customerModel.setRepFaxNo(repFaxNo);
    	    	customerModel.setRepTelNo(repTelNo);
    	    	customerModel.setCorpAdNo(corpAdNo);
    	    	customerModel.setMgrNm(mgrNm);
    	    	customerModel.setMgrEmailAddr(mgrEmailAddr);
    	    	customerModel.setMgrTelNo(mgrTelNo);
    	    	customerModel.setCrtId(userId);
    	    	customerModel.setCrtIp(req.getRemoteAddr());
    	    	customerModel.setUdtId(userId);
    	    	customerModel.setUdtIp(req.getRemoteAddr());
    	    	//고객정보 등록
    	    	contractService.insCustomer(customerModel);
        	}
        	
        	contractModel.setTenantId(tenantId);
        	contractModel.setCustId(custId);
        	contractModel.setContNm(contNm);
        	contractModel.setValidStartDt(validStartDt);
        	contractModel.setValidEndDt(validEndDt);
        	contractModel.setContStatCd(contStatCd);
        	contractModel.setNetworkFgCd(networkFgCd);
        	contractModel.setPasswordLodCd(passwordLodCd);
        	contractModel.setPasswordMinLen(Integer.parseInt(passwordMinLen));
        	contractModel.setPasswordRnwlCyclCd(passwordRnwlCyclCd);
        	contractModel.setPasswordUseLmtYn(passwordUseLmtYn);
        	contractModel.setPasswordPoseYn(passwordPoseYn);
        	contractModel.setCrtId(userId);
        	contractModel.setCrtIp(req.getRemoteAddr());
        	contractModel.setUdtId(userId);
        	contractModel.setUdtIp(req.getRemoteAddr());
        	//계약정보등록
        	contractService.insContract(contractModel);
        	
        	//등록 완료 플레그 
        	model.addAttribute("result", "1");
        	
    	return "jsonView";
    	
    }
    

}