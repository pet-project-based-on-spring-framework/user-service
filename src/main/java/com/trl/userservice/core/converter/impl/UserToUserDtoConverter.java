package com.trl.userservice.core.converter.impl;

import com.trl.userservice.core.converter.TypeConverter;
import com.trl.userservice.core.dto.UserDto;
import com.trl.userservice.core.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDtoConverter implements TypeConverter<User, UserDto> {

    private final ModelMapper modelMapper;

    public UserToUserDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Class<User> getSourceClass() {
        return User.class;
    }

    @Override
    public Class<UserDto> getTargetClass() {
        return UserDto.class;
    }

    @Override
    public UserDto convert(User entity) {
        return modelMapper.map(entity, UserDto.class);
    }

}

