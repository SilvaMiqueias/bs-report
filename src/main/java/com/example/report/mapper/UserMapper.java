package com.example.report.mapper;

import com.example.report.dto.UserDTO;
import com.example.report.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(User user);

    User  userDTOToUser(UserDTO userDTO);
}
