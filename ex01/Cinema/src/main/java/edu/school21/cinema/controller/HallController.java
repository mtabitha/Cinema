package edu.school21.cinema.controller;


import edu.school21.cinema.model.Hall;
import edu.school21.cinema.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/admin/panel/halls")
public class HallController {

    @Autowired
    private HallService hallService;

    @GetMapping
    String getHalls(Model model) {
        model.addAttribute("halls", hallService.getHalls());
        return "Halls";
    }

    @PostMapping
    String newHall(@RequestParam(value = "size", required = false) Integer size) {
        if (size != null) {
            hallService.addNew(new Hall(size));
        }
        return "redirect:/admin/panel/halls";
    }

}
