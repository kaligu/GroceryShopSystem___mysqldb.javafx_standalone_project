package lk.ijse.groceryshop.dao.util;
;
import lk.ijse.groceryshop.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtil {
   /** private static PreparedStatement getPreparedStatement(String sql,Object...params) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            statement.setObject((i+1),params[i]);
        }
        return statement;
    }
    public static ResultSet executeQuery(String sql, Object...params) throws SQLException, ClassNotFoundException {
        return getPreparedStatement(sql, params).executeQuery();
    }
    public static boolean executeUpdate(String sql, Object...params) throws SQLException, ClassNotFoundException {
        return getPreparedStatement(sql, params).executeUpdate()>0;
    }
    */

   public static <T> T execute(String sql,Object...params) throws SQLException, ClassNotFoundException {
       PreparedStatement statement = DBConnection.getDbcConnection().getConnection().prepareStatement(sql);
       for (int i = 0; i < params.length; i++) {
           statement.setObject((i+1),params[i]);
       }
       if (sql.startsWith("SELECT")){
           return (T)statement.executeQuery();
       }
       return (T)(Boolean)(statement.executeUpdate()>0);
   }

}
