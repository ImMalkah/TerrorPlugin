package co.terrorsquadmc.terrorplugin.SQL;

import java.sql.*;
import java.util.Date;

public class SQLUtil {

    String url, port, database, username, password;
    Connection connection = null;

    public SQLUtil(String url, String port, String database, String username, String password) {
        this.url = url;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public void connect() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://" + url + ":" + port + "/" + database, username, password);
            System.out.println("Successfully connected to Terror Squad database");
        } catch (SQLException e) {
            System.out.println("Failed to connect to database...");
            e.printStackTrace();
        }
    }

    public void createTable(String table, String attributes) {
        if (localStatement("CREATE TABLE IF NOT EXISTS " + table + "(" + attributes + ")"))
            System.out.println("Successfully created table: " + table);
        else System.out.println("Failed to create table: " + table);
    }

    public void deleteTable(String table) {
        if (localStatement("DROP TABLE " + table))
            System.out.println("Successfully deleted table: " + table);
        else System.out.println("Failed to delete table: " + table);
    }

    public void addColumn(String table, String column, String datatype) {
        if (localStatement("ALTER TABLE " + table + " ADD " + column + " " + datatype))
            System.out.println("Success added column '" + column + "' to table '" + table +"'");
        else System.out.println("Failed to add column '" + column + "' to table '" + table +"'");
    }

    public void deleteColumn(String table, String column) {
        if (localStatement("ALTER TABLE " + table + " DROP " + column))
            System.out.println("Success removed column '" + column + "' from table '" + table +"'");
        else System.out.println("Failed to remove column '" + column + "' from table '" + table +"'");
    }

    public boolean localStatement(String sql) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
            statement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public PlayerStats findPlayerByUUID(String uuid) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM player_stats WHERE uuid = ?");
        statement.setString(1, uuid);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int kills = resultSet.getInt("kills");
            Date firstLogin = resultSet.getDate("first_login");
            long blocksBroken = resultSet.getLong("blocks_broken");
            int deaths = resultSet.getInt("deaths");
            String name = resultSet.getString("name");
            Date lastLogin = resultSet.getDate("last_login");

            return new PlayerStats(uuid, kills, firstLogin, blocksBroken, deaths, name, lastLogin);
        }

        return null;
    }

    public void createStats(PlayerStats stats) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO player_stats(uuid, kills, first_login, blocks_broken, deaths, name) VALUES (?,?,?,?,?,?)");
        statement.setString(1, stats.getUuid());
        statement.setInt(2, stats.getKills());
        statement.setDate(3, new java.sql.Date(stats.getFirstLogin().getTime()));
        statement.setLong(4, stats.getBlocksBroken());
        statement.setInt(5, stats.getDeaths());
        statement.setString(6, stats.getName());

        statement.executeUpdate();
    }

    public void updateStats(PlayerStats stats) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE player_stats SET kills = ?, blocks_broken = ?, deaths = ?, name = ?, last_login = ? WHERE uuid = ?");
        statement.setInt(1, stats.getKills());
        statement.setLong(2, stats.getBlocksBroken());
        statement.setInt(3, stats.getDeaths());
        statement.setString(4, stats.getName());
        statement.setDate(5, new java.sql.Date(stats.getLastLogin().getTime()));
        statement.setString(6, stats.getUuid());

        statement.executeUpdate();
    }

    public Connection getConnection() {
        return connection;
    }

    public boolean isConnected() {
        return connection != null;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

}
