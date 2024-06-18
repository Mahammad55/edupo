package az.gigroup.edupo.entity;

import az.gigroup.edupo.enums.GenderType;
import az.gigroup.edupo.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    private GenderType genderType;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Course> courseList;

    private int probability;
    private String nextStep;
    private String admin;

    private double price;
}

