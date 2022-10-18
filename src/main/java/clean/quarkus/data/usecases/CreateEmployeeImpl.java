package clean.quarkus.data.usecases;



import clean.quarkus.data.interfaces.EmployeeRepository;
import clean.quarkus.data.interfaces.Encrypter;
import clean.quarkus.domain.dto.CreateEmployeeDTO;
import clean.quarkus.domain.models.Employee;
import clean.quarkus.domain.usecases.CreateEmployee;

import javax.transaction.Transactional;

public class CreateEmployeeImpl implements CreateEmployee {

    private EmployeeRepository employeeRepository;
    private Encrypter encrypter;

    public CreateEmployeeImpl(EmployeeRepository employeeRepository, Encrypter encrypter) {
        this.employeeRepository = employeeRepository;
        this.encrypter = encrypter;
    }

    @Override
    @Transactional
    public Employee create(CreateEmployeeDTO employeeDTO) {
        Employee employee = this.employeeRepository.findByEmail(employeeDTO.email);
        if(employee != null) {
            throw new RuntimeException("Já existe um funcionário com este email");
        }
        employeeDTO.password = this.encrypter.hash(employeeDTO.password);
        return this.employeeRepository.create(employeeDTO);
    }
}
