package com.msa.console.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.msa.console.model.Member;

public interface UserService {

    public Member findByUserNameOrEmail(String username);
    
    public void iniDataForTesting();
    
    @Transactional(rollbackFor=Exception.class)
    public void createMember(Member member) ;
    
    public List<Member> getMember();
}
