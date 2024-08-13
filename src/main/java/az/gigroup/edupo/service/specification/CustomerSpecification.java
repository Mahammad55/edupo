package az.gigroup.edupo.service.specification;

import az.gigroup.edupo.dto.criteria.CustomerSearchCriteria;
import az.gigroup.edupo.entity.Customer;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class CustomerSpecification implements Specification<Customer> {
    private CustomerSearchCriteria searchCriteria;

    @Override
    public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (searchCriteria.getName() != null)
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + searchCriteria.getName().toLowerCase() + "%"));

        if (searchCriteria.getStage() != null)
            predicates.add(criteriaBuilder.equal(root.get("stage"), searchCriteria.getStage()));

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
