public abstract class Type {
    public Type(){};

    public abstract Type getAvantage();
    public abstract Type getType();
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Type)) {
            return false;
        }

        Type otherType = (Type) other;

        // Compare les classes des deux objets Type
        return this.getClass().equals(otherType.getClass());
    }
}
