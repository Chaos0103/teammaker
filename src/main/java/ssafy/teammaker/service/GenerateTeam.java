package ssafy.teammaker.service;

import ssafy.teammaker.domain.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateTeam {

    private final Random random = new Random();

    public List<List<Student>> makeTeamByTeamCount(int count, List<Student> students) {
        List<List<Student>> res = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            res.add(new ArrayList<>());
        }

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
        Random random = new Random();

        List<List<Student>> res = new ArrayList<>();
        for (int i = 0; i < students.size() / count + 1; i++) {
            res.add(new ArrayList<>());
        }

        boolean[] picked = new boolean[students.size()];

        int teamIndex = 0;
        int memberCount = 0;
        while (memberCount < students.size()) {
            int studentIndex = random.nextInt(students.size());
            if (picked[studentIndex]) {
                continue;
            }
            res.get(teamIndex).add(students.get(studentIndex));
            memberCount++;
            if (memberCount % count == 0) {
                teamIndex++;
            }
            picked[studentIndex] = true;
        }

        return res;
    }
}
