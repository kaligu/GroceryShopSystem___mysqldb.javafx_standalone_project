package lk.ijse.groceryshop.dao;

import org.hibernate.Session;

import java.io.Serializable;
import java.util.Optional;

public interface CrudDAO<T extends Serializable,ID extends Serializable> extends SuperDAO{

    boolean save(T entity , Session session);

    boolean update(T entity, Session session);

    boolean deleteByPk(ID pk, Session session);

    T findByPk(ID pk, Session session) ;

    boolean existByPk(ID pk) ;

}
