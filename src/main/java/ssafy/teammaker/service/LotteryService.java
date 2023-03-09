package ssafy.teammaker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.teammaker.domain.Student;
import ssafy.teammaker.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LotteryService {

    private final StudentRepository studentRepository;

    private static final double FIRST = 1;
    private static final double SECOND = 3;
    private static final double THIRD = 5;
    private static final double FOURTH = 5;


    public List<Student> getRandomStudents() {
        return studentRepository.findAll();
    }

    public void LotteryStudent() {

    }

    public List<Double> getRate(int studentCount) {
        List<Double> rates = new ArrayList<>();
        rates.add(FIRST / studentCount);
        rates.add(SECOND / studentCount);
        rates.add(THIRD / studentCount);
        rates.add(FOURTH / studentCount);
        return rates;
    }

    public void enhance() {

    }

    public void stop() {

    }
}
