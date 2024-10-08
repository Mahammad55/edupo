
package az.gigroup.edupo.repository;

import az.gigroup.edupo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsCourseByCourseName(String courseName);

    boolean existsCourseByCourseNameAndIdNot(String courseName, Long id);
}