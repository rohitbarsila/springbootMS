package com.customer.UserDtls.controller;


import com.customer.UserDtls.config.TokenProvider;
import com.customer.UserDtls.exception.InvalidData;
import com.customer.UserDtls.model.AuthToken;
import com.customer.UserDtls.model.LoginUser;
import com.customer.UserDtls.model.UserData;
import com.customer.UserDtls.model.UserDto;
import com.customer.UserDtls.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Value("${jwt.header.string}")
    public String HEADER_STRING;

    @Value("${jwt.token.prefix}")
    public String TOKEN_PREFIX;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody LoginUser loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public UserData saveUser(@RequestBody UserDto user) throws InvalidData {
        if (userService.findAtt(user.getUsername(), user.getEmail(), user.getPhone()))
            throw new InvalidData();
        return userService.save(user);
    }

    @RequestMapping(value = "/userDTLS", method = RequestMethod.POST)
    @ResponseBody
    public UserData getTestsListByUserId(HttpServletRequest req) {
        String authToken = req.getHeader(HEADER_STRING).replace(TOKEN_PREFIX, "");
        return userService.findByUsername(jwtTokenUtil.getUsernameFromToken(authToken)).getUserFromDto();
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/userping", method = RequestMethod.GET)
    public String userPing() {
        return "Any User Can Read This";
    }
}
