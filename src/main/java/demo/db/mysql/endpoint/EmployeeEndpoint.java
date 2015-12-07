package demo.db.mysql.endpoint;

import demo.db.mysql.domain.Employee;
import demo.db.mysql.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("demo/db/mysql/employee")
public class EmployeeEndpoint {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/getAll")
    @ResponseBody
    public String getAllEmployee() {
        final List<Employee> employees = employeeService.getAll();

        return employees.toString();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createCustomer(@RequestBody Employee employee) {

        employeeService.save(employee);

        return "created employee successfully!";
    }

}
