package com.msa.console.service;

import java.util.List;

import com.msa.console.model.AdminModel;

public interface AdminService {

    public List<AdminModel> selectListAdmin(AdminModel adminModel);
    
}
