package Week4.FirstProjectWork;

import java.io.*;
import java.sql.*;

public class DeveloperClass implements Developers{
    String url = "jdbc:mysql://localhost:3306/developer";
    String userName = "root";
    String pw ="12345";

    @Override
    public ResultSet loadDevelopers() {
        try{
            Connection connection = DriverManager.getConnection(url, userName, pw);
            createSQLTable(connection);
            methodToPopulateSQL(connection);
            String query = "SELECT * FROM developers";
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    private void createSQLTable(Connection connection) throws Exception{
        String createDevelopersTable = "CREATE TABLE IF NOT EXISTS developers(name Text, age Integer, location Text, skill Text)";
        Statement statement = connection.createStatement();
        statement.execute(createDevelopersTable);
        statement.close();
    }

    private void closeConnectionToDb(Connection connection){
        try{
            if(connection != null){
                connection.close();
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private void methodToPopulateSQL(Connection connection) throws Exception{
        String filePath = "C:\\Users\\user\\Desktop\\Java-Resources\\INGRYD\\WORKBOOK\\src\\Week4\\FirstProjectWork\\project.txt";
        try(
                BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
                Statement statement = connection.createStatement();
        ){
            String line;
            while ((line = bufferedReader.readLine()) != null) {
//                System.out.println("Read line: " + line);
                String[] data = line.split(", ");
                String name = data[0];
                int age = Integer.parseInt(data[1]);
                String location = data[2];
                String skill = data[3].replace("#", "");
                String insertSQLValues = "INSERT INTO developers(name, age, location, skill) VALUES('"+name+"','"+age+"','"+location+"','"+skill+"')";
                statement.executeUpdate(insertSQLValues);
            }
            statement.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        DeveloperClass devClass_DB = new DeveloperClass();
        ResultSet resultSet = devClass_DB.loadDevelopers();
        try{
            while (resultSet.next()){
                System.out.println("Name: "+ resultSet.getString(1) + ", Age: "+ resultSet.getInt(2) +"" +
                        ", Location: "+ resultSet.getString(3) + ", Skills: "+ resultSet.getString(4));
            }
            resultSet.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
