package ssafy.teammaker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ssafy.teammaker.domain.Student;
import ssafy.teammaker.service.SelectService;
import ssafy.teammaker.service.StudentService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SelectController {

    private final StudentService studentService;
    private final SelectService selectService;

    @ModelAttribute("students")
    public List<Student> studentNames() {
        return studentService.findStudents();
    }

    @GetMapping("/lottery")
    public String selectStudent(@ModelAttribute("form") SelectForm form, Model model) {
        log.debug("form={}", form);

        if (form.isFirst()) {
            form.setFirst(false);
            model.addAttribute("student", null);
            return "select";
        }

        Student findStudent = selectService.findRandomOneStudent(form.getExclusionIds(), form.getHidden());
        form.setHidden(form.getHidden() + findStudent.getId() + ",");
        model.addAttribute("student", findStudent);
        return "select";
    }
}
