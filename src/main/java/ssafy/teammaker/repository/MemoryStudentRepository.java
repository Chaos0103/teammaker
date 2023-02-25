package ssafy.teammaker.repository;

import org.springframework.stereotype.Repository;
import ssafy.teammaker.domain.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static ssafy.teammaker.repository.Constant.*;

@Repository
public class MemoryStudentRepository implements StudentRepository {

    private static final HashMap<Long, Student> store = new HashMap<>();
    private static Long instance;

    private MemoryStudentRepository() {
        loadCSV();
        instance = (long) store.size();
    }

    @Override
    public Long save(String name) {
        store.put(++instance, new Student(instance, name));
        return instance;
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public List<Student> findStudents(List<Long> exclusionsId) {
        return new ArrayList<>(store.values()).stream()
                .filter(student -> !exclusionsId.contains(student.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public void remove(Long id) {
        store.remove(id);
    }

    @Override
    public void removeAll() {
        store.clear();
    }

    private void loadCSV() {
        File file = new File(FILE_NAME);
        BufferedReader reader = null;
        String line = "";

        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                store.put(Long.parseLong(data[0]), new Student(Long.parseLong(data[0]), data[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean saveCSV() {
        File file = new File(FILE_NAME);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file, true));
            for (Student student : store.values()) {
                writer.write(student.toString());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.flush();
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
