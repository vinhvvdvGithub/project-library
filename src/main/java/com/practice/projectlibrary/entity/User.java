package com.practice.projectlibrary.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.lang.Nullable;



import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {


    @Column(name = "username", columnDefinition = "nvarchar(50) not null")
    @NotNull
    private String username;

    @Column(name = "password",columnDefinition = "nvarchar(500) not null")
    @NotNull
    private String password;

    @NonNull
    @Column(name = "email", unique = true)
    @Email
    private String email;

    @Nullable
    private String avatar;
    //    @NotNull
    @Nullable
    private Boolean active;


    //loan's user
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Loan> listLoanBookOfUser;

    //role relationship
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();




}
