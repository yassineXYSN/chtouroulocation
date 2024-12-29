package classes;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBC {
    public Connection con;
    public JDBC() {
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/chtouroulocation","root","yassine123Y");
            this.con = con;
        }catch(SQLException e){
            System.out.println(e);
        }
    }

    public User SignIn(String email, String password, String ville, String name, String Lastname){
        try{
            Statement stmt = this.con.createStatement();
            ResultSet r = stmt.executeQuery("Select * from clients where email = '" + email + "'");
            while(r.next()){
                return null;
            }
            String insertQuery = "INSERT INTO clients (name, lastname, email, AddressPostal, mdp) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt2 = this.con.prepareStatement(insertQuery);
            stmt2.setString(1, name);
            stmt2.setString(2, Lastname);
            stmt2.setString(3, email);
            stmt2.setString(4, ville);
            stmt2.setString(5, password);
            int rowsAffected = stmt2.executeUpdate();

            if (rowsAffected > 0) {
                // Optionally, create and return a new User object
                return new Client(name,Lastname,email,ville,0);}
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }

    public User LogIn(String email, String password){
        try{
            Statement stmt = this.con.createStatement();
            ResultSet r = stmt.executeQuery("Select * from admins where email = '" + email + "' and mdp = '" + password + "'");
            while(r.next()){
                User a = new Admin(r.getString("name"),r.getString("lastname"),r.getString("email"),r.getString("AddressPostal"));
                return a;
            }
            ResultSet r2 = stmt.executeQuery("Select * from clients where email = '" + email + "' and mdp = '" + password + "'");
            while(r2.next()){
                User c = new Admin(r.getString("name"),r.getString("lastname"),r.getString("email"),r.getString("AddressPostal"));
                return c;
            }
            r.close();
            r2.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }

    int getMakeByName(String make){
        int idMake = 0;
        String query = "SELECT * FROM makes WHERE makename = ?";
        try (PreparedStatement pstmt = this.con.prepareStatement(query)) {
            pstmt.setString(1, make); // Set the parameter safely
            ResultSet r = pstmt.executeQuery();

            while (r.next()) {
                
                idMake = r.getInt("idmake");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return idMake;
    }

    int getModelByName(String model){
        int idModel = 0;
        String query = "SELECT * FROM models WHERE modelname = ?";
        try (PreparedStatement pstmt = this.con.prepareStatement(query)) {
            pstmt.setString(1, model); // Set the parameter safely
            ResultSet r = pstmt.executeQuery();

            while (r.next()) {

                idModel = r.getInt("idmodel");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return idModel;
    }

    public List<Car> getCarsByMake(String make2){
        List<Car> cars = new ArrayList<>();
        int idmake = this.getMakeByName(make2);
        String query = "SELECT * FROM cars WHERE idmake = ?";
        try (PreparedStatement pstmt = this.con.prepareStatement(query)) {
            pstmt.setString(1, String.valueOf(idmake)); // Set the parameter safely
            ResultSet r = pstmt.executeQuery();

            while (r.next()) {
                int id = r.getInt("idcars");
                int idMake = r.getInt("idmake");
                int idModel = r.getInt("idmodel");
                String color = r.getString("color");
                Float price = r.getFloat("price");
                int age = r.getInt("age");
                boolean availability = r.getBoolean("availability");

                String makeName = null;
                try (PreparedStatement pstmtMake = con.prepareStatement("SELECT makename FROM makes WHERE idmake = ?")) {
                    pstmtMake.setInt(1, idMake);
                    ResultSet make = pstmtMake.executeQuery();
                    if (make.next()) {
                        makeName = make.getString("makename");
                    }
                }

                String modelName = null;
                try (PreparedStatement pstmtModel = con.prepareStatement("SELECT modelname FROM models WHERE idmodel = ?")) {
                    pstmtModel.setInt(1, idModel);
                    ResultSet model = pstmtModel.executeQuery();
                    if (model.next()) {
                        modelName = model.getString("modelname");
                    }
                }

                Model m = new Model(modelName, makeName);
                cars.add(new Car(id,m, age, price, color, availability));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return cars;
    }

    public List<Car> getCarsByAvailability(String avai) {
        boolean a ;
        if (avai.equals("Available")){
            a = true;
        }
        else {
            a = false;
        }
        List<Car> cars = new ArrayList<>();
        String query = "SELECT * FROM cars WHERE availability = ?";
        try (PreparedStatement pstmt = this.con.prepareStatement(query)) {
            pstmt.setBoolean(1, a); // Set the parameter safely
            ResultSet r = pstmt.executeQuery();

            while (r.next()) {
                int id = r.getInt("idcars");
                int idMake = r.getInt("idmake");
                int idModel = r.getInt("idmodel");
                String color = r.getString("color");
                Float price = r.getFloat("price");
                int age = r.getInt("age");
                boolean availability = r.getBoolean("availability");

                String makeName = null;
                try (PreparedStatement pstmtMake = con.prepareStatement("SELECT makename FROM makes WHERE idmake = ?")) {
                    pstmtMake.setInt(1, idMake);
                    ResultSet make = pstmtMake.executeQuery();
                    if (make.next()) {
                        makeName = make.getString("makename");
                    }
                }

                String modelName = null;
                try (PreparedStatement pstmtModel = con.prepareStatement("SELECT modelname FROM models WHERE idmodel = ?")) {
                    pstmtModel.setInt(1, idModel);
                    ResultSet model = pstmtModel.executeQuery();
                    if (model.next()) {
                        modelName = model.getString("modelname");
                    }
                }

                Model m = new Model(modelName, makeName);
                cars.add(new Car(id,m, age, price, color, availability));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return cars;
    }


    public List<String> getMakes() {
        List<String> makes = new ArrayList<>();
        try {
            Statement stmt = this.con.createStatement();
            ResultSet r = stmt.executeQuery("SELECT * FROM makes");
            while (r.next()) {
                String make = r.getString("makename");
                makes.add(make);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return makes;
    }

    public List<String> getModels(String makename) {
        List<String> models = new ArrayList<>();
        try {
            int makeid = getMakeByName(makename);
            Statement stmt = this.con.createStatement();
            ResultSet r = stmt.executeQuery("SELECT * FROM models where idmake = "+makeid);
            while (r.next()) {
                String model = r.getString("modelname");
                models.add(model);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return models;
    }

    public List<Car> getCars(){
        List<Car> cars = new ArrayList<>();
        try{
            Statement stmt = this.con.createStatement();
            ResultSet r = stmt.executeQuery("SELECT * from cars");
            while (r.next()) {
                int id = r.getInt("idcars");
                int idMake = r.getInt("idmake");
                int idModel = r.getInt("idmodel");
                String color = r.getString("color");
                int idCars = r.getInt("idcars");
                Float price = r.getFloat("price");
                int age = r.getInt("age");
                boolean availability = r.getBoolean("availability");

                Statement s2 = con.createStatement();
                ResultSet make = s2.executeQuery("SELECT makename FROM makes WHERE idmake = " + idMake);
                String makeName = null;
                if (make.next()) {
                    makeName = make.getString("makename");
                }

                Statement s3 = con.createStatement();
                ResultSet model = s3.executeQuery("SELECT modelname FROM models WHERE idmodel = " + idModel);
                String modelName = null;
                if (model.next()) {
                    modelName = model.getString("modelname");
                }

                Model m = new Model(modelName,makeName);
                cars.add(new Car(id,m,age,price,color,availability));

                make.close();
                s2.close();
                s3.close();
            }
        }
        catch(SQLException e){System.out.println(e);}
        return cars;
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();

        String adminQuery = "SELECT name, lastname, email, AddressPostal FROM admins";
        String clientQuery = "SELECT * FROM clients";

        try (Statement adminStmt = this.con.createStatement();
             Statement clientStmt = this.con.createStatement();
             ResultSet adminRs = adminStmt.executeQuery(adminQuery);
             ResultSet clientRs = clientStmt.executeQuery(clientQuery)) {

            // Process admin users
            while (adminRs.next()) {
                String name = adminRs.getString("name");
                String lastname = adminRs.getString("lastname");
                String email = adminRs.getString("email");
                String addressPostal = adminRs.getString("AddressPostal");
                users.add(new Admin(name, lastname, email, addressPostal));
            }

            // Process client users
            while (clientRs.next()) {
                String name = clientRs.getString("name");
                String lastname = clientRs.getString("lastname");
                String email = clientRs.getString("email");
                String addressPostal = clientRs.getString("AddressPostal");
                int nbrents = clientRs.getInt("nbrents");
                users.add(new Client(name, lastname, email, addressPostal,nbrents));
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Log the full stack trace for debugging
        }

        return users;
    }

    public void InsertCar(Car car) {
        // Assuming `Car` has fields for Model, color, age, price, and availability
        int make = this.getMakeByName(car.Model.MakeName);
        int model = this.getModelByName(car.Model.ModelName);

        String sql = "INSERT INTO cars (color, age, price, availability, idmake, idmodel) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = this.con.prepareStatement(sql)) {
            pstmt.setString(1, car.Color);
            pstmt.setInt(2, car.Age);
            pstmt.setFloat(3, car.Price);
            pstmt.setBoolean(4, car.availability);
            pstmt.setInt(5, make);
            pstmt.setInt(6, model);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new car was inserted successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Car> getAvailableCars(){
        List<Car> cars = new ArrayList<>();
        try{
            Statement stmt = this.con.createStatement();
            ResultSet r = stmt.executeQuery("SELECT * from cars where availability = true");
            while (r.next()) {
                int id = r.getInt("idcars");
                int idMake = r.getInt("idmake");
                int idModel = r.getInt("idmodel");
                String color = r.getString("color");
                int idCars = r.getInt("idcars");
                Float price = r.getFloat("price");
                int age = r.getInt("age");
                boolean availability = r.getBoolean("availability");

                Statement s2 = con.createStatement();
                ResultSet make = s2.executeQuery("SELECT makename FROM makes WHERE idmake = " + idMake);
                String makeName = null;
                if (make.next()) {
                    makeName = make.getString("makename");
                }

                Statement s3 = con.createStatement();
                ResultSet model = s3.executeQuery("SELECT modelname FROM models WHERE idmodel = " + idModel);
                String modelName = null;
                if (model.next()) {
                    modelName = model.getString("modelname");
                }

                Model m = new Model(modelName,makeName);
                cars.add(new Car(id,m,age,price,color,availability));

                make.close();
                s2.close();
                s3.close();
            }
        }
        catch(SQLException e){System.out.println(e);}
        return cars;
    }

    public void UpdateCarAndUser(Car car, Client client){
        String updateSqlcar = "UPDATE cars SET availability = 0 WHERE idcars = ?";
        try (PreparedStatement pstmt = this.con.prepareStatement(updateSqlcar)) {
            pstmt.setInt(1, car.id);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Succesful");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String updateSqluser = "UPDATE clients SET nbrents = nbrents + 1 WHERE email = ?";
        try (PreparedStatement pstmt = this.con.prepareStatement(updateSqluser)) {
            pstmt.setString(1, client.Email);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Succesful");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Car> getAvailableCarsByMake(String make2){
        List<Car> cars = new ArrayList<>();
        int idmake = this.getMakeByName(make2);
        String query = "SELECT * FROM cars WHERE idmake = ? and availability = true";
        try (PreparedStatement pstmt = this.con.prepareStatement(query)) {
            pstmt.setString(1, String.valueOf(idmake)); // Set the parameter safely
            ResultSet r = pstmt.executeQuery();

            while (r.next()) {
                int id = r.getInt("idcars");
                int idMake = r.getInt("idmake");
                int idModel = r.getInt("idmodel");
                String color = r.getString("color");
                Float price = r.getFloat("price");
                int age = r.getInt("age");
                boolean availability = r.getBoolean("availability");

                String makeName = null;
                try (PreparedStatement pstmtMake = con.prepareStatement("SELECT makename FROM makes WHERE idmake = ?")) {
                    pstmtMake.setInt(1, idMake);
                    ResultSet make = pstmtMake.executeQuery();
                    if (make.next()) {
                        makeName = make.getString("makename");
                    }
                }

                String modelName = null;
                try (PreparedStatement pstmtModel = con.prepareStatement("SELECT modelname FROM models WHERE idmodel = ?")) {
                    pstmtModel.setInt(1, idModel);
                    ResultSet model = pstmtModel.executeQuery();
                    if (model.next()) {
                        modelName = model.getString("modelname");
                    }
                }

                Model m = new Model(modelName, makeName);
                cars.add(new Car(id,m, age, price, color, availability));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return cars;
    }

}
