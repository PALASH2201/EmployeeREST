@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<EmployeeRest> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeRest> getEmployee(@PathVariable Integer id) {
        EmployeeRest employee = employeeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/employees")
    public ResponseEntity<String> addEmployee(@RequestBody @Valid EmployeeRest employee) {
        employeeRepository.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body("Employee added");
    }

    @PutMapping("/employees/{id}/{name}")
    public ResponseEntity<String> updateEmployee(@PathVariable Integer id, @PathVariable String name) {
        EmployeeRest employee = employeeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
        employee.setEmpName(name);
        employeeRepository.save(employee);
        return ResponseEntity.ok("Employee updated");
    }

    @GetMapping("/employees/dept/{name}")
    public ResponseEntity<List<EmployeeRest>> displayEmpByDept(@PathVariable String name) {
        List<EmployeeRest> employees = employeeRepository.findByEmpDept(name);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/employees/sort")
    public List<EmployeeRest> getEmployeesBySalary() {
        return employeeRepository.findByOrderByEmpSalary();
    }

    @DeleteMapping("/employees")
    public ResponseEntity<String> deleteAll() {
        if (employeeRepository.count() == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No employees to delete");
        }
        employeeRepository.deleteAll();
        return ResponseEntity.ok("All employees deleted");
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Integer id) {
        EmployeeRest employee = employeeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
        employeeRepository.deleteById(id);
        return ResponseEntity.ok("Employee deleted");
    }
}
