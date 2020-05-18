package com.lizavetakeura.controller;

import com.lizavetakeura.api.service.IUserService;
import com.lizavetakeura.service.dto.CredDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private IUserService userService;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<CredDTO> signUp(@RequestBody CredDTO credDTO) {
        userService.createUser(credDTO);
        return ResponseEntity.status(HttpStatus.OK).body(credDTO);
    }

    @PostMapping("/update")
    public ResponseEntity<CredDTO> exitProfile(@RequestBody CredDTO credDTO) {
        userService.updateUser(credDTO);
        return ResponseEntity.status(HttpStatus.OK).body(credDTO);
    }
}
