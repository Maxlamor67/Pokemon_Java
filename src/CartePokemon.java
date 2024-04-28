public class CartePokemon {
    private String nom;
    private String affinite;
    private int vie;

    private int vieMax;
    private int attaque;

    public CartePokemon(String nom, String affinite, int vie,int vieMax ,int attaque) {
        this.nom = nom;
        this.affinite = affinite;
        this.vie = vie;
        this.attaque = attaque;
        this.vieMax = vieMax;
    }

    public String getNom() {
        return nom;
    }

    public  int getVieMax(){
        return vieMax;
    }
    public  int getAttaque(){
        return attaque;
    }
    public int getVie() {
        return vie;
    }

    public void setVie(int vie) {
        this.vie = Math.max(0, vie); // Assure que la vie ne peut pas être négative
    }

    public String getAffinite(){
        return affinite;
    }
    public void attaquer(CartePokemon pokemonCible) {
        pokemonCible.setVie(pokemonCible.getVie() - this.attaque);
    }


    public void afficherCarte() {
            System.out.println("  *--------------------*");
            System.out.printf("  | Attaque: %-2d       |\n", attaque);
            System.out.printf("  | Vie: %-2d/%-3d     |\n", vie, vieMax);
            System.out.printf("  | Affinite : %-6s   |\n", affinite);
            System.out.printf("  |     %-10s          |\n", nom);
            System.out.println("  *--------------------*");
        }



}



