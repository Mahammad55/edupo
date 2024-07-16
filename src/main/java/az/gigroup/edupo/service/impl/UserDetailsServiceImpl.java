package az.gigroup.edupo.service.impl;

import az.gigroup.edupo.entity.User;
import az.gigroup.edupo.exception.UserNotFoundException;
import az.gigroup.edupo.repository.UserRepository;
import az.gigroup.edupo.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username)
                .orElseThrow(() -> {
                            log.error("UserDetailsService.loadUserByUsername.error -- user not found email:{}", username);
                            return new UserNotFoundException(username);
                        }
                );

        return new MyUserDetails(user);
    }
}
