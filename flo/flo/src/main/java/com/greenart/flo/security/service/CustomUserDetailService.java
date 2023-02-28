package com.greenart.flo.security.service;

import com.greenart.flo.repository.AdminRepository;
import com.greenart.flo.entity.AdminEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
//    private final MemberMapper memberMapper;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return createUserDetails(memberMapper.getMemberInfoByMemberId(username));
        return createUserDetails(adminRepository.findByAdminId(username));
    }
    public UserDetails createUserDetails(AdminEntity member) {
        return User.builder().username(member.getAdminId())
                .password(passwordEncoder.encode(member.getAdminPwd()))
                .roles(member.getAdminRole())
                .build();
    }
}

