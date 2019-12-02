package features;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DbSteps {
    private Connection connection;


    private String createUserBy(String id, String mail, String familyName, String firstName) {
        return String.format("INSERT INTO userr VALUES ('%s', '%s', '%s', '%s');", id, mail, familyName, firstName);
    }

    private String createBookBy(String isbn, String amount) {
        return String.format("INSERT INTO book VALUES ('%s', '%s');", isbn, amount);
    }

    private String createLentBy(String userId, String isbn) {
        return String.format("INSERT INTO lending_event VALUES ('%s', '%s');", isbn, userId);
    }

    @Given("^DBの設定をする$")
    public void setupDB() throws Throwable {
        String url = "jdbc:postgresql://localhost:15432/library";

        connection = DriverManager.getConnection(url, "libuser", "libpass");
    }

    @Given("^初期化$")
    public void initialize() throws Throwable {
        for (DB oneDB : DB.values()) {
            Statement statement = connection.createStatement();
            statement.execute(oneDB.getDeleteSql());
        }
    }

    @And("userId が isbn の本を借りる")
    public void がの本を借りる(DataTable dataTable) throws SQLException {
        boolean header = true;
        for (List<String> row : dataTable.asLists()) {
            if (header){
                header = false;
                continue;
            }
            String userId = row.get(0);
            String isbn = row.get(1);
            Statement statement = connection.createStatement();
            statement.execute(this.createLentBy(userId, isbn));
        }
    }

    @And("ユーザを作る")
    public void createUser(DataTable dataTable) throws Throwable {
        boolean header = true;
        for (List<String> a : dataTable.asLists()) {
            if (header){
                header = false;
                continue;
            }
            String id = a.get(0);
            String mail = a.get(1);
            String familyName = a.get(2);
            String firstname = a.get(3);
            Statement statement = connection.createStatement();
            statement.execute(this.createUserBy(id, mail, familyName, firstname));
        }
    }

    @And("本を作る")
    public void createBook(DataTable dataTable) throws Throwable {
        boolean header = true;
        for (List<String> row : dataTable.asLists()) {
            if (header){
                header = false;
                continue;
            }
            String isbn = row.get(0);
            String amount = row.get(1);
            Statement statement = connection.createStatement();
            statement.execute(this.createBookBy(isbn, amount));
        }
    }

    public enum DB {
        LENDING_EVENT("DELETE FROM LENDING_EVENT"),
        RETURN_EVENT("DELETE FROM RETURN_EVENT"),
        BOOK("DELETE FROM BOOK"),
        USERR("DELETE FROM USERR");

        String deleteSql;

        DB(String deleteSql) {
            this.deleteSql = deleteSql;
        }

        public String getDeleteSql() {
            return deleteSql;
        }
    }


    @Then("^コネクションを閉じる$")
    public void connectionClose() throws Throwable {
        connection.close();
    }
}
