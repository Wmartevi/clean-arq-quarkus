package clean.quarkus.main;



import clean.quarkus.data.interfaces.EmployeeRepository;
import clean.quarkus.data.interfaces.Encrypter;
import clean.quarkus.data.usecases.CreateEmployeeImpl;
import clean.quarkus.domain.dto.CreateEmployeeDTO;
import clean.quarkus.domain.models.Employee;
import clean.quarkus.domain.usecases.CreateEmployee;
import clean.quarkus.infra.panache.repositories.PanacheEmployeeRepository;
import clean.quarkus.infra.util.BCryptAdapter;

import javax.inject.Singleton;
import javax.transaction.Transactional;

@Singleton
public class CreateEmployeeService {

    CreateEmployee createEmployee;

    public CreateEmployeeService() {
        Encrypter encrypter = new BCryptAdapter();
        EmployeeRepository employeeRepository = new PanacheEmployeeRepository();
        this.createEmployee = new CreateEmployeeImpl(employeeRepository, encrypter);
    }

    @Transactional
    public Employee handle(CreateEmployeeDTO createEmployeeDTO) {
        return this.createEmployee.create(createEmployeeDTO);
    }
}
