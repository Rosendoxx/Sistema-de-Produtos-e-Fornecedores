package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class DataBaseConnection {
    private static DataBaseConnection instance;
    private final Connection CONNECTION;

    private DataBaseConnection() {
        try{
            CONNECTION = DriverManager.getConnection("jdbc:mysql://localhost:3306/GerenciadorEstoque", "root", "12345678");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Ocorreu um erro ao se conectar com banco de dados");
        }
    }

    public static DataBaseConnection getInstance(){
        try{
            if(Objects.isNull(instance) || instance.connection().isClosed()){
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
