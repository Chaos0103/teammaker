package ssafy.teammaker.controller;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TeamMakerForm {

    private Integer searchType;
    private Integer count;
    private List<Long> exclusionsIds;

    public TeamMakerForm() {
        this.searchType = 1;
        this.count = 0;
        this.exclusionsIds = new ArrayList<>();
    }
}
