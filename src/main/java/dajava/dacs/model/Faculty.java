package dajava.dacs.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Entity
@Table(name = "Faculty")
public class Faculty {

    @Id
    @NotBlank(message = "FacultyID không được để trống")
    @Size(max = 20)
    @Column(name = "faculty_id", length = 20, nullable = false, unique = true)
    private String facultyID;

    @NotBlank(message = "Tên khoa không được để trống")
    @Column(name = "faculty_name", length = 250, nullable = false)
    private String facultyName;

    public @NotBlank(message = "FacultyID không được để trống") @Size(max = 20) String getFacultyID() {
        return facultyID;
    }

    public void setFacultyID(@NotBlank(message = "FacultyID không được để trống") @Size(max = 20) String facultyID) {
        this.facultyID = facultyID;
    }

    public @NotBlank(message = "Tên khoa không được để trống") String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(@NotBlank(message = "Tên khoa không được để trống") String facultyName) {
        this.facultyName = facultyName;
    }

    @Getter
    @Setter
    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL)
    private List<Student> students;

    @Setter
    @Getter
    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL)
    private List<Lecturer> lecturers;
}
