package igor.henrique.screenMatchAPI.cli;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("cli")
@AllArgsConstructor
public class RunnerCLI implements CommandLineRunner {
	private final SeriesMenuCLI menuCLI;

    public static void main(String[] args) {
		SpringApplication.run(RunnerCLI.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		menuCLI.showMenu();
	}
}
