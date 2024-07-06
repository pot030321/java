package dajava.dacs.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "Lecturer")
public class Lecturer {

    @Id
    @NotBlank(message = "LecturerID không được để trống")
    @Size(max = 20)
    @Column(name = "lecturer_id", length = 20, nullable = false, unique = true)
    private String lecturerID;

    @NotBlank(message = "Tên giảng viên không được để trống")
    @Column(name = "lecturer_name", length = 250, nullable = false)
    private String lecturerName;

    @ManyToOne
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;
}

