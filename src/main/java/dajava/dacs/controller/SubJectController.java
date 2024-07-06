package dajava.dacs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import dajava.dacs.model.SubJect;
import dajava.dacs.service.SubjectService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/subjects")
public class SubJectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public String getAllSubjects(Model model) {
        List<SubJect> subjects = subjectService.getAllSubjects();
        model.addAttribute("subjects", subjects);
        return "subject/list";
    }

    @GetMapping("/add")
    public String showAddSubjectForm(Model model) {
        model.addAttribute("subject", new SubJect());
        return "subject/add";
    }

    @PostMapping("/add")
    public String createSubject(@ModelAttribute("subject") SubJect subject) {
        subjectService.saveSubject(subject);
        return "redirect:/subjects";
    }

    @GetMapping("/edit/{id}")
    public String showEditSubjectForm(@PathVariable String id, Model model) {
        Optional<SubJect> optionalSubject = subjectService.getSubjectById(id);
        if (optionalSubject.isPresent()) {
            model.addAttribute("subject", optionalSubject.get());
            return "subject/edit";
        } else {
            return "redirect:/subjects";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateSubject(@PathVariable String id, @ModelAttribute SubJect subject) {
        Optional<SubJect> optionalSubject = subjectService.getSubjectById(id);
        if (optionalSubject.isPresent()) {
            SubJect existingSubject = optionalSubject.get();
            existingSubject.setSubJectName(subject.getSubJectName());
            existingSubject.setNumberOfCredits(subject.getNumberOfCredits());
            subjectService.saveSubject(existingSubject);
            return "redirect:/subjects";
        } else {
            return "redirect:/subjects";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteSubject(@PathVariable String id) {
        subjectService.deleteSubject(id);
        return "redirect:/subjects";
    }
}

