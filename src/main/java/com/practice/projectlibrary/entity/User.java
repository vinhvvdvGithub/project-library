package com.practice.projectlibrary.entity;


import lombok.*;
import org.springframework.lang.Nullable;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username",columnDefinition = "nvarchar(50) not null")
    @NotNull
    private String username;

    @Column(name = "password")
    @NotNull
    private String password;

    @NonNull
    @Column(name = "email",unique = true)
    @Email
    private String email;

//    @NotNull
    @Nullable
    private String avatar;
//    @NotNull
    @Nullable

    private Boolean active;


    //loan's user
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER )
    private List<Loan> listLoanBookOfUser;

    @Column(name = "created_at")
//    @Temporal(value = TemporalType.TIMESTAMP)
    private Timestamp createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at")
//    @Temporal(value = TemporalType.TIMESTAMP)
    private Timestamp updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;
//    @Enumerated(EnumType.STRING)
//    private Role role;



    //user detail for spring security

}
