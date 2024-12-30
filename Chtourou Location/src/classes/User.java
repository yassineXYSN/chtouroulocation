package classes;

public abstract class User {

    public User(String Name,String LastName ,String Email,String AddressPostal ) {
        this.Name = Name;
        this.LastName = LastName;
        this.Email = Email;
        this.AddressPostal = AddressPostal;

    }
    private static int id;

    public String Name;


    public String LastName;

    public String Email;

    public String AddressPostal;


}