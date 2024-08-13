package az.gigroup.edupo.controller;

import az.gigroup.edupo.dto.request.CourseRequest;
import az.gigroup.edupo.dto.response.CourseResponse;
import az.gigroup.edupo.service.CourseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/courses")
@Tag(name = "Course Controller")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(@Validated @RequestBody CourseRequest courseRequest) {
        return ResponseEntity.status(CREATED).body(courseService.createCourse(courseRequest));
    }

    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAllCourse() {
        return ResponseEntity.ok(courseService.getAllCourse());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> updateCourse(@PathVariable Long id, @Validated @RequestBody CourseRequest courseRequest) {
        return ResponseEntity.ok(courseService.updateCourse(id, courseRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
