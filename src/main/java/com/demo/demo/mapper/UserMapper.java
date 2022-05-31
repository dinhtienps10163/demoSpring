package com.demo.demo.mapper;

import com.demo.demo.dto.UserDto;
import com.demo.demo.model.Users;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {})
public interface UserMapper extends EntityMapper<UserDto, Users> {

//    @Mapping(source = "role.id", target = "roleId")
//    @Mapping(source = "function.id", target = "functionId")
    UserDto toDto(Users users);

//    @Mapping(source = "roleId", target = "role")
//    @Mapping(source = "functionId", target = "function")
    Users toEntity(UserDto userDto);

    default Users fromId(Long id) {
        if (id == null) {
            return null;
        }
        Users users = new Users();
        users.setId(id);
        return users;
    }
}
