package classes;

public class Model extends Make{

    public Model(String ModelName,String MakeName) {
        super(MakeName);
        this.ModelName = ModelName;
    }

    public String ModelName;

}