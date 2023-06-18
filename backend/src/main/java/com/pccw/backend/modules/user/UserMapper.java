package com.pccw.backend.modules.user;

import org.apache.ibatis.annotations.Param;

import java.util.Collection;

public interface UserMapper {

    long insert(@Param("entity") User user);

    long update(@Param("entity") UpdateUserInput user, @Param("userId") Long userId);

    Collection<User> findByCriteria(@Param("criteria") UserCriteria criteria);

    Long countByCriteria(@Param("criteria") UserCriteria criteria);

    User findById(@Param("userId") Long userId);

    void delete(Long userId);

    boolean usernameExists(@Param("username") String username);

    boolean emailExists(@Param("email") String email);
}
