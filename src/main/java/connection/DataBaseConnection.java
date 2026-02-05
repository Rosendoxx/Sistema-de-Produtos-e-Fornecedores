package connection;

import import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class DataBaseConnection {
    private static DataBaseConnection instance;
    private final Connection CONNECTION;

    private DataBaseConnection() {
        Dotenv dotenv = Dotenv.load();

        String url = dotenv.get("DB_URL");
        String user = dotenv.get("DB_USER");
        String password = dotenv.get("DB_PASSWORD");
        try{
            CONNECTION = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Ocorreu um erro ao se conectar com banco de dados");
        }
    }

    public static DataBaseConnection getInstance(){
        try{
            if(Objects.isNull(instance) || Objects.isNull(instance.connection()) || instance.connection().isClosed()){
                instance = new DataBaseConnection();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return instance;
    }

    public Connection connection() {
        return CONNECTION;
    }
}
