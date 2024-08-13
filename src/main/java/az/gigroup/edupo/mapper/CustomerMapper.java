package az.gigroup.edupo.mapper;

import az.gigroup.edupo.dto.request.CustomerRequest;
import az.gigroup.edupo.dto.response.CustomerResponse;
import az.gigroup.edupo.entity.Customer;
import az.gigroup.edupo.enums.NextStep;
import az.gigroup.edupo.enums.Stage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import static az.gigroup.edupo.enums.NextStep.FOLLOW_UP;
import static az.gigroup.edupo.enums.NextStep.RETARGET_LATER;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer requestToEntity(CustomerRequest customerRequest);

    @Mapping(target = "price", source = "course.price")
    @Mapping(target = "nextStep", source = "stage", qualifiedByName = "setNextStep")
    @Mapping(target = "probability", source = "stage", qualifiedByName = "setProbability")
    @Mapping(target = "course", source = "course.courseName")
    CustomerResponse entityToResponse(Customer customer);

    @Named("setProbability")
    default long setProbability(Stage stage) {
        return switch (stage) {
            case LOST -> 0;
            case LEAD -> 10;
            case CONTACTED -> 30;
            case QUALIFIED -> 50;
            case POSTPONED -> 20;
            case WON -> 100;
        };
    }

    @Named("setNextStep")
    default NextStep setNextStep(Stage stage) {
        return switch (stage) {
            case LEAD, CONTACTED, QUALIFIED -> FOLLOW_UP;
            case POSTPONED -> RETARGET_LATER;
            default -> null;
        };
    }
}
