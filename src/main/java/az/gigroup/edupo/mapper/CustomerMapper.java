package az.gigroup.edupo.mapper;

import az.gigroup.edupo.dto.request.CustomerRequest;
import az.gigroup.edupo.dto.response.CustomerResponse;
import az.gigroup.edupo.entity.Course;
import az.gigroup.edupo.entity.Customer;
import az.gigroup.edupo.enums.NextStep;
import az.gigroup.edupo.enums.Stages;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

import static az.gigroup.edupo.enums.NextStep.FOLLOW_UP;
import static az.gigroup.edupo.enums.NextStep.RETARGET_LATER;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer requestToEntity(CustomerRequest customerRequest);

    @Mapping(target = "price", source = "courses", qualifiedByName = "setPrice")
    @Mapping(target = "nextStep", source = "stages", qualifiedByName = "setNextStep")
    @Mapping(target = "probability", source = "stages", qualifiedByName = "setProbability")
    CustomerResponse entityToResponse(Customer customer);

    @Named("setProbability")
    default long setProbability(Stages stages) {
        return switch (stages) {
            case LOST -> 0;
            case LEAD -> 20;
            case CONTACTED -> 40;
            case QUALIFIED -> 65;
            case POSTPONED -> 85;
            case WON -> 100;
        };
    }

    @Named("setNextStep")
    default NextStep setNextStep(Stages stages) {
        return switch (stages) {
            case LEAD, CONTACTED, QUALIFIED -> FOLLOW_UP;
            case POSTPONED -> RETARGET_LATER;
            default -> null;
        };
    }

    @Named("setPrice")
    default double setPrice(List<Course> courses) {
        return courses.stream().mapToDouble(Course::getPrice).sum();
    }
}
