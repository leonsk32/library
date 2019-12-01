package features;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbSteps {
    private Connection connection;


    private String createUserBy(String id, String mail, String familyName, String firstName) {
        return String.format("INSERT INTO userr VALUES ('%s', '%s', '%s', '%s');", id, mail, familyName, firstName);
    }

    private String createBookBy(String isbn, int amount) {
        return String.format("INSERT INTO book VALUES ('%s', '%d');", isbn, amount);
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

    @When("{string} が {string}　の本を借りる")
    public void がの本を借りる(String userId, String isbn) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(this.createLentBy(userId, isbn));
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

    @When("id {string} mail {string} 氏　{string} 名 {string} のユーザを作る")
    public void createUser(String id, String mail, String familyName, String firstname) throws Throwable {
        Statement statement = connection.createStatement();
        statement.execute(this.createUserBy(id, mail, familyName, firstname));
    }

    @When("isbn {string} amount {int} の本を作る")
    public void createBook(String isbn, int amount) throws Throwable {
        Statement statement = connection.createStatement();
        statement.execute(this.createBookBy(isbn, amount));
    }

    @Then("^コネクションを閉じる$")
    public void connectionClose() throws Throwable {
        connection.close();
    }
}
