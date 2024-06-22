package az.gigroup.edupo.dto.response;

import az.gigroup.edupo.entity.Customer;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private Customer customer;

    private double totalPrice;
}

