package az.gigroup.edupo.service.specification;

import az.gigroup.edupo.dto.criteria.TaskCriteriaRequest;
import az.gigroup.edupo.entity.Task;
import az.gigroup.edupo.enums.TaskStatus;
import az.gigroup.edupo.enums.TaskType;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskSpecification implements Specification<Task> {

    public static Specification<Task> getTaskByCriteria(TaskCriteriaRequest taskCriteriaRequest) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (taskCriteriaRequest.getTaskType() != null) {
                predicates.add(criteriaBuilder.equal(root.get("taskType"), taskCriteriaRequest.getTaskType()));
            }

            if (taskCriteriaRequest.getCustomerName() != null && !taskCriteriaRequest.getCustomerName().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("customerName")), "%" + taskCriteriaRequest.getCustomerName().toLowerCase() + "%"));
            }

            if (taskCriteriaRequest.getStatus() != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), taskCriteriaRequest.getStatus()));
            }

            LocalDate today = LocalDate.now();
            if (taskCriteriaRequest.isLastWeek()) {
                LocalDate startOfWeek = today.minusDays(7);
                predicates.add(criteriaBuilder.between(root.get("dueDate"), startOfWeek, today));
            } else if (taskCriteriaRequest.isLast15Days()) {
                LocalDate startOfPeriod = today.minusDays(15);
                predicates.add(criteriaBuilder.between(root.get("dueDate"), startOfPeriod, today));
            } else if (taskCriteriaRequest.isLastMonth()) {
                LocalDate startOfMonth = today.minusMonths(1);
                predicates.add(criteriaBuilder.between(root.get("dueDate"), startOfMonth, today));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Override
    public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null; // Implementation is handled by getTaskByCriteria method
    }
}
