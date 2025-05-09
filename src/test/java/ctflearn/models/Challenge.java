package ctflearn.models;

import lombok.Data;

@Data
public class Challenge {
    private String title, flag, description, attachFile, category, howWeSolveThis;
    private int points;
}
