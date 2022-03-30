package edu.school21.cinema.controller;

import edu.school21.cinema.model.Session;
import edu.school21.cinema.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/sessions")
public class SessionJQueryController {

    @Autowired
    private SessionService sessionService;

    @GetMapping
    public String get() {
        return "SessionJQuery";
    }

    @ResponseBody
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Session> get(@RequestParam(required = false, value = "filmName") String filmName) {
        System.out.println("GET");
        return sessionService.showMovies(filmName);
    }

    @GetMapping("/{id}")
    public String showSession(@PathVariable Long id,
                              Model model) {

        List<Session> sessions = new ArrayList<>();

        sessionService.getSession(id).ifPresent(sessions::add);

        sessions.forEach(System.out::println);

        model.addAttribute("sessions", sessions);
        return "SessionNew";
    }

}



