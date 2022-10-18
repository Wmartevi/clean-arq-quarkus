package clean.quarkus.infra.panache.entities;

import clean.quarkus.domain.models.Employee;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;


import javax.persistence.*;

@Entity
@Table(name = "employee")
public class PanacheEmployee extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "email")
    public String email;

    @Column(name = "password")
    public String password;

    @Column(name = "name")
    public String name;

    public Employee toEmployee() {
        Employee employee = new Employee();
        employee.email = this.email;
        employee.password = this.password;
        employee.name = this.name;
        employee.id = this.id;

        return employee;
    }
}
