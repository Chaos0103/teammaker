package ssafy.teammaker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.teammaker.domain.Student;
import ssafy.teammaker.repository.StudentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;

    public void addStudents(String data) {
        String[] names = data.split(",");
        for (String name : names) {
            repository.save(name);
        }
        repository.saveCSV();
    }

    public List<Student> findStudents() {
        return repository.findAll();
    }

    public void removeStudent(Long id) {
        repository.remove(id);
    }

    public void removeAllStudents() {
        repository.removeAll();
    }

    public boolean externalStorage() {
        return repository.saveCSV();
    }
}
