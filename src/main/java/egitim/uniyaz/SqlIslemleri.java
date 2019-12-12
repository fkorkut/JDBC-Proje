package egitim.uniyaz;

import java.sql.*;

public class SqlIslemleri {
    final static String JDBC_CONNECTION_STR = "jdbc:mysql://127.0.0.1:3306/sinav?serverTimezone=UTC";//TimeZone hatası alındığından ?serverTimezone=UTC eklenmiştir.
    final static String USERNAME = "root";
    final static String PASSWORD = "12345";
    static String printFormat = " %-15s  %-7d %n";//15 karaterlik ve 7 sayılık alan ayrıldı.

    public boolean baglantiyiKontrolEt() {

        try (Connection conn = DriverManager.getConnection(JDBC_CONNECTION_STR, USERNAME, PASSWORD)) {
            if (conn != null) {
                return true;
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public void getHeroList() {
        String printFormatHero = " %-3d  %-10s  %-10s %n";
        String sql = "Select * from hero";

        try (Connection conn = DriverManager.getConnection(JDBC_CONNECTION_STR, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.format(" ID   Name \t\t  Surname  \n");
            System.out.println("---------------------------------");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");;

                System.out.format(printFormatHero, id, name, surname);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getMovieList() {
        String printFormatMovie = " %-7d  %-15s  %-7d %n";
        String sql = "Select * from movie";

        try (Connection conn = DriverManager.getConnection(JDBC_CONNECTION_STR, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.format(" Hero ID   Movie \t\t  Budget  \n");
            System.out.println("---------------------------------");
            while (resultSet.next()) {
                int heroId = resultSet.getInt("hero_id");
                String movieName = resultSet.getString("movie");
                int budget = resultSet.getInt("budget");;

                System.out.format(printFormatMovie, heroId, movieName, budget);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getHeroTotalBudget(){
        String sql = "select concat( H.name ,' ', H.surname) as Hero ,ifnull(sum(M.budget),0) as Total_budget from Hero H left join Movie M on H.id=M.hero_id group by H.name order by total_budget desc";

        try (Connection conn = DriverManager.getConnection(JDBC_CONNECTION_STR, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();


            System.out.format(" Hero \t\t\t  Total Budget  %n");
            System.out.println("---------------------------------");
            while (resultSet.next()) {
                String heroName = resultSet.getString("Hero");
                int totalBudget = resultSet.getInt("Total_budget");;

                System.out.format(printFormat,heroName, totalBudget);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  void getHeroMovieCount(){
        String sql = "select concat( H.name,' ', H.surname) as Hero ,count(M.hero_id) as movie_count from Hero H inner join Movie M on H.id=M.hero_id group by H.name";

        try (Connection conn = DriverManager.getConnection(JDBC_CONNECTION_STR, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.format(" Hero \t\t\t  Movie Count \n");
            System.out.println("---------------------------------");
            while (resultSet.next()) {
                String heroName = resultSet.getString("Hero");
                int movieCount = resultSet.getInt("movie_count");;

                System.out.format(printFormat,heroName, movieCount);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
