package dajava.dacs.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.*;

@Data
@Entity
@Table( name ="User")
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username",length = 50, unique = true)
    @NotBlank(message = "Username is required")
    @Size(min = 1 , max = 50, message = "Username must be between 1 and 50 characters")
    private String username;

    @Column(name = "password", length = 250)
    @NotBlank(message = "Password is required")
    private String password;

    @Column(name = "email", length = 50, unique = true)
    @NotBlank(message = "Email is required")
    @Size(min = 1 , max = 50, message = "Email must be between 1 and 50 characters")
    @Email
    private String email;

    @Column(name = "name",length = 50,nullable = false)
    @Size(max = 50,message = "Tên của bạn phải ít hơn 50 ký tự")
    @NotBlank(message = "Tên của bạn không được để trống")
    private  String name;

    @Column(name = "phone", length = 10, unique = true)
    @Length(min = 10, max = 10, message = "Phone must be 10 characters")
    @Pattern(regexp = "^[0-9]*$",message = "Phone must be number")
    private String phone;

    @NotBlank(message =" Địa chỉ không được để trống")
    @Column(name = "address",length = 100, unique = true)
    @Size(max = 100, message = "Địa chỉ không được quá 100 ký tự")
    private String address;

    @Column(name = "date_birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datebirth;

    @Transient
    @Size(min = 6, message = "Password không được nhỏ hơn 6 ký tự")
    private String confirmPassword;


    @Column(name = "reset_password_token")
    private String resetPasswordToken;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;



}
