package fr.fms.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5, max = 30)
    private String name;

    @NotNull
    @Size(min = 5, max = 30)
    private String sname;

    @NotNull
    @Size(min = 5, max = 30)
    private String email;

    @NotNull
    @Size(min = 5, max = 30)
    private String adresse;

    @NotNull
    @Size(min = 10, max = 30)
    private String phone;

    @OneToMany(mappedBy = "customer")
    Collection<Order> orders;

}
