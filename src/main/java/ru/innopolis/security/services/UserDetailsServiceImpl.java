package ru.innopolis.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.pojo.UsersEntity;
import ru.innopolis.repositories.UserRepository;

/**
 * @author KhafizovR by 20.02.2020
 * tickets-service-server
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsersEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Не найден пользователь: " + email));

        return UserDetailsImpl.build(user);
    }

}