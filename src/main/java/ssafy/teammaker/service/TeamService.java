package ssafy.teammaker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.teammaker.domain.Student;
import ssafy.teammaker.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final StudentRepository repository;

    public List<List<Student>> makeTeam(int teamCount, List<Long> exclusions) {
        Random random = new Random();

        List<List<Student>> res = new ArrayList<>();
        for (int i = 0; i < teamCount; i++) {
            res.add(new ArrayList<>());
        }

        List<Student> findStudents = this.findStudents(exclusions);
        boolean[] picked = new boolean[findStudents.size()];

        int teamIndex = 0;
        while (teamIndex < findStudents.size()) {
            int studentIndex = random.nextInt(findStudents.size());
            if (picked[studentIndex]) {
                continue;
            }
            res.get(teamIndex++ % teamCount).add(findStudents.get(studentIndex));
            picked[studentIndex] = true;
        }

        return res;
    }

    private List<Student> findStudents(List<Long> exclusions) {
        return repository.findAll().stream()
                .filter(student -> !exclusions.contains(student.getId()))
                .collect(Collectors.toList());
    }
}
