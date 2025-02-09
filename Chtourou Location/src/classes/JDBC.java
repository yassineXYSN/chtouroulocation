package classes;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
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

    public User LogIn(String email, String password) {
        try {
            User u;
            Statement stmt = this.con.createStatement();

            ResultSet r = stmt.executeQuery("SELECT * FROM admins WHERE email = '" + email + "' AND mdp = '" + password + "'");
            if (r.next()) {
                u = new Admin(r.getString("name"), r.getString("lastname"), r.getString("email"), r.getString("AddressPostal"));
                r.close();
                return u;
            }
            r.close();

            ResultSet r2 = stmt.executeQuery("SELECT * FROM clients WHERE email = '" + email + "' AND mdp = '" + password + "'");
            if (r2.next()) {
                u = new Client(r2.getString("name"), r2.getString("lastname"), r2.getString("email"), r2.getString("AddressPostal"),0);
                r2.close();
                return u;
            }
            r2.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Boolean ClientExists(String email, String password) {
        try {
            Statement stmt = this.con.createStatement();
            ResultSet r2 = stmt.executeQuery("SELECT * FROM clients WHERE email = '" + email + "' AND mdp = '" + password + "'");
            if (r2.next()) {
                return Boolean.TRUE;
            }
            r2.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return Boolean.FALSE;
    }

    public int ClientID(String email, String password) {
        try {
            Statement stmt = this.con.createStatement();
            ResultSet r2 = stmt.executeQuery("SELECT * FROM clients WHERE email = '" + email + "' AND mdp = '" + password + "'");
            if (r2.next()) {
                return r2.getInt("idusers");
            }
            r2.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
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
                JOptionPane.showMessageDialog(null, "Car added Successfully");
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

    public void UpdateCarAndUser(Car car, Client client, Date startdate, Date enddate,String pass){
        String updateSqlcar = "UPDATE cars SET availability = 0 WHERE idcars = ?";
        try (PreparedStatement pstmt = this.con.prepareStatement(updateSqlcar)) {
            pstmt.setInt(1, car.id);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                String updateSqluser = "UPDATE clients SET nbrents = nbrents + 1 WHERE email = ?";
                try (PreparedStatement pstmt2 = this.con.prepareStatement(updateSqluser)) {
                    pstmt2.setString(1, client.Email);

                    int rowsUpdated2 = pstmt2.executeUpdate();
                    if (rowsUpdated2 > 0) {
                        String updateSqlRents = "INSERT INTO rents (iduser, idcar, startdate, enddate) VALUES (?, ?, ?, ?)";
                        try (PreparedStatement pstmt3 = con.prepareStatement(updateSqlRents)) {
                            pstmt3.setInt(1, ClientID(client.Email, pass)); // Set iduser
                            pstmt3.setInt(2, car.id);                      // Set idcar
                            pstmt3.setDate(3, new java.sql.Date(startdate.getTime())); // Set rent_date
                            pstmt3.setDate(4, new java.sql.Date(enddate.getTime()));   // Set return_date

                            int rowsInserted = pstmt3.executeUpdate(); // Execute the insert statement
                            if (rowsInserted > 0) {
                                JOptionPane.showMessageDialog(null, "Car Rented Successfully");
                            } else {
                                JOptionPane.showMessageDialog(null, "Failed to Rent Car. No rows affected.");
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateRents() {
        String selectSql = "SELECT idcar FROM rents WHERE CURDATE()+1 < enddate";
        String deleteSql = "DELETE FROM rents WHERE CURDATE()+1 < enddate";

        try {
            // Retrieve and print idcar values
            try (PreparedStatement selectStmt = this.con.prepareStatement(selectSql)) {
                try (ResultSet resultSet = selectStmt.executeQuery()) {
                    System.out.println("Cars to be deleted:");
                    while (resultSet.next()) {
                        int idcar = resultSet.getInt("idcar");
                        String updateSqlcar = "UPDATE cars SET availability = 1 WHERE idcars = ?";
                        try (PreparedStatement pstmt = this.con.prepareStatement(updateSqlcar)) {
                            pstmt.setInt(1, idcar);
                            int rowsUpdated = pstmt.executeUpdate();
                            if (rowsUpdated > 0) {
                                System.out.println("Car available again "+idcar);
                            }
                        }catch(SQLException e){System.out.println(e);}
                    }
                }
            }

            // Perform the delete operation
            try (PreparedStatement deleteStmt = this.con.prepareStatement(deleteSql)) {
                int rowsDeleted = deleteStmt.executeUpdate();
                System.out.println(rowsDeleted + " rows deleted.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Car> getRentedCars(Client c){
        List<Car> cars = new ArrayList<>();
        int idclient = this.getClientIdByEmail(c.Email);
        List<Integer> caridlist = getCarIdsRentedByUser(idclient);
        try{
            Statement stmt = this.con.createStatement();
            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM cars WHERE idcars IN (");
            for (int i = 0; i < caridlist.size(); i++) {
                queryBuilder.append(caridlist.get(i));
                if (i < caridlist.size() - 1) {
                    queryBuilder.append(", ");
                }
            }
            queryBuilder.append(")");
            ResultSet r = stmt.executeQuery(queryBuilder.toString());
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

    int getClientIdByEmail(String Email){
        int clientId = -1; // Default to -1 if no client is found
        String sql = "SELECT idusers FROM clients WHERE email = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, Email);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    clientId = rs.getInt("idusers");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientId;
    }
    public int getNBRentsByEmail(String email){
        int clientId = 0; // Default to -1 if no client is found
        String sql = "SELECT nbrents FROM clients WHERE email = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, email);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    clientId = rs.getInt("nbrents");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientId;
    }
    public List<Integer> getCarIdsRentedByUser(int userId) {
        List<Integer> rentedCarIds = new ArrayList<>();
        String sql = "SELECT idcar FROM rents WHERE iduser = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, userId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    rentedCarIds.add(rs.getInt("idcar"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rentedCarIds;
    }
}


