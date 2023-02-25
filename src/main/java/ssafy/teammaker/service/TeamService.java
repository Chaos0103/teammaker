package ssafy.teammaker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.teammaker.domain.Student;
import ssafy.teammaker.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final StudentRepository studentRepository;

    public List<List<Student>> makeTeam(int searchType, int count, List<Long> exclusionIds) {
        if (count == 0) {
            return new ArrayList<>();
        }

        List<Student> findStudents = studentRepository.findStudents(exclusionIds);

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
        Random random = new Random();

        List<List<Student>> res = new ArrayList<>();
        for (int i = 0; i < students.size()+1 / count; i++) {
            res.add(new ArrayList<>());
        }

        boolean[] picked = new boolean[students.size()];

        int teamIndex = 0;
        int memberCount = 0;
        while (memberCount < students.size()) {
            int studentIndex = random.nextInt(students.size());
            if (picked[studentIndex]) {
                continue;
            }
            res.get(teamIndex).add(students.get(studentIndex));
            memberCount++;
            if (memberCount % count == 0) {
                teamIndex++;
            }
            picked[studentIndex] = true;
        }

        return res;
    }
}
