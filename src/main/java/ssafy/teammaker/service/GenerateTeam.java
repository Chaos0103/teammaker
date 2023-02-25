package ssafy.teammaker.service;

import ssafy.teammaker.domain.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateTeam {

    private final Random random = new Random();

    public List<List<Student>> makeTeamByTeamCount(int count, List<Student> students) {
        List<List<Student>> res = initList(count);

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

    public List<List<Student>> makeTeamByMemberCount(int count, List<Student> students) {
        int size = students.size() / count;
        List<List<Student>> res = initList(size);

        boolean[] picked = new boolean[students.size()];

        int teamIndex = 0;
        while (teamIndex < students.size()) {
            int studentIndex = random.nextInt(students.size());
            if (picked[studentIndex]) {
                continue;
            }
            res.get(teamIndex++ % size).add(students.get(studentIndex));
            picked[studentIndex] = true;

        }

        return res;
    }

    private List<List<Student>> initList(int size) {
        List<List<Student>> res = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            res.add(new ArrayList<>());
        }
        return res;
    }
}
