package com.zhurylo.testtaskkameleoon.service;

import com.zhurylo.testtaskkameleoon.dto.UserDto;
import com.zhurylo.testtaskkameleoon.entity.User;
import com.zhurylo.testtaskkameleoon.exception.EmailAlreadyExistsException;
import com.zhurylo.testtaskkameleoon.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class UserService {

    @NonNull
    private final UserRepository repository;

    @Transactional
    public void createUser(UserDto dto) throws EmailAlreadyExistsException {
        if (repository.findByEmail(dto.email()).isPresent()) {
            throw new EmailAlreadyExistsException("Email not found");
        }
        User newUser = User.builder()
                .name(dto.name())
                .email(dto.email())
                .dateOfCreation(LocalDateTime.now())
                .build();
        repository.save(newUser);
    }
}