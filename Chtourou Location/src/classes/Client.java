package classes;

public class Client extends User {

    public Client(String Name,String LastName,String Email,String AddressPostal,int nbrents) {
        super(Name,LastName,Email,AddressPostal);
        this.NbRents = nbrents;
    }

    public int NbRents;

    public void GetClients() {
        // TODO implement here
    }
}