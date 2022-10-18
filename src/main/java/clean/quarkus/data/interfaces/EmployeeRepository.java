package clean.quarkus.data.interfaces;


import clean.quarkus.domain.dto.CreateEmployeeDTO;
import clean.quarkus.domain.models.Employee;

public interface EmployeeRepository {
    Employee findByEmail(String email);
    Employee create(CreateEmployeeDTO employee);
}
