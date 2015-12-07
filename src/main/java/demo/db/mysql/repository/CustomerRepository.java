package demo.db.mysql.repository;

import demo.db.mysql.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends BaseRepository<Customer, Integer>, CustomerRepositoryCustom {

    Customer findByName(String name);

    @Query("select c from Customer c where c.id = :id")
    Customer findCustomerById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM customer WHERE name like ?", nativeQuery = true)
    List<Customer> findByNameStartsWith(String name);


}
