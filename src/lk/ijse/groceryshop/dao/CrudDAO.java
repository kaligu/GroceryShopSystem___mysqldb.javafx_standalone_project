package lk.ijse.groceryshop.dao;

import lk.ijse.groceryshop.entity.SuperEntity;

import java.io.Serializable;
import java.util.Optional;

public interface CrudDAO<T extends SuperEntity,ID extends Serializable> extends SuperDAO{

    T save(T entity);

    T update(T entity);

    void deleteByPk(ID pk);

    Optional<T> findByPk(ID pk) ;

    boolean existByPk(ID pk) ;

}
