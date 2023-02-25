package ssafy.teammaker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.teammaker.domain.Student;
import ssafy.teammaker.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final StudentRepository studentRepository;

    public List<List<Student>> makeTeam(int searchType, int count, List<Long> exclusionIds) {
        if (count == 0) {
            return new ArrayList<>();
        }

        GenerateTeam generateTeam = new GenerateTeam();
        List<Student> findStudents = studentRepository.findStudents(exclusionIds);

        if (searchType == 1) {
            return generateTeam.makeTeamByTeamCount(count, findStudents);
        } else if (searchType == 2) {
            return generateTeam.makeTeamByMemberCount(count, findStudents);
        }

        throw new IllegalStateException();
    }
}
