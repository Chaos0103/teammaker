package ssafy.teammaker.repository;

import ssafy.teammaker.domain.Student;

import java.util.List;

public interface StudentRepository {

    Long save(String name);

    List<Student> findAll();

    List<Student> findStudents(List<Long> exclusionsId);

    void remove(Long id);

    void removeAll();

    boolean saveCSV();
}
