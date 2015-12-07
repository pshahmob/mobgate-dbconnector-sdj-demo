package demo.db.mysql.endpoint;

import demo.db.mysql.domain.Customer;
import demo.db.mysql.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/demo/db/mysql")
public class CustomerEndpoint {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/getAllCustomers")
    @ResponseBody
    public String getAllCustomers() {

        final List<Customer> customers = customerService.findAll();

        return customers.toString();
    }

    @RequestMapping(value = "/deleteCustomer/{custId}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteCustomer(@PathVariable Integer custId) {

        customerService.delete(custId);

        return "deleted successfully!";
    }

    @RequestMapping(value = "/createCustomer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createCustomer(@RequestBody Customer customer) {

        customerService.saveWithUpperCaseName(customer);

        return "created customer successfully!";
    }

    @RequestMapping(value = "/updateCustomer", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Transactional("mySQLTxManager")
    public String updateCustomer(@RequestBody Customer customer) {

        customerService.update(customer);

        return "updated customer successfully!";
    }

    @RequestMapping(value = "/getCustomer/{custName}")
    @ResponseBody
    public String getCustomerByName(@PathVariable String custName) {
        final Customer customer = customerService.findByName(custName);

        return custName + "'s id is: " + customer.getId();
    }

    @RequestMapping(value = "/getCustomer/id/{custId}")
    @ResponseBody
    public String getCustomerById(@PathVariable Integer custId) {
        final Customer customer = customerService.findCustomerById(custId);

        return "Customer Id: " + custId + "'s name is: " + customer.getName();
    }

    @RequestMapping(value = "/getCustomer/name/startswith/{custName}")
    @ResponseBody
    public String getCustomerByNameStartsWith(@PathVariable String custName) {
        final List<Customer> customers = customerService.findByNameStartsWith(custName + "%");

        return "Customer's name starts with: " + custName + " are " + customers;
    }

    @RequestMapping(value = "/getCustomerCount/name/startswith/{custName}")
    @ResponseBody
    public String getCustomerCountByNameStartsWith(@PathVariable String custName) {
        Integer count = customerService.getTotalCustomersStartsNameWith(custName);

        return "Customer's name starts with: " + custName + " are " + count;
    }

}
