package utils;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class DatabaseUtils {

    private static String dbUrl;
    private static String dbUser;
    private static String dbPassword;


    public static Connection createConnection(String env) {
        try {
            Properties properties = new Properties();

            // database.properties dosyasını oku
            InputStream inputStream = DatabaseUtils.class.getClassLoader().getResourceAsStream("database.properties");
            if (inputStream == null) {
                throw new RuntimeException("database.properties dosyası bulunamadı!");
            }
            properties.load(inputStream);

            // Ortama göre yapılandırmayı ayarla
            dbUrl = properties.getProperty(env + ".db.url");
            System.out.println(dbUrl);
            dbUser = properties.getProperty(env + ".db.user");
            System.out.println(dbUser);
            dbPassword = properties.getProperty(env + ".db.password");
            System.out.println(dbPassword);

            if (dbUrl == null || dbUser == null || dbPassword == null) {
                throw new RuntimeException(env + " ortamı için eksik veritabanı yapılandırması!");
            }
        } catch (Exception e) {
            throw new RuntimeException("Veritabanı yapılandırması yüklenirken hata oluştu: " + e.getMessage(), e);
        }
        try {
            // JDBC bağlantısını oluştur
            Connection connection = DriverManager.getConnection(dbUrl, dbUser,dbPassword);
            System.out.println("Veritabanı bağlantısı başarıyla oluşturuldu!");
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Veritabanı bağlantısı oluşturulurken hata oluştu: " + e.getMessage(), e);
        }
    }

    public static List<Map<String, Object>> runQuery(Connection connection, String query) {
        List<Map<String, Object>> results = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnName(i), resultSet.getObject(i));
                }
                results.add(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL sorgusu çalıştırılırken hata oluştu: " + e.getMessage(), e);
        }
        return results;
    }

}
