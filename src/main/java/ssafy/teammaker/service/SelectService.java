package ssafy.teammaker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.teammaker.domain.Student;
import ssafy.teammaker.repository.StudentRepository;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class SelectService {

    private final StudentRepository studentRepository;

    public Student findRandomOneStudent(List<Long> exclusionIds, String hidden) {
        if (!hidden.isEmpty()) {
            for (String id : hidden.split(",")) {
                exclusionIds.add(Long.parseLong(id));
            }
        }

        Random random = new Random();

        List<Student> findStudents = studentRepository.findStudents(exclusionIds);
        int randomIndex = random.nextInt(findStudents.size());

        return findStudents.get(randomIndex);
    }

    //중복 추첨을 켜거나 끌 수 있다
    //뽑힌 횟수를 초기화 할 수 있다
    //등록된 명단 중 미참여자는 제외할 수 있다
}
