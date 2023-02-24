package ssafy.teammaker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import ssafy.teammaker.domain.Student;
import ssafy.teammaker.service.StudentService;
import ssafy.teammaker.service.TeamService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TeamController {

    private final TeamService service;
    private final StudentService studentService;

    @ModelAttribute("students")
    public List<Student> studentNames() {
        return studentService.findStudents();
    }

    @GetMapping("/maker")
    public String teamMaker(
            @RequestParam(defaultValue = "1") Integer searchType,
            @RequestParam(defaultValue = "0") Integer count,
            Model model) {
        log.debug("StudentController#indexPage");
        List<List<Student>> teams = service.makeTeam(searchType, count, new ArrayList<>());
        model.addAttribute("teams", teams);
        return "team-maker";
    }
}
