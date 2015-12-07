package demo.db.mysql.service;

import demo.db.mysql.domain.Customer;
import demo.db.mysql.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public List<Customer> findAll() {
        return (List<Customer> ) customerRepository.findAll();
    }

    public void delete(Integer custId) {
        final Customer customer = customerRepository.findOne(custId);
        customerRepository.delete(customer);
    }

    public void saveWithUpperCaseName(Customer customer) {
        //        customerRepository.saveAndFlush(customer);
        customerRepository.saveWithUpperCaseName(customer);
    }

    public void update(Customer customer) {
        final Customer customerFromDB = customerRepository.findOne(customer.getId());
        updateCustomer(customer, customerFromDB);
    }

    @Transactional("mySQLTxManager")
    private void updateCustomer(@RequestBody Customer customer, Customer customerFromDB) {
        customerFromDB.setName(customer.getName());
        customerRepository.save(customerFromDB);
    }

    public Customer findByName(String custName) {
        return customerRepository.findByName(custName);
    }

    public Customer findCustomerById(Integer custId) {
        return customerRepository.findCustomerById(custId);
    }

    public List<Customer> findByNameStartsWith(String nameStartsWith) {
        return customerRepository.findByNameStartsWith(nameStartsWith);
    }

    public Integer getTotalCustomersStartsNameWith(String custName) {
        return customerRepository.getTotalCustomersStartsNameWith(custName);
    }
}
