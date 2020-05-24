package com.smriti.hmac.service.impl;

import com.smriti.hmac.model.Admin;
import com.smriti.hmac.repository.AdminRepository;
import com.smriti.hmac.service.AdminService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;

import static com.smriti.hmac.utils.HMACKeyGenerator.generateApiKey;
import static com.smriti.hmac.utils.HMACKeyGenerator.generateApiSecret;

/**
 * @author smriti on 20/04/20
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public void save() throws NoSuchAlgorithmException {
        Admin admin = new Admin();

        admin.setUsername("smriti");
        admin.setFullName("Smriti Mool");
        admin.setPassword(BCrypt.hashpw("admin", BCrypt.gensalt()));
        admin.setEmail("sm@g.com");
        admin.setApiKey(generateApiKey());
        admin.setApiSecret(generateApiSecret());
        adminRepository.save(admin);
    }
}
