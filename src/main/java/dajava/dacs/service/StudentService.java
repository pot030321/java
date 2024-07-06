package dajava.dacs.service;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import dajava.dacs.model.Student;
import dajava.dacs.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(String id) {
        return studentRepository.findById(id);
    }

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }

    public void generateExcel(HttpServletResponse response) throws IOException {
        List<Student> students = studentRepository.findAll();

        // Tạo workbook và sheet Excel
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Students");

        // Tạo header row
        HSSFRow headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Student ID");
        headerRow.createCell(1).setCellValue("Student Name");
        headerRow.createCell(2).setCellValue("Date of Birth");
        headerRow.createCell(3).setCellValue("Phone Number");
        headerRow.createCell(4).setCellValue("Gmail");
        headerRow.createCell(5).setCellValue("Faculty");

        // Điền dữ liệu từ danh sách sinh viên vào sheet
        int rowNum = 1;
        for (Student student : students) {
            HSSFRow row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(student.getStudentID());
            row.createCell(1).setCellValue(student.getStudentName());
            row.createCell(2).setCellValue(student.getDateOfBirth().toString()); // Chuyển đổi LocalDate thành String
            row.createCell(3).setCellValue(student.getPhoneNumber());
            row.createCell(4).setCellValue(student.getGmail());
            // Nếu có quan hệ với Faculty, có thể thêm dòng sau
            // row.createCell(5).setCellValue(student.getFaculty().getFacultyName());
        }

        // Thiết lập các header cho response
        response.setHeader("Content-Disposition", "attachment; filename=students.xls");
        response.setContentType("application/vnd.ms-excel");

        // Ghi xuống OutputStream của response
        workbook.write(response.getOutputStream());

        // Đóng workbook
        workbook.close();
    }


}

