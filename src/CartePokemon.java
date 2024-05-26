import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//this.nomsPokemon = new ArrayList<>(Arrays.asList("Herbizarre", "Florizarre", "Salamèche", "Dracaufeu", "Bulbizarre", "Pikachu",
//                "Arceus", "Keunotor", "Entei", "Sharpedo", "Lippoutou", "Fulguris",
//                "Carchacrok", "Elektor", "Philaly", "Raiku", "Zeraora", "Poussacha",
//                "Simiabraz", "Roigada", "Tengalice", "Metamorph", "Malamandre", "Altaria",
//                "Galifeu", "Mustéflott", "Cacnea", "Laggron", "Flambusard", "Suicune",
//                "Psykokwak", "Tiplouf", "Démolosse", "Pingoléon", "Aéroptéryx", "Manaphy",
//                "Scorplane", "Cizayox", "Yveltal", "Mewtwo","Giratina"));
public class CartePokemon {
    private String nom;
    private Type affinite;
    private Pouvoir pouvoir;
    private int vie;
    private int vieMax;
    private int attaque;
    private int resistance;

    private boolean aDejaAttaque;



    public CartePokemon(String nom, Type affinite, Pouvoir pouvoir, int vie, int vieMax, int attaque) {
        this.nom = nom;
        this.affinite = affinite;
        this.pouvoir = pouvoir;
        this.vie = vie;
        this.vieMax = vieMax;
        this.attaque = attaque;
        this.resistance = 0;
        this.aDejaAttaque = false; // Le Pokémon n'a pas encore attaqué au début du tour
    }

    public String getNom() {
        return nom;
    }

    public int getVieMax() {
        return vieMax;
    }

    public int getAttaque() {
        return attaque;
    }

    public int getVie() {
        return vie;
    }

    public void setVie(int vie) {
        this.vie = Math.max(0, vie); // Assure que la vie ne peut pas être négative
    }

    public Type getAffinite() {
        return affinite;
    }

    public Pouvoir getPouvoir() {return pouvoir;}

    public int getResistance() {return resistance;}

    public void setResistance(int resistance) {this.resistance = resistance;}

    public boolean getADejaAttaque() {
        return this.aDejaAttaque;
    }

    public void resetAttaque() {
        this.aDejaAttaque = false; // Réinitialiser l'attribut pour le prochain tour
    }


    public void attaquer(CartePokemon pokemonCible) {
            if (pokemonCible.getAffinite().equals(this.getAffinite().getAvantage())) {
                pokemonCible.setVie(pokemonCible.getVie() - this.attaque + pokemonCible.resistance - 10);
                System.out.println(this.nom + " a infligé " + (this.attaque - pokemonCible.resistance + 10) + " dégâts à " + pokemonCible.nom);
            } else if (this.getAffinite().equals(pokemonCible.getAffinite().getAvantage())) {
                pokemonCible.setVie(pokemonCible.getVie() - this.attaque + pokemonCible.resistance + 10);
                System.out.println(this.nom + " a infligé " + (this.attaque - pokemonCible.resistance - 10) + " dégâts à " + pokemonCible.nom);
            } else {
                pokemonCible.setVie(pokemonCible.getVie() - this.attaque + pokemonCible.resistance);
                System.out.println(this.nom + " a infligé " + (this.attaque-pokemonCible.resistance) + " dégâts à " + pokemonCible.nom);
            }

    }

    public void setADejaAttaque(boolean aDejaAttaque) {
        this.aDejaAttaque = aDejaAttaque;
    }


    public void afficherCarte() {
        System.out.println("  *--------------------*");
        System.out.printf("  | Attaque: %-2d        |\n", attaque);
        System.out.printf("  | Vie: %-2d/%-3d       |\n", vie, vieMax);
        System.out.printf("  | Affinite : %-6s  |\n", affinite.getClass().getSimpleName());
        if(pouvoir==null) {
            System.out.printf("  | Pouvoir : None  |\n");
        }
        else {
            System.out.printf("  | Pouvoir : %-6s  |\n", pouvoir);
        }
        System.out.printf("  |     %-10s     |\n", nom);
        System.out.println("  *--------------------*");
    }
}


