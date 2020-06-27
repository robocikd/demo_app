package sda.projekt.rezerwacje.infrastructure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sda.projekt.rezerwacje.infrastructure.entity.User;
import sda.projekt.rezerwacje.infrastructure.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SecurityUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User user = userRepository.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User with login: " + login + " not found");
        }
        return new org.springframework.security.core.userdetails
                .User(user.getLogin(),
                user.getPassword(),
                user.isEnable(),
                true,
                true,
                true,
                mapRoles(user)
        );
    }

    private List<GrantedAuthority> mapRoles(User user) {
        return user.getRoles().stream().
                map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }
}
