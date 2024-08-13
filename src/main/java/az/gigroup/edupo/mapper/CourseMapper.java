package az.gigroup.edupo.mapper;

import az.gigroup.edupo.dto.request.CourseRequest;
import az.gigroup.edupo.dto.response.CourseResponse;
import az.gigroup.edupo.entity.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    Course requestToEntity(CourseRequest courseRequest);

    CourseResponse entityToResponse(Course course);
}
