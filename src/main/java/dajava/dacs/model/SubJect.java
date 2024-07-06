package dajava.dacs.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "SubJect")
public class SubJect {

    @Id
    @NotBlank(message = "Mã môn học không được để trống")
    @Size(max=20)
    private  String SubJectID;

    @Column(name = "SubJectName", length = 250, nullable = false, unique = true)
    @NotBlank(message = "Tên môn học không được để trống")
    private String SubJectName;

    @Column(name = "NumberOfCredits", length = 250, nullable = false, unique = false)
    @NotNull(message = "Số tín chỉ không được để trống")
    private int NumberOfCredits;
}
