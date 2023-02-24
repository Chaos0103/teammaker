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

    private final StudentRepository studentRepository;

    public List<List<Student>> makeTeam(int searchType, int count, List<Long> exclusions) {
        if (count == 0) {
            return new ArrayList<>();
        }

        List<Student> findStudents = findStudents(exclusions);

        List<List<Student>> res = new ArrayList<>();
        if (searchType == 1) {
            res = makeTeamByTeamCount(count, findStudents);
        } else if (searchType == 2) {
            res = makeTeamByMemberCount(count, findStudents);
        }

        return res;
    }

    private List<List<Student>> makeTeamByTeamCount(int count, List<Student> students) {
        Random random = new Random();

        List<List<Student>> res = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            res.add(new ArrayList<>());
        }

        boolean[] picked = new boolean[students.size()];

        int teamIndex = 0;
        while (teamIndex < students.size()) {
            int studentIndex = random.nextInt(students.size());
            if (picked[studentIndex]) {
                continue;
            }
            res.get(teamIndex++ % count).add(students.get(studentIndex));
            picked[studentIndex] = true;
        }

        return res;
    }

    private List<List<Student>> makeTeamByMemberCount(int count, List<Student> students) {
        // TODO: 2023-02-24 팀원수 예외 잡기
        Random random = new Random();

        List<List<Student>> res = new ArrayList<>();
        for (int i = 0; i < students.size()+1 / count; i++) {
            res.add(new ArrayList<>());
        }

        boolean[] picked = new boolean[students.size()];

        int teamIndex = 0;
        int memberCount = 0;
        System.out.println("students.size() = " + students.size());
        while (memberCount < students.size()) {
            int studentIndex = random.nextInt(students.size());
            if (picked[studentIndex]) {
                continue;
            }
            res.get(teamIndex).add(students.get(studentIndex));
            memberCount++;
            System.out.println("memberCount = " + memberCount);
            System.out.println("teamIndex = " + teamIndex);
            if (memberCount % count == 0) {
                teamIndex++;
            }
            picked[studentIndex] = true;
        }

        return res;
    }

    private List<Student> findStudents(List<Long> exclusions) {
        return studentRepository.findAll().stream()
                .filter(student -> !exclusions.contains(student.getId()))
                .collect(Collectors.toList());
    }
}
