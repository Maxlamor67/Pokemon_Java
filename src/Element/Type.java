package Element;

import java.util.List;

public abstract class Type {
    public Type() {}

    public abstract List<Type> getAvantages();
    public abstract Type getType();
    public abstract String toString();

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Type)) {
            return false;
        }

        Type otherType = (Type) other;

        // Compare les classes des deux objets Element.Type
        return this.getClass().equals(otherType.getClass());
    }
}