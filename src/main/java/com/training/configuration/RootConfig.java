package com.training.configuration;

import com.training.DTO.UserDTO;
import com.training.model.Good;
import com.training.model.User;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(value = {"com.training.controller",
        "com.training.DAO",
        "com.training.security",
        "com.training.validator",
        "com.training.service.impl",
        "com.training.mapper"})
public class RootConfig {

    //Model
    @Bean
    @Scope("prototype")
    public User user() {
        return new User();
    }

    @Bean
    @Scope("prototype")
    public Good good(){
        return new Good();
    }

    //DTO
    @Bean
    @Scope("prototype")
    public UserDTO userDTO() {
        return new UserDTO();
    }
}
