package cn.originmc.plugins.magicpaper.database.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//TODO 完善方法并封装为语义
public class MySQLManager {
    private final MySQLSource dataSource;

    public MySQLManager(String host, String port, String database, String username, String password) {
        this.dataSource = new MySQLSource(host, port, database, username, password);
    }

    public ResultSet executeQuery(String query, Object... params) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int executeUpdate(String query, Object... params) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void close() {
        dataSource.getDataSource().close();
    }
}
