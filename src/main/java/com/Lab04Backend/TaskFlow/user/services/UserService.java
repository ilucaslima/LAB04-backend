package com.Lab04Backend.TaskFlow.user.services;

import com.Lab04Backend.TaskFlow.member.entity.Members;
import com.Lab04Backend.TaskFlow.member.repository.MemberRepository;
import com.Lab04Backend.TaskFlow.user.dtos.RegisterUserDTO;
import com.Lab04Backend.TaskFlow.user.dtos.UpdateUserDTO;
import com.Lab04Backend.TaskFlow.user.entity.User;
import com.Lab04Backend.TaskFlow.user.exceptions.EmailAlreadyExistsException;
import com.Lab04Backend.TaskFlow.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final MemberRepository memberRepository;

    private final PasswordEncoder encoder;

    public User register(RegisterUserDTO dto) {

        if (repository.existsByEmail(dto.email())) {
            throw new EmailAlreadyExistsException(dto.email());
        }

        User user = User.builder()
                .name(dto.name())
                .email(dto.email())
                .password(encoder.encode(dto.password()))
                .avatar(dto.avatar())
                .birthday(dto.birthday())
                .build();

        Members member = Members.builder()
                .user(user)
                .build();

        user = repository.save(user);
        memberRepository.save(member);
        return user;
    }

    public User update(
            Authentication authentication,
            UpdateUserDTO dto
    ){

        User user = getAuthenticatedUser(authentication);


        if(dto.name() != null){
            user.setName(dto.name());
        }

        if(dto.avatar() != null){
            user.setAvatar(dto.avatar());
        }

        if(dto.birthday() != null){
            user.setBirthday(dto.birthday());
        }


        return repository.save(user);
    }

    public void delete(Authentication authentication){

        User user = getAuthenticatedUser(authentication);

        user.setDeletedAt(LocalDateTime.now());

        repository.save(user);
    }

    public User getAuthenticatedUser(Authentication authentication){

        String email = authentication.getName();

        return repository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );
    }
}
