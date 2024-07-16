package az.gigroup.edupo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

@Entity
@Table(name = "authorities")
@Getter
@Setter
@Accessors(fluent = true, chain = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String authority;
}
