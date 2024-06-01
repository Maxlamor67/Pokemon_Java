import java.util.ArrayList;
import java.util.List;

public class Plomb extends Type {
    public Plomb(){
        super();
    }

    @Override
    public List<Type> getAvantages(){
        return new ArrayList<>(); // No advantage
    }

    @Override
    public Type getType(){
        return new Plomb();
    }

    public String toString() {return "Plomb";}
}