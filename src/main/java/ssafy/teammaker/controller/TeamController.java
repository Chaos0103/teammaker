package ssafy.teammaker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ssafy.teammaker.domain.Student;
import ssafy.teammaker.service.StudentService;
import ssafy.teammaker.service.TeamService;

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
    public String teamMaker(@ModelAttribute("form") TeamMakerForm form, Model model) {
        log.debug("StudentController#indexPage");
        log.debug("form={}", form);
        List<List<Student>> teams = service.makeTeam(form.getSearchType(), form.getCount(), form.getExclusionsIds());
        model.addAttribute("teams", teams);
        return "team-maker";
    }
}
