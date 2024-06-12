package az.gigroup.edupo.entity;

import az.gigroup.edupo.enums.GenderType;
import az.gigroup.edupo.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Stages (lead, contacted, qualified, postponed, won, lost)

    private String mobileNumber;

    private String email;

    @Enumerated(EnumType.STRING)
    private GenderType genderType;

    @Enumerated(EnumType.STRING)
    private Status status;

    // Products (Course list)

    // Probability
    // Next step (Follow up and retarget later)
    // Admin (User name)
    // Price
}
