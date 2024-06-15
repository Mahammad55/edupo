package az.gigroup.edupo.controller;

import az.gigroup.edupo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
