package com.demo.task.mapper;



import com.demo.task.Dto.UserDto;
import com.demo.task.Entity.User;
import org.springframework.stereotype.Component;

@Component  // Automatically registered as a Spring Bean
public class UserMappperImpl implements UserMapper {

    @Override
    public UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }

        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setName(user.getName());
        
        // Map other fields from User to UserDto as needed

        return userDto;
    }

    @Override
    public User toUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setName(userDto.getName());
        // Map other fields from UserDto to User as needed

        return user;
    }
}

