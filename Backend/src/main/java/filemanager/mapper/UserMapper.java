package filemanager.mapper;

import filemanager.dto.UserLoginResponseDto;
import filemanager.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserLoginResponseDto entityToUserLoginResponseDto(UserEntity userEntity);

}