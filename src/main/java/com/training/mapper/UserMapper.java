package com.training.mapper;

import com.training.DTO.UserDTO;
import com.training.model.User;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class UserMapper {

    public UserDTO GetUserToDTO(User user, UserDTO dto) {
        dto.setId(user.getId());
        dto.setLogin(user.getUsername());
        return dto;
    }
}