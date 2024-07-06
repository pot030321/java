package dajava.dacs.controller;

import dajava.dacs.model.Faculty;
import dajava.dacs.model.Student;
import dajava.dacs.service.FacultyService;
import dajava.dacs.service.StudentService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private FacultyService facultyService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String getAllLecturers(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "student/list";

    }
    @GetMapping("/add")
    public String showAddStudentForm(Model model) {
        model.addAttribute("student", new Student());
        List<Faculty> faculties = facultyService.getAllFaculties();
        model.addAttribute("faculties", faculties);
        return "student/add";
    }

    @PostMapping("/add")
    public String addStudent(@ModelAttribute Student student, @RequestParam("facultyId") String facultyId) {
        Optional<Faculty> facultyOptional = facultyService.getFacultyById(facultyId);
        if (facultyOptional.isPresent()) {
            student.setFaculty(facultyOptional.get());
            studentService.saveStudent(student);
            return "redirect:/students";
        } else {
            return "redirect:/students/add?error=InvalidFacultyID";
        }
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        Optional<Student> student = studentService.getStudentById(id);
        model.addAttribute("student", student.get());
        model.addAttribute("faculties", facultyService.getAllFaculties());
        return "student/edit";
    }

    // Endpoint to handle form submission and update a student
    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable("id") String id, @Valid @ModelAttribute("student") Student student, BindingResult result) {
        Optional<Student> optionalStudent = studentService.getStudentById(id);
        if ( optionalStudent.isPresent()) {
            Student existingStudent =  optionalStudent.get();
            existingStudent.setStudentName(student.getStudentName());
            studentService.saveStudent(existingStudent);
            return "redirect/students/list";
        } else {
            return "redirect/students/list";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") String id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
    @GetMapping("/excel")
    public void generateExcel(HttpServletResponse response) {
        try {
            studentService.generateExcel(response);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

}
