package com.pccw.backend.modules.user;

import com.pccw.backend.exceptions.ErrorDefinitions;
import com.pccw.backend.exceptions.PccwException;
import com.pccw.backend.persistence.Page;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    @Transactional
    @CacheEvict(value = "users", allEntries = true)
    public void save(User user) {
        if (userMapper.usernameExists(user.getUsername()))
            throw PccwException.of(ErrorDefinitions.DUPLICATE_USERNAME);
        if (userMapper.emailExists(user.getEmail()))
            throw PccwException.of(ErrorDefinitions.DUPLICATE_EMAIL);
        userMapper.insert(user);
    }

    @Transactional
    @CacheEvict(value = "users", allEntries = true)
    public void update(UpdateUserInput user, Long userId) {
        userMapper.update(user, userId);
    }

    @Cacheable("users")
    public Collection<User> findByCriteria(UserCriteria userCriteria) {
        return userMapper.findByCriteria(userCriteria);
    }

    @Cacheable(value = "users", key = "#userId")
    public User findById(Long userId) {
        return userMapper.findById(userId);
    }

    @Transactional
    @CacheEvict(value = "users", allEntries = true)
    public void delete(Long userId) {
        userMapper.delete(userId);
    }

    @Cacheable("users")
    public Page<User> findPageByCriteria(@Valid UserCriteria userCriteria) {
        Collection<User> collection = userMapper.findByCriteria(userCriteria);
        Long count = userMapper.countByCriteria(userCriteria);
        return new Page<>(collection, userCriteria.getPageRequest().getPage(), count);
    }

}
