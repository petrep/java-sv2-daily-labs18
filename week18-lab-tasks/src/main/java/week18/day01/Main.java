package week18.day01;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.flywaydb.core.Flyway;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        MysqlDataSource dataSource = new MysqlDataSource();
//        try{
            dataSource.setUrl("jdbc:mysql://localhost:3308/bookstore?useUnicode=true&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC");
            dataSource.setUser("root");
            dataSource.setPassword("password");
//        } catch (SQLException sqle) {
//            throw new IllegalStateException("Cannot reach Database!");
//        }

        Flyway flyway = Flyway.configure().locations("db/migration/bookstore").dataSource(dataSource).load();
        flyway.clean();
        flyway.migrate();

        BooksRepository booksRepository = new BooksRepository(dataSource);

        booksRepository.insertBook("Fekete István", "Vuk", 3400,  10);
        booksRepository.insertBook("Fekete Péter", "Kártyatrükkök", 1200,  2);

        booksRepository.updatePieces(2L, 30);
        System.out.println(booksRepository.findBooksByWriter("Fekete Péter"));

    }




}
