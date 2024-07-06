package dajava.dacs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import dajava.dacs.model.Department;
import dajava.dacs.service.DepartmentService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String getAllDepartments(Model model) {
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "department/list";
    }

    @GetMapping("/add")
    public String showAddDepartmentForm(Model model) {
        model.addAttribute("department", new Department());
        return "department/add";
    }

    @PostMapping("/add")
    public String createDepartment(@ModelAttribute("department") Department department) {
        departmentService.saveDepartment(department);
        return "redirect:/departments";
    }

    @GetMapping("/edit/{id}")
    public String showEditDepartmentForm(@PathVariable String id, Model model) {
        Optional<Department> optionalDepartment = departmentService.getDepartmentById(id);
        if (optionalDepartment.isPresent()) {
            model.addAttribute("department", optionalDepartment.get());
            return "department/edit";
        } else {
            return "redirect:/departments";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateDepartment(@PathVariable String id, @ModelAttribute Department department) {
        Optional<Department> optionalDepartment = departmentService.getDepartmentById(id);
        if (optionalDepartment.isPresent()) {
            Department existingDepartment = optionalDepartment.get();
            existingDepartment.setDepartmentName(department.getDepartmentName());
            existingDepartment.setFloor(department.getFloor());
            existingDepartment.setListOfClass(department.getListOfClass());
            departmentService.saveDepartment(existingDepartment);
            return "redirect:/departments";
        } else {
            return "redirect:/departments";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable String id) {
        departmentService.deleteDepartment(id);
        return "redirect:/departments";
    }
}

