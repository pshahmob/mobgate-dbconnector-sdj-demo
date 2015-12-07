package demo.db.mysql.repository;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    private final EntityManager entityManager;

    public BaseRepositoryImpl(JpaEntityInformation entityInformation,
                            EntityManager entityManager) {
        super(entityInformation, entityManager);

        this.entityManager = entityManager;
    }

    @Override
    @Transactional("mySQLTxManager")
    public void saveWithUpperCaseName(T t) {
        try {
            final Class<?> aClass = t.getClass();
            Method method = aClass.getDeclaredMethod("getName");
            Object invoke = method.invoke(t);

            method = aClass.getDeclaredMethod("setName", String.class);
            method.invoke(t, ((String) invoke).toUpperCase());
        } catch (Exception e) {
            e.printStackTrace();
        }

        saveAndFlush(t);
    }
}
