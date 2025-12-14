package edu.icet.service.impl;

import edu.icet.model.dto.User;
import edu.icet.model.dto.UserPrincipal;
import edu.icet.model.entity.UserEntity;
import edu.icet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity=userRepository.findByUsername(username);
        User user=new User(userEntity.getUserId(),
                userEntity.getUsername(),
                userEntity.getNIC(),
                userEntity.getPhone(),
                userEntity.getPassword(),
                userEntity.getRole(),
                userEntity.getCreatedAt(),
                userEntity.getUpdatedAt()
                );
        if(userEntity==null){
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("User Name Not Found");
        }
return new UserPrincipal(user);
    }
}
