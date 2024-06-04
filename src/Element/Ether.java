package Element;

import java.util.ArrayList;
import java.util.List;

public class Ether extends Type {
    public Ether(){
        super();
    }

    @Override
    public List<Type> getAvantages(){
        List<Type> avantages = new ArrayList<>();
        avantages.add(new Terre());
        avantages.add(new Eau());
        avantages.add(new Feu());
        avantages.add(new Air());
        avantages.add(new Plomb());
        avantages.add(new Ether());
        return avantages;
    }

    @Override
    public Type getType(){
        return new Ether();
    }

<<<<<<< HEAD:src/Ether.java
    public String toString() {return "Ether";}

=======
    public String toString() {return "Element.Ether";}
>>>>>>> 48cf3df5d936771020fba00572ccd6c3b7d3b577:src/Element/Ether.java
}
