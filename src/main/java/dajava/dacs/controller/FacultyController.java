package dajava.dacs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import dajava.dacs.model.Faculty;
import dajava.dacs.service.FacultyService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/faculties")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping
    public String getAllFaculties(Model model) {
        List<Faculty> faculties = facultyService.getAllFaculties();
        model.addAttribute("faculties", faculties);
        return "faculty/list";
    }

    @GetMapping("/add")
    public String showAddFacultyForm(Model model) {
        model.addAttribute("faculty", new Faculty());
        return "faculty/add";
    }

    @PostMapping
    public String createFaculty(@ModelAttribute("faculty") Faculty faculty) {
        facultyService.saveFaculty(faculty);
        return "redirect:/faculties";
    }

    @GetMapping("/edit/{id}")
    public String showEditFacultyForm(@PathVariable String id, Model model) {
        Optional<Faculty> optionalFaculty = facultyService.getFacultyById(id);
        if (optionalFaculty.isPresent()) {
            model.addAttribute("faculty", optionalFaculty.get());
            return "faculty/edit";
        } else {
            return "redirect:/faculties";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateFaculty(@PathVariable String id, @ModelAttribute Faculty faculty) {
        Optional<Faculty> optionalFaculty = facultyService.getFacultyById(id);
        if (optionalFaculty.isPresent()) {
            Faculty existingFaculty = optionalFaculty.get();
            existingFaculty.setFacultyName(faculty.getFacultyName());
            facultyService.saveFaculty(existingFaculty);
            return "redirect:/faculties";
        } else {
            return "redirect:/faculties";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteFaculty(@PathVariable String id) {
        facultyService.deleteFaculty(id);
        return "redirect:/faculties";
    }
}

