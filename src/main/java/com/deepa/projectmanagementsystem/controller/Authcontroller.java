package com.deepa.projectmanagementsystem.controller;


import com.deepa.projectmanagementsystem.config.JwtProvider;
import com.deepa.projectmanagementsystem.model.User;
import com.deepa.projectmanagementsystem.repository.UserRepository;
import com.deepa.projectmanagementsystem.request.LoginRequest;
import com.deepa.projectmanagementsystem.response.AuthResponse;
import com.deepa.projectmanagementsystem.service.SubscriptionService;
import com.deepa.projectmanagementsystem.service.customUserdetailsImpli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class Authcontroller {

    @Autowired
    private UserRepository userrepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private customUserdetailsImpli customuserdetailsimpli;
    
    @Autowired
    private SubscriptionService subscriptionservice;

    // @PostMapping("/signup")
    // public ResponseEntity<User> createUserHandler(@RequestBody User user) throws Exception {
    //     User isUserExits = userrepository.findByEmail(user.getEmail());

    //     if (isUserExits != null) {
    //         throw new Exception("Email already exist with another account");

    //     }
    //     User createdUser = new User();
    //     createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
    //     createdUser.setEmail(user.getEmail());
    //     createdUser.setFullName(user.getFullName());

    //     User savedUser = userrepository.save(createdUser);

    //    //createdUser.setProjectSize(user.getProjectSize() != null ? user.getProjectSize() : 5);


    //    subscriptionservice.createSubscription(savedUser);
        
    //     Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

    //     SecurityContextHolder.getContext().setAuthentication(authentication);

    //     String jwt = JwtProvider.generateToken(authentication);

    //     AuthResponse res = new AuthResponse();
    //     res.setMessage("signup success");
    //     res.setJwt(jwt);

    //     return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    // }

    @PostMapping("/signup")
public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {
    if (userrepository.findByEmail(user.getEmail()) != null) {
        throw new Exception("Email already exist with another account");
    }

    User createdUser = new User();
    createdUser.setFullName(user.getFullName());
    createdUser.setEmail(user.getEmail());
    createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
    createdUser.setProjectSize(user.getProjectSize() != null ? user.getProjectSize() : 5);

    User savedUser = userrepository.save(createdUser);

    subscriptionservice.createSubscription(savedUser);

    Authentication authentication = authenticate(user.getEmail(), user.getPassword());
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = JwtProvider.generateToken(authentication);

    AuthResponse res = new AuthResponse();
    res.setMessage("Signup success");
    res.setJwt(jwt);

    return new ResponseEntity<>(res, HttpStatus.CREATED);
}

    @PostMapping("/signing")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        Authentication authentication = authenticate(username, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = JwtProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse();
        res.setMessage("signup success");
        res.setJwt(jwt);

        return new ResponseEntity<>(res, HttpStatus.CREATED);


    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customuserdetailsimpli.loadUserByUsername(username);
        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
        throw new BadCredentialsException("Invalid password");

    }
    return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
}
}
