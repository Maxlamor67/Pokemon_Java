import java.util.ArrayList;
import java.util.List;

public class Terre extends Type {
    public Terre(){
        super();
    }

    @Override
    public List<Type> getAvantages(){
        List<Type> avantages = new ArrayList<>();
        avantages.add(new Eau());
        avantages.add(new Plomb());
        return avantages;
    }

    @Override
    public Type getType(){
        return new Terre();
    }

    public String toString() {return "Terre";}
}