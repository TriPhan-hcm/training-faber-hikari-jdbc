package com.faber.airmgr.services.impl;

import com.faber.airmgr.data.entities.UserEntity;
import com.faber.airmgr.data.repositories.impl.RoleDAO;
import com.faber.airmgr.data.repositories.impl.UserDAO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService  {
    
    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private UserDAO userDAO;
    
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        
    }
    
}
