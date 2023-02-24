package ssafy.teammaker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ssafy.teammaker.domain.Student;
import ssafy.teammaker.service.StudentService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class StudentController {

    private final StudentService service;

    @GetMapping("/students")
    public String studentList(Model model) {
        log.debug("StudentController#studentList");
        List<Student> students = service.findStudents();
        model.addAttribute("students", students);
        return "student-list";
    }

    @GetMapping("/students/add")
    public String addStudentPage(@ModelAttribute("form") StudentForm form) {
        log.debug("StudentController#addStudentPage");
        return "student-add";
    }

    @PostMapping("/students/add")
    public String addStudent(@ModelAttribute("form") StudentForm form) {
        log.debug("StudentController#addStudent");
        log.debug("names={}", form.getNames());
        service.addStudents(form.getNames());
        return "redirect:/";
    }

    @PostMapping("/students/{id}/remove")
    public String removeStudent(@PathVariable Long id) {
        service.removeStudent(id);
        return "redirect:/students";
    }

    @PostMapping("students/clear")
    public String clear() {
        service.removeAllStudents();
        return "redirect:/students";
    }
}
