package dialer.variables;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Constants {
    public static final Path deviceFarm = Paths.get(System.getProperty("user.dir"),
            "src", "test", "resources", "devices");
}
