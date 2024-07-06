package dajava.dacs.controller;

import dajava.dacs.model.Student;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import dajava.dacs.model.Faculty;
import dajava.dacs.model.Lecturer;
import dajava.dacs.service.FacultyService;
import dajava.dacs.service.LecturerService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/lecturers")
public class LecturerController {

    @Autowired
    private LecturerService lecturerService;
    @Autowired
    private FacultyService facultyService;

    @GetMapping
    public String getAllLecturers(Model model) {
        List<Lecturer> lecturers = lecturerService.getAllLecturers();
        model.addAttribute("lecturers", lecturers);
        return "lecturer/list";
    }

    @GetMapping("/add")
    public String showAddLecturerForm(Model model) {
        model.addAttribute("lecturer", new Lecturer());
        List<Faculty> faculties = facultyService.getAllFaculties();
        model.addAttribute("faculties", faculties);
        return "lecturer/add";
    }

    @PostMapping("/add")
    public String createLecturer(@ModelAttribute("lecturer") Lecturer lecturer, @RequestParam("facultyId") String facultyId) {
        Optional<Faculty> faculty = facultyService.getFacultyById(facultyId);
        faculty.ifPresent(lecturer::setFaculty);
        lecturerService.saveLecturer(lecturer);
        return "redirect:/lecturers";
    }

    @GetMapping("/edit/{id}")
    public String showEditLecturerForm(@PathVariable String id, Model model) {
        Optional<Lecturer> lecturer =  lecturerService.getLecturerById(id);
        List<Faculty> faculties = facultyService.getAllFaculties();
        model.addAttribute("lecturer", lecturer.get());
        model.addAttribute("faculties", facultyService.getAllFaculties());
        return "lecturer/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateLecturer(@PathVariable("id") String id, @Valid @ModelAttribute("lecturer") Lecturer lecturer, BindingResult result) {
        Optional<Lecturer> optionalLecturer = lecturerService.getLecturerById(id);
        if ( optionalLecturer.isPresent()) {
            Lecturer existingLecturer =  optionalLecturer.get();
            existingLecturer.setLecturerName(lecturer.getLecturerName());
            lecturerService.saveLecturer(existingLecturer);
            return "redirect/lecturers/list";
        } else {
            return "redirect/lecturers/list";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteLecturer(@PathVariable String id) {
        lecturerService.deleteLecturer(id);
        return "redirect:/lecturers";
    }
}

