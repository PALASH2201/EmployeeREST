import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeRestAppApplication implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeConfig employeeConfig;

    public static void main(String[] args) {
        SpringApplication.run(EmployeeRestAppApplication.class, args);
        System.out.println("Running");
    }

    @Override
    public void run(String... args) throws Exception {
        // Save employees defined in the configuration file
        employeeRepository.saveAll(employeeConfig.getEmployees());
        System.out.println("Employees added by default");
    }
}
