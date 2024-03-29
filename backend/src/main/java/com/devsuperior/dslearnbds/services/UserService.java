package com.devsuperior.dslearnbds.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslearnbds.repositories.UserRepository;
import com.devsuperior.dslearnbds.services.exceptions.ResourceNotFoundException;
import com.devsuperior.dslearnbds.dto.UserDTO;
import com.devsuperior.dslearnbds.entities.User;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthService authService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByEmail(username);
        if (user == null) {
            //exibe mensagem no console da aplicação 
            logger.error("User not found:" + username);
            throw new UsernameNotFoundException("Email not found");
        }
        //exibe mensagem no console da aplicação
        logger.info("User found: " + username);
        return user;
    } 
    
    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {

        authService.validateSelfOrAdmin(id);

        Optional<User> obj = repository.findById(id);        
        User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found")); 
        return new UserDTO(entity);
    }
    
}
