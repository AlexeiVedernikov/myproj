package com.my3o.backend.service;

import com.my3o.common.dto.AuthenticationResponse;

/**
 * Created by: Alexander Duckardt
 * Date: 8/3/14.
 */
public interface IAuthenticationService {
    public AuthenticationResponse login(String principal, String password);
    public void logout(String userId);
    public AuthenticationResponse renewToken(String principal, String token, String usId);
}
