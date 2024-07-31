package az.gigroup.edupo.mapper;

import az.gigroup.edupo.dto.response.UserResponse;
import az.gigroup.edupo.entity.Authority;
import az.gigroup.edupo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User requestToEntity(UserResponse userResponse);

    @Mapping(target = "jobPosition", source = "authorities", qualifiedByName = "setJobPosition")
    UserResponse entityToResponse(User user);

    @Named("setJobPosition")
    default String setJobPosition(Set<Authority> authorities) {
        return authorities
                .stream()
                .map(Authority::authority)
                .collect(Collectors.joining(","));
    }
}
