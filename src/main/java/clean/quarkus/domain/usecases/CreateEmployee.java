package clean.quarkus.domain.usecases;


import clean.quarkus.domain.dto.CreateEmployeeDTO;
import clean.quarkus.domain.models.Employee;

public interface CreateEmployee {
    Employee create(CreateEmployeeDTO employee);
}
