package lk.ijse.groceryshop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.groceryshop.embeded.CustMobile;
import lk.ijse.groceryshop.embeded.CustName;
import lk.ijse.groceryshop.entity.Customer;
import lk.ijse.groceryshop.util.HbFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.ArrayList;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        /*
        try {
            primaryStage.setScene(new Scene(
                    FXMLLoader.load(
                            getClass().getResource("resources/forms/DashboardForm.fxml")
                    )
            ));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.show();
        */

        Session session = HbFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();


        //save customer object
        /*
        ArrayList<CustMobile> cutsMobileNos = new ArrayList<>();
        cutsMobileNos.add(new CustMobile("076644434"));
        cutsMobileNos.add(new CustMobile("072434544"));

        Customer c = new Customer();
        c.setId("C001");
        c.setName(new CustName("Chethiya","Kaligu","Jayanath"));
        c.setAge(20);
        c.setAddress("Horana");
        c.setSalary(233333);
        c.setPhoneNos(cutsMobileNos);
        session.save(c);
         */

        System.out.println(session.get(Customer.class,"C001"));

        transaction.commit();
        session.close();
    }
}
