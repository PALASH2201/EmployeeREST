import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "employees")
public class EmployeeConfig {

    private List<EmployeeRest> employees;

    public List<EmployeeRest> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeRest> employees) {
        this.employees = employees;
    }
}
