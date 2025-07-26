package com.custom.met.cmmn.security.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.custom.met.cmmn.model.CustomMap;
import com.custom.met.cmmn.security.model.CustomUser;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberDao memberDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CustomMap params = new CustomMap();
		
		params.put("memId", username);
		
		CustomMap member = memberDao.selectMember(params);
		List<CustomMap> memberAuthList = memberDao.selectMemberAuthList(member);
		
		CustomMap theMember = new CustomMap();
		theMember.put("memId", member.getString("memId"));
		theMember.put("memPw", member.getString("memPw"));
		
		List<String> authList = new ArrayList<>();
		
		for (CustomMap memberAuth : memberAuthList) {
			authList.add(memberAuth.getString("memAuth"));
		}
		theMember.put("memAuthList", authList);
		
		return new CustomUser(theMember);
	}

}
