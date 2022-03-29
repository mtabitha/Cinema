package edu.school21.cinema.controller;

import edu.school21.cinema.model.Session;
import edu.school21.cinema.service.HallService;
import edu.school21.cinema.service.MovieService;
import edu.school21.cinema.service.SessionService;
import net.bytebuddy.build.Plugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

@Controller
@RequestMapping("/admin/panel/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private HallService hallService;

    @Autowired
    private MovieService movieService;

    @GetMapping
    public String getSessions(Model model) {
        model.addAttribute("sessions", sessionService.getSessions());
        model.addAttribute("halls", hallService.getHalls());
        model.addAttribute("movies", movieService.getMoves());
        return "Session";
    }

    @PostMapping
    public String newSession(@RequestParam Long movieId,
                             @RequestParam Long hallId,
                             @RequestParam @DateTimeFormat(pattern = "HH:mm") Date time,
                             @RequestParam String price) throws ParseException {


        Session session = new Session(time, Integer.parseInt(price));

        sessionService.addNew(session, hallId, movieId);

        return "redirect:/admin/panel/sessions";
    }

}
