package ssafy.teammaker.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ssafy.teammaker.domain.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @BeforeEach
    void beforeEach() {
        studentRepository.removeAll();
    }

    @Test
    @DisplayName("회원 저장")
    void saveMember () {
        //given

        //when
        Long memberId = studentRepository.save("memberName");

        //then
        Optional<Student> findMember = studentRepository.findById(memberId);
        assertThat(findMember).isNotEmpty();
    }

    @Test
    @DisplayName("전체 조회")
    void findAll() {
        //given
        int size = 5;
        for (int i = 1; i <= size; i++) {
            studentRepository.save("memberName" + i);
        }

        //when
        List<Student> findStudents = studentRepository.findAll();

        //then
        assertThat(findStudents.size()).isEqualTo(size);
    }

    @Test
    @DisplayName("전체 조회 - pk 제외")
    void findStudents() {
        //given
        int size = 10;
        List<Long> exclusionIds = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            Long memberId = studentRepository.save("memberName" + i);
            if (i <= 3) {
                exclusionIds.add(memberId);
            }
        }

        //when
        List<Student> findStudents = studentRepository.findStudents(exclusionIds);

        //then
        assertThat(findStudents.size()).isEqualTo(size - exclusionIds.size());
    }

    @Test
    @DisplayName("회원 삭제")
    void removeMember () {
        //given
        Long memberId = studentRepository.save("memberName");

        //when
        studentRepository.remove(memberId);

        //then
        Optional<Student> findMember = studentRepository.findById(memberId);
        assertThat(findMember).isEmpty();
    }
}