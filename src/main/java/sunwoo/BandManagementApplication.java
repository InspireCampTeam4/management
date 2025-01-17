package sunwoo;

import sunwoo.view.CLIView;

import java.io.IOException;
import java.util.Scanner;

public class BandManagementApplication {
    public void run(Scanner scanner) throws IOException {
        ProjectInitializer.init();
        CLIView.cliMain(scanner);
    }
}
