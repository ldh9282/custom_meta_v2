package com.custom.met.login.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.custom.met.cmmn.constant.CustomAuthCode;
import com.custom.met.cmmn.exception.CustomException;
import com.custom.met.cmmn.exception.CustomExceptionCode;
import com.custom.met.cmmn.model.CustomMap;
import com.custom.met.cmmn.security.service.MemberDao;

@Service
public class LoginService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MemberDao memberDao;
	
	@Transactional
	public CustomMap insertMember(CustomMap params) {
		CustomMap resultMap = new CustomMap();
		
		try {
			CustomMap memberCnt = memberDao.selectMemberCnt(params);
			
			if (memberCnt.getInt("cnt") > 0) {
				throw new CustomException(CustomExceptionCode.ERR999, new String[] { "회원아이디 중복입니다." });
			}
			
			String memSno = memberDao.selectMemSno();
			
			CustomMap member = new CustomMap();
			member.put("memSno", memSno);
			member.put("memId", params.getString("memId"));
			member.put("memPw", passwordEncoder.encode(params.getString("memPw")));
			member.put("sysCreator", params.getString("sysCreator"));
			
			memberDao.insertMember(member);
			
			List<String> authList = new ArrayList<>();
//			authList.add(CustomAuthCode.A001.getCode());
			authList.add(CustomAuthCode.A002.getCode());
			
			for (String auth : authList) {
				String memAuthSno = memberDao.selectMemAuthSno();
				CustomMap memberAuth = new CustomMap();
				
				memberAuth.put("memSno", memSno);
				memberAuth.put("memAuthSno", memAuthSno);
				memberAuth.put("memId", params.getString("memId"));
				memberAuth.put("memAuth", auth);
				memberAuth.put("sysCreator", params.getString("sysCreator"));
				
				memberDao.insertMemberAuth(memberAuth);
				
			}
			
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR999, new String[] { e.getMessage() }, e);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR521, new String[] { "회원정보" }, e);
		}
		
		return resultMap;
	}
}
