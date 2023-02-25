package ssafy.teammaker.controller;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SelectForm {

    private List<Long> exclusionIds;
    private String hidden = "";

    public SelectForm() {
        this.exclusionIds = new ArrayList<>();
        this.hidden = "";
    }
}
