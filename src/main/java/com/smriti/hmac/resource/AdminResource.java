package com.smriti.hmac.resource;

import com.smriti.hmac.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

/**
 * @author smriti on 20/04/20
 */
@RestController
@RequestMapping("/api")
@Api("This is admin resource")
public class AdminResource {

    private final AdminService adminService;

    public AdminResource(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/admin")
    @ApiOperation("Save admin")
    public ResponseEntity<?> createAdmin() throws NoSuchAlgorithmException {
        System.out.println(":::: ADMIN RESOURCE EXECUTED :::");
//        adminService.save();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/test")
    @ApiOperation("Test")
    public String testHMAC() throws NoSuchAlgorithmException {
        return "HMAC Test done successfully";
    }
}
