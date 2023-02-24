package ssafy.teammaker.repository;

import ssafy.teammaker.domain.Student;

import java.util.List;

public interface StudentRepository {

    Long save(String name);

    List<Student> findAll();

    void remove(Long id);

    void removeAll();

    boolean saveCSV();
}
