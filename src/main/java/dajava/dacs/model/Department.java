package dajava.dacs.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "Department")
public class Department {

    @Id
    @NotBlank(message = "Mã tòa không được để trống")
    @Size(max = 20)
    @Column(name = "department_id", length = 20, nullable = false, unique = true)
    private String departmentID;

    @NotBlank(message = "Tên tòa không được để trống")
    @Column(name = "department_name", length = 250, nullable = false, unique = true)
    private String departmentName;

    @NotBlank(message = "Số tầng không được để trống")
    @Column(name = "floor", length = 250, nullable = false)
    private String floor;

    @NotBlank(message = "Số lượng phòng học không được để trống")
    @Column(name = "list_of_class", length = 250, nullable = false)
    private String listOfClass;
}
