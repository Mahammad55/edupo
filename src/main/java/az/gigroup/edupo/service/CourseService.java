package az.gigroup.edupo.service;

import az.gigroup.edupo.dto.request.CourseRequest;
import az.gigroup.edupo.dto.response.CourseResponse;

import java.util.List;

public interface CourseService {
    CourseResponse createCourse(CourseRequest courseRequest);

    List<CourseResponse> getAllCourse();

    CourseResponse getCourseById(Long id);

    CourseResponse updateCourse(Long id, CourseRequest courseRequest);

    void deleteCourse(Long id);
}
