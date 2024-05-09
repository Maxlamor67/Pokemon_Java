<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CartePokemon {
    private String nom;
    private Type affinite;
=======
public class CartePokemon {
    private String nom;
    private String affinite;
>>>>>>> e89514b0096bab0428b64fe29eedc0ec936b3eb5
    private int vie;
    private int vieMax;
    private int attaque;

<<<<<<< HEAD
    public CartePokemon(String nom, Type affinite, int vie, int vieMax, int attaque) {
=======
    public CartePokemon(String nom, String affinite, int vie, int vieMax, int attaque) {
>>>>>>> e89514b0096bab0428b64fe29eedc0ec936b3eb5
        this.nom = nom;
        this.affinite = affinite;
        this.vie = vie;
        this.vieMax = vieMax;
        this.attaque = attaque;
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

<<<<<<< HEAD
    public Type getAffinite() {
=======
    public String getAffinite() {
>>>>>>> e89514b0096bab0428b64fe29eedc0ec936b3eb5
        return affinite;
    }

    public void attaquer(CartePokemon pokemonCible) {
<<<<<<< HEAD
        if (pokemonCible.getAffinite().equals(this.getAffinite().getAvantage())){
            pokemonCible.setVie(pokemonCible.getVie() - this.attaque - 10);
            System.out.println(this.nom + " a infligé " + (this.attaque + 10) + " dégâts à " + pokemonCible.nom);
        } else if (this.getAffinite().equals(pokemonCible.getAffinite().getAvantage())) {
            pokemonCible.setVie(pokemonCible.getVie() - this.attaque + 10);
            System.out.println(this.nom + " a infligé " + (this.attaque - 10) + " dégâts à " + pokemonCible.nom);
        }
        else {
            pokemonCible.setVie(pokemonCible.getVie() - this.attaque);
            System.out.println(this.nom + " a infligé " + this.attaque + " dégâts à " + pokemonCible.nom);
        }
=======
        pokemonCible.setVie(pokemonCible.getVie() - this.attaque);
        System.out.println(this.nom + " a infligé " + this.attaque + " dégâts à " + pokemonCible.nom);
>>>>>>> e89514b0096bab0428b64fe29eedc0ec936b3eb5
    }


    public void afficherCarte() {
        System.out.println("  *--------------------*");
        System.out.printf("  | Attaque: %-2d       |\n", attaque);
        System.out.printf("  | Vie: %-2d/%-3d     |\n", vie, vieMax);
<<<<<<< HEAD
        System.out.printf("  | Affinite : %-6s   |\n", affinite.getClass().getSimpleName());
=======
        System.out.printf("  | Affinite : %-6s   |\n", affinite);
>>>>>>> e89514b0096bab0428b64fe29eedc0ec936b3eb5
        System.out.printf("  |     %-10s          |\n", nom);
        System.out.println("  *--------------------*");
    }
}
