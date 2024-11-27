package com.demo.task.mapper;

import org.mapstruct.Mapper;

import com.demo.task.Dto.UserDto;
import com.demo.task.Entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper{
	
	
	UserDto toUserDto(User user);
	User toUser(UserDto userDto);

}
