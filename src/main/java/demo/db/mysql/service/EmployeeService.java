package demo.db.mysql.service;

import demo.db.mysql.domain.Employee;
import demo.db.mysql.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public void save(Employee employee) {
        employeeRepository.saveAndFlush(employee);
    }
}
