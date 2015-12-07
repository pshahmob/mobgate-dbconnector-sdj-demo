package demo.db.mysql.repository;

import demo.db.mysql.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private EntityManager entityManager;

    @Autowired
    CustomerRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate, JpaContext context) {
        this.jdbcTemplate = jdbcTemplate;
        this.entityManager = context.getEntityManagerByManagedType(Customer.class);
    }

    public Integer getTotalCustomersStartsNameWith(String startsWith) {

        startsWith = startsWith + "%";

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("startsWith", startsWith);

        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM customer WHERE name like :startsWith",
                queryParams,
                Integer.class
        );

        return count;
    }
}
