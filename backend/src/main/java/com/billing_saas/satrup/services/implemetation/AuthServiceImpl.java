package com.billing_saas.satrup.services.implemetation;

import com.billing_saas.satrup.dtos.LoginRequest;
import com.billing_saas.satrup.dtos.LoginResponse;
import com.billing_saas.satrup.entities.User;
import com.billing_saas.satrup.exceptions.BadRequestException;
import com.billing_saas.satrup.repositories.UserRepo;
import com.billing_saas.satrup.security.JwtProvider;
import com.billing_saas.satrup.services.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepository;
    private final JwtProvider jwtProvider;

    public AuthServiceImpl(UserRepo userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByTenant_TenantIdAndPhone(Long.parseLong(request.getTenantId()), request.getPhone())
                .orElseThrow(() -> new BadRequestException("Invalid phone number or tenant ID"));

        if (!user.getIsActive()) {
            throw new BadRequestException("User account is inactive");
        }

        String token = jwtProvider.generateToken(
                user.getUserId().toString(),
                user.getTenant().getTenantId().toString(),
                user.getRole()
        );

        return new LoginResponse(
                token,
                user.getUserId(),
                user.getTenant().getTenantId(),
                user.getName(),
                user.getPhone(),
                user.getRole()
        );
    }
}
