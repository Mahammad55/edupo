package az.gigroup.edupo.mapper;

import az.gigroup.edupo.dto.response.UserDto;
import az.gigroup.edupo.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    User mapToEntity(UserDto userDto);
    UserDto mapToDto(User user);
}
