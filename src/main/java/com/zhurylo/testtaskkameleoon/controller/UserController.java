package com.zhurylo.testtaskkameleoon.controller;

import com.zhurylo.testtaskkameleoon.dto.UserDto;
import com.zhurylo.testtaskkameleoon.exception.EmailAlreadyExistsException;
import com.zhurylo.testtaskkameleoon.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserDto dto) {
        try {
            userService.createUser(dto);
        } catch (EmailAlreadyExistsException e) {
            log.error(String.format("%s already exists", dto.email()));
        }
        return ResponseEntity.ok(dto);
    }


}