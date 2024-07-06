package dajava.dacs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import dajava.dacs.model.PersonalUser;
import dajava.dacs.service.PersonalUserService;

import java.util.List;

@RestController
@RequestMapping("/api/personalusers")
public class PersonalUserController {

    @Autowired
    private PersonalUserService personalUserService;

    @GetMapping
    public List<PersonalUser> getAllPersonalUsers() {
        return personalUserService.getAllEvents();
    }

    @PostMapping
    public String createPersonalUser(@RequestBody PersonalUser personalUser) {
        personalUserService.saveEvent(personalUser);
        return "redirect:/personalusers";
    }
    @DeleteMapping("/{id}")
    public String deletePersonalUser(@PathVariable Long id) {
        personalUserService.deleteEvent(id);
        return "redirect:/personalusers";
    }
}
