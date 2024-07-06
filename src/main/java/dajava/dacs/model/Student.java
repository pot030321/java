    package dajava.dacs.model;


    import jakarta.persistence.*;
    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.NotNull;
    import jakarta.validation.constraints.Size;
    import lombok.Data;
    import org.springframework.format.annotation.DateTimeFormat;

    import java.time.LocalDate;

    @Data
    @Entity
    @Table(name = "Student")
    public class Student {

        @Id
        @NotBlank(message = "StudentID không được để trống")
        @Size(max=20)
        @Column(name = "student_id", length = 20, nullable = false, unique = true)
        private String studentID;

        @NotBlank(message = "Tên sinh viên không được để trống")
        @Column(name = "student_name", length = 250, nullable = false)
        private String studentName;

        @DateTimeFormat(pattern = "yyyy-MM-dd") // hoặc pattern = "dd/MM/yyyy"
        @NotNull(message = "Không được bỏ trống ngày sinh")
        @Column(name = "date_of_birth", nullable = false)
        private LocalDate dateOfBirth;

        @NotNull(message = "không được bỏ trống số điện thoại")
        @Column(name = "phone_number", length = 10, nullable = false, unique = true)
        private String phoneNumber;

        @NotNull(message = "không được bỏ trống Gmail")
        @Column(name = "gmail", length = 50, nullable = false, unique = true)
        private String gmail;

        @ManyToOne
        @JoinColumn(name = "faculty_id")
        private Faculty faculty;
    }
