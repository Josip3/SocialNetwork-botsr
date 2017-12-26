package ua.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import ua.entity.User;
import ua.request.LoginRequest;
import ua.request.delete;
import ua.service.restService.UserRestService;


@CrossOrigin
@RestController
@RequestMapping("/registration")
public class UserRestController {

    @Value("${lgs.token.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

//    @Autowired
//    private TokenUtils tokenUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRestService userRestService;



    @PutMapping("/save")
    public User register(@RequestBody User user){
        return userRestService.register(user);
    }

//    @RequestMapping(method = RequestMethod.POST,value = "/login")
//    public ResponseEntity<?> authOnRequest(@RequestBody LoginRequest loginRequest){
//       //о це вроді не робить
//         Authentication authentication =
//                this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        UserDetails userDetails = this.userDetailsService.loadUserByUsername(loginRequest.getEmail());
//        String token = this.tokenUtils.generateToken(userDetails);
//        System.err.println("USER REST CONTROLLER : : 'authOnRequest' " + token + " token");
//        System.err.println("USER REST CONTROLLER : : 'authOnRequest' " + loginRequest.getEmail()+loginRequest.getPassword());
//        return  ResponseEntity.ok(token);
//    }


    @DeleteMapping("/delete")
    public Boolean delete(@RequestBody delete request){
        return userRestService.delete(request.getId());
    }






}
