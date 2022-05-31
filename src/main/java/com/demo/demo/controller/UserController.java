package com.demo.demo.controller;

import com.demo.demo.dto.UserDto;
import com.demo.demo.mapper.UserMapper;
import com.demo.demo.model.Users;
import com.demo.demo.service.UserService;
import com.demo.demo.utils.HeaderUtil;
import com.demo.demo.utils.MessageCodeUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/api/user")
@SecurityRequirement(name = "api")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);


    private final String DATE_PARAM_FORMAT = "dd-MM-yyyy";
    final UserMapper userMapper;
    final UserService userService;

    public UserController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }


    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody UserDto userDto) {
        try {
            Users request = userMapper.toEntity(userDto);
            Users users = userService.save(request);
            return ResponseEntity.ok().body(userMapper.toDto(users));
        } catch (Exception e) {
            HttpHeaders headers = HeaderUtil.createAlert(MessageCodeUtil.CM_001.getErrorCode(),
                    MessageCodeUtil.CM_001.getErrorMessage());
            return ResponseEntity.badRequest().headers(headers).build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        try {
            Optional<Users> entity = userService.findById(id);
            LOGGER.info("entity" + entity);
            if(entity.isEmpty()){
                HttpHeaders headers = HeaderUtil.createAlert(MessageCodeUtil.US_002.getErrorCode(),
                        MessageCodeUtil.US_002.getErrorMessage());
                return ResponseEntity.badRequest().headers(headers).build();
            }
            else {
                UserDto newuserDto = userMapper.toDto(entity.get());
                newuserDto.setName(userDto.getName());
                newuserDto.setEmail(userDto.getEmail());
                Users request = userMapper.toEntity(newuserDto);
                Users users = userService.saveUpdate(id, request);


                return ResponseEntity.created(new URI("/api/user/" + users.getName()))
                        .headers(HeaderUtil.createAlert("user.updated", users.getName()))
                        .body(userMapper.toDto(users));
            }
        } catch (Exception e) {
            HttpHeaders headers = HeaderUtil.createAlert(MessageCodeUtil.CM_001.getErrorCode(),
                    MessageCodeUtil.CM_001.getErrorMessage());
            return ResponseEntity.badRequest().headers(headers).build();
        }
    }

//    @GetMapping("search")
//    public ResponseEntity<List<UserDto>> list(@RequestParam(required = false) String q,
//                                              @RequestParam(defaultValue = "0") int page,
//                                              @RequestParam(defaultValue = "20") int size) {
//        try {
//            Pageable pageable = PageRequest.of(page, size);
//            Page<UserDto> data;
//            if (q != null) {
//                String filter = "username=like=" + q;
//                data = userRepository.findAll(RSQLJPASupport.toSpecification(filter), pageable).map(userMapper::userToUserDto);
//            } else {
//                data = userRepository.findAll(pageable).map(userMapper::userToUserDto);
//            }
//
//            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(data, "/api/user");
//            return ResponseEntity.ok().headers(headers).body(data.getContent());
//        } catch (Exception e) {
//            HttpHeaders headers = HeaderUtil.createAlert(MessageCodeUtil.CM_001.getErrorCode(),
//                    MessageCodeUtil.CM_001.getErrorMessage());
//            return ResponseEntity.badRequest().headers(headers).build();
//        }
//    }

    @GetMapping("detail/{email}")
    public ResponseEntity<UserDto> get(@PathVariable String email) {
        try {
            Optional<Users> user = userService.findByEmail(email);
            if (user.isEmpty()) {
                HttpHeaders headers = HeaderUtil.createAlert(MessageCodeUtil.US_001.getErrorCode(),
                        MessageCodeUtil.US_001.getErrorMessage());
                return ResponseEntity.badRequest().headers(headers).build();
            } else {
                UserDto userDto = userMapper.toDto(user.get());
                return ResponseEntity.created(new URI("/api/user/" + userDto.getName()))
                        .headers(HeaderUtil.createAlert("user.get", userDto.getName()))
                        .body(userDto);
            }
        } catch (Exception e) {
            HttpHeaders headers = HeaderUtil.createAlert(MessageCodeUtil.CM_001.getErrorCode(),
                    MessageCodeUtil.CM_001.getErrorMessage());
            return ResponseEntity.badRequest().headers(headers).build();
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            Optional<Users> entity = userService.findById(id);
            LOGGER.info("delete " + entity);
            if (entity.isEmpty()) {
                HttpHeaders headers = HeaderUtil.createAlert(MessageCodeUtil.US_002.getErrorCode(),
                        MessageCodeUtil.US_002.getErrorMessage());
                return ResponseEntity.badRequest().headers(headers).build();
            } else {
                UserDto newuserDto = userMapper.toDto(entity.get());
                Users request = userMapper.toEntity(newuserDto);
                userService.delete(request);
                return ResponseEntity.ok().headers(HeaderUtil.createAlert("user.deleted", String.valueOf(id))).build();
            }
        } catch (Exception e) {
            HttpHeaders headers = HeaderUtil.createAlert(MessageCodeUtil.CM_001.getErrorCode(),
                    MessageCodeUtil.CM_001.getErrorMessage());
            return ResponseEntity.badRequest().headers(headers).build();
        }
    }

}
