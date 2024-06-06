package Element;

import java.util.ArrayList;
import java.util.List;

public class Air extends Type {
    public Air(){
        super();
    }

    @Override
    public List<Type> getAvantages(){
        List<Type> m_avantages = new ArrayList<>();
        m_avantages.add(new Terre());
        m_avantages.add(new Plomb());
        return m_avantages;
    }

    @Override
    public Type getType(){
        return new Air();
    }

    public String toString() {return "Element.Air";}
}