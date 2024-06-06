package Element;

import java.util.ArrayList;
import java.util.List;

public class Ether extends Type {
    public Ether(){
        super();
    }

    @Override
    public List<Type> getAvantages(){
        List<Type> m_avantages = new ArrayList<>();
        m_avantages.add(new Terre());
        m_avantages.add(new Eau());
        m_avantages.add(new Feu());
        m_avantages.add(new Air());
        m_avantages.add(new Plomb());
        m_avantages.add(new Ether());
        return m_avantages;
    }

    @Override
    public Type getType(){
        return new Ether();
    }


    public String toString() {return "Element.Ether";}
}
