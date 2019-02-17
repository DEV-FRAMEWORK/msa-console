package com.msa.console.api.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.apache.commons.lang.StringUtils;
//import org.apache.commons.lang.math.NumberUtils;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msa.console.common.Pagination;
import com.msa.console.model.AdminModel;
import com.msa.console.service.AdminService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RestApiController {

	@Autowired
	AdminService adminService;
	
	private Integer rowPerPage = 10;  
	
    @RequestMapping("/getAdmin")
    public Map<String, String> selectListAdmin(@RequestParam Map<String, String> param, AdminModel adminModel, Model model) {
		// 페이징 처리) {
    	System.out.println("addate : ++++++++++++");
   
    	// 페이징 처리
    			String page = StringUtils.defaultIfEmpty(param.get("page"), "1");
    			if(NumberUtils.toInt(page) < 1) page = "1";
    			
    			int rows = StringUtils.isNotEmpty( param.get("rowPerPage"))? NumberUtils.toInt(param.get("rowPerPage")) : rowPerPage;
    			
    			adminModel.setRowPerPage(rows);
    			adminModel.setPage(NumberUtils.toInt(page));
    			adminModel.setSkipCount(rows * (NumberUtils.toInt(page) - 1));
    	    	
    			
    	        List<AdminModel> list = adminService.selectListAdmin(adminModel);  
    	      
    	        //페이징 처리
    	        Pagination pagination = new Pagination();
    			if(list != null && !list.isEmpty() ){
    				pagination.setTotalRow(list.get(0).getTotalCount()).setRowPerPage(rows).setCurrentPage(page);
    			} else {
    				pagination.setTotalRow(0);
    			}
    			
    			System.out.println("addate : "+list.get(0).getAdDate());
    			
    			Map<String, String > map = new HashMap<>();
    			map.put(list.get(0).getAdminId(), "Ok");
    			map.put(list.get(1).getAdminNm(), "Ok");
    			
    	    	
    	    	return map;
    	    	
    	
    }
//    @RequestMapping("/getId")
//    public String getMemberById(AdminModel adminModel) {
//    	System.out.println("addate : ++++++++++++");
//	
//        Member rooms = adminService.getMemberById("1");  
//       
//        System.out.println("addate : "+rooms);
//    	return "rooms";
//    	
//    }
    @RequestMapping("/list")
    public Map<String, String> list(){
        Map<String, String > map = new HashMap<>();
        map.put("row","제대로 나오고 있어용 ");
       
        return map;
    }

}
