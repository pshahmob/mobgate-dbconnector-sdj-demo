package demo.db.mysql.config;

import com.mobiquityinc.apigateway.mysql.dao.api.jpa.MySqlContextConfig;
import demo.db.mysql.repository.BaseRepositoryImpl;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"demo.db.mysql.endpoint", "demo.db.mysql.service", "demo.db.mysql.repository"})
@PropertySource(value = "classpath:application.properties")
@Import(MySqlContextConfig.class)
@EnableJpaRepositories(entityManagerFactoryRef = "mySQLEntityManagerFactory",
        transactionManagerRef = "mySQLTxManager",
        basePackages = "demo.db.mysql.repository",
        repositoryBaseClass = BaseRepositoryImpl.class)
public class ApplicationConfiguration {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {

        return new PropertySourcesPlaceholderConfigurer();
    }
}
