package ssafy.teammaker.repository;

import ssafy.teammaker.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    Long save(String name);

    Optional<Student> findById(Long studentId);

    List<Student> findAll();

    List<Student> findStudents(List<Long> exclusionsId);

    void remove(Long id);

    void removeAll();

    boolean saveCSV();
}
