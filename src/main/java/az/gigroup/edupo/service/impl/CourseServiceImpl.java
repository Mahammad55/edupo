package az.gigroup.edupo.service.impl;

import az.gigroup.edupo.dto.request.CourseRequest;
import az.gigroup.edupo.dto.response.CourseResponse;
import az.gigroup.edupo.entity.Course;
import az.gigroup.edupo.exception.AlreadyExistsException;
import az.gigroup.edupo.exception.NotFoundException;
import az.gigroup.edupo.mapper.CourseMapper;
import az.gigroup.edupo.repository.CourseRepository;
import az.gigroup.edupo.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    private final CourseMapper courseMapper;

    @Override
    public CourseResponse createCourse(CourseRequest courseRequest) {
        if (courseRepository.existsCourseByCourseName(courseRequest.getCourseName())) {
            throw new AlreadyExistsException("Course by name=%s already exist".formatted(courseRequest.getCourseName()));
        }
        return courseMapper.entityToResponse(courseRepository.save(courseMapper.requestToEntity(courseRequest)));
    }

    @Override
    public List<CourseResponse> getAllCourse() {
        return courseRepository.findAll().stream()
                .map(courseMapper::entityToResponse)
                .toList();
    }

    @Override
    public CourseResponse getCourseById(Long id) {
        return courseRepository.findById(id)
                .map(courseMapper::entityToResponse)
                .orElseThrow(() -> new NotFoundException("Course by id=%d not found".formatted(id)));
    }

    @Override
    public CourseResponse updateCourse(Long id, CourseRequest courseRequest) {
        courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course by id=%d not found".formatted(id)));

        if (courseRepository.existsCourseByCourseNameAndIdNot(courseRequest.getCourseName(), id)) {
            throw new AlreadyExistsException("Course by name=%s already exist".formatted(courseRequest.getCourseName()));
        }

        Course course = courseMapper.requestToEntity(courseRequest);
        course.setId(id);
        return courseMapper.entityToResponse(courseRepository.save(course));
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course by id=%d not found".formatted(id)));
        courseRepository.deleteById(id);
    }
}
