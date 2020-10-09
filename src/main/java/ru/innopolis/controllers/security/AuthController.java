package ru.innopolis.controllers.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.dto.CityDto;
import ru.innopolis.dto.UserDto;
import ru.innopolis.dto.security.JwtResponceDto;
import ru.innopolis.dto.security.LoginRequestDto;
import ru.innopolis.dto.security.MessageResponceDto;
import ru.innopolis.dto.security.SignupRequestDto;
import ru.innopolis.pojo.RoleEntity;
import ru.innopolis.pojo.UsersEntity;
import ru.innopolis.repositories.CityRepository;
import ru.innopolis.repositories.RoleRepository;
import ru.innopolis.repositories.UserRepository;
import ru.innopolis.security.jwt.JwtUtils;
import ru.innopolis.security.services.UserDetailsImpl;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author KhafizovR by 22.02.2020
 * tickets-service-server
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequest) {


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        UsersEntity ue = userRepository.getUsersEntityById(userDetails.getId());
        UserDto userDto  = UserDto.from(ue);
        if (ue.getCity() != null)
            userDto.setCity(CityDto.from(ue.getCity()));

        return ResponseEntity.ok(new JwtResponceDto(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles, userDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequestDto signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponceDto("Ошибка: email занят!"));
        }

        UsersEntity user = new UsersEntity();
        user.setEmail(signUpRequest.getEmail());
        String[] userNames = signUpRequest.getUsername().split(" ", 3);
        if(userNames.length == 0 || userNames[0].isEmpty()) {
            ResponseEntity.ok(new MessageResponceDto("Не указано имя пользователя!"));
        }
        user.setFirstName(userNames[0]);
        user.setMiddleName((userNames.length > 1 && !userNames[1].isEmpty()) ?  userNames[1]: "");
        user.setLastName((userNames.length > 2 && !userNames[2].isEmpty()) ? userNames[2]: "");
        user.setPassword(encoder.encode(signUpRequest.getPassword()));

        Set<RoleEntity> roles = new HashSet<>();
        RoleEntity userRole = roleRepository.getDefaultRole()
                .orElseThrow(() -> new RuntimeException("Ошибка: Не удалось получить роль по умолчанию."));
        roles.add(userRole);
        user.setRoles(roles);

        if(signUpRequest.getCity().getCity() != null)
            user.setCity(cityRepository.getCityEntityByCity(signUpRequest.getCity().getCity()));

        if(signUpRequest.getPhone()!= null)
            user.setPhone(signUpRequest.getPhone());

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponceDto("Пользователь успешно зарегистрирован!"));
    }
}
