package az.gigroup.edupo.entity;

import az.gigroup.edupo.enums.GenderType;
import az.gigroup.edupo.enums.Stages;
import az.gigroup.edupo.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static az.gigroup.edupo.enums.Active.ACTIVE;

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

    private String mobileNumber;

    private String email;

    @Enumerated(EnumType.STRING)
    private GenderType gender;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Stages stages;

    @ManyToOne
    private Course course;

    private Integer active;

    @PrePersist
    public void setActiveStatusOnSave() {
        active = ACTIVE.value;
    }
}

