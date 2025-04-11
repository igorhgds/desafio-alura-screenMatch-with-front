package igor.henrique.screenMatchAPI.cli;

import igor.henrique.screenMatchAPI.repositories.SerieRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("cli")
@AllArgsConstructor
public class RunnerCLI implements CommandLineRunner {
	@Autowired
	private SerieRepository repository;

    public static void main(String[] args) {
		SpringApplication.run(RunnerCLI.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		SeriesMenuCLI menuCLI = new SeriesMenuCLI(repository);
		menuCLI.showMenu();
	}
}
