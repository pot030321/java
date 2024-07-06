package dajava.dacs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import dajava.dacs.service.PersonalUserService;

@Controller
@RequestMapping("/personalusers")
public class PersonalUserNoneApiController {
    @Autowired
    private PersonalUserService personalUserService;

    @GetMapping()
    public String getAllPersonalUsers(Model model) {
        model.addAttribute("personalUsers", personalUserService.getAllEvents());
        return "personaluser/ManagerTaskOfSefl";
    }
}
