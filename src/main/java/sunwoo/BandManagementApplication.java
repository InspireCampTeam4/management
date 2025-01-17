package sunwoo;

import sunwoo.view.CLIView;

import java.io.IOException;

public class BandManagementApplication {
    public static void main(String[] args) throws IOException {
        ProjectInitializer.init();
        CLIView.cliMain();
    }
}
