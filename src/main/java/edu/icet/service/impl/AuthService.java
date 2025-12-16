package edu.icet.service.impl;


import edu.icet.model.dto.request.UserRequest;
import edu.icet.model.dto.response.UserResponse;
import edu.icet.model.entity.UserEntity;
import edu.icet.repository.UserRepository;
import edu.icet.security.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    JWTService jwtService;

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);


    public UserResponse register(UserRequest user){

        UserEntity userEntity=new UserEntity();

        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(encoder.encode(user.getPassword()));


        userRepository.save(userEntity);

        return new UserResponse(
                userEntity.getUserId(),
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getNIC(),
                userEntity.getPhone(),
                userEntity.getPassword(),
                userEntity.getRole(),
                userEntity.getCreatedAt(),
                userEntity.getUpdatedAt()
        );
    }

    public String verify(UserRequest user) {
        Authentication authentication=manager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
        if(authentication.isAuthenticated())
            return jwtService.generateToken(user.getEmail());

        return "fail";

    }
}
