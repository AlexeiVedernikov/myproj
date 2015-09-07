package com.my3o.backend.dao;

import com.my3o.backend.domain.AuthStateEntity;

/**
 * Created by: Alexander Duckardt
 * Date: 8/3/14.
 */
public interface IAuthStateDao {
    public AuthStateEntity findById(String id);
    public void saveAuthState(final AuthStateEntity authState);
    public void deleteByUser(String userId);
}
