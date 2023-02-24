package ssafy.teammaker.domain;

import lombok.Getter;

@Getter
public class Student {

    private Long id;
    private String name;

    public Student(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%d,%s", id, name);
    }
}
