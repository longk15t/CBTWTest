package ctflearn.models;

import lombok.Data;

@Data
public class Config {
    private String environment, browser;
    private int implicitWait, explicitWait;
    private Env dev, qa, staging;
}
