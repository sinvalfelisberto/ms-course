package com.felisberto.hroauth.services;

import com.felisberto.hroauth.FeignClients.UserFeignClients;
import com.felisberto.hroauth.entities.User;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import static org.slf4j.LoggerFactory.getLogger;

@Service
public class UserService implements UserDetailsService {

    private static Logger logger = getLogger(UserService.class);

    @Autowired
    private UserFeignClients userFeignClients;

    //ficou como material educacional.
    public User findByEmail(String email) {
        User user = userFeignClients.findByEmail(email).getBody();
        if(user == null){
            logger.error("Email not found: " + email);
            throw new IllegalArgumentException("Email not found");
        }
        logger.info("Email found: " + email);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userFeignClients.findByEmail(email).getBody();
        if(user == null){
            logger.error("Email not found: " + email);
            throw new UsernameNotFoundException("Email not found");
        }
        logger.info("Email found: " + email);
        return user;
    }
}
