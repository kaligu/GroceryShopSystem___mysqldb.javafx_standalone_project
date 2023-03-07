package lk.ijse.groceryshop.dao;

import java.io.Serializable;
import java.util.Optional;

public interface CrudDAO<T extends Serializable,ID extends Serializable> extends SuperDAO{

    boolean save(T entity);

    boolean update(T entity);

    boolean deleteByPk(ID pk);

    Optional<T> findByPk(ID pk) ;

    boolean existByPk(ID pk) ;

}
