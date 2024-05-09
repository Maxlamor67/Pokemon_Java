import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CartePokemon {
    private String nom;
    private Type affinite;
    private int vie;
    private int vieMax;
    private int attaque;

    private boolean aDejaAttaque;



    public CartePokemon(String nom, Type affinite, int vie, int vieMax, int attaque) {
        this.nom = nom;
        this.affinite = affinite;
        this.vie = vie;
        this.vieMax = vieMax;
        this.attaque = attaque;
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

    public boolean getADejaAttaque() {
        return this.aDejaAttaque;
    }

    public void resetAttaque() {
        this.aDejaAttaque = false; // Réinitialiser l'attribut pour le prochain tour
    }


    public void attaquer(CartePokemon pokemonCible) {
        if (!this.aDejaAttaque) {// Vérifier que le Pokémon n'a pas encore attaqué pendant le tour en cours
            this.aDejaAttaque = true; // Mettre à jour l'attribut après avoir effectué l'attaque
            if (pokemonCible.getAffinite().equals(this.getAffinite().getType())) {
                pokemonCible.setVie(pokemonCible.getVie() - this.attaque - 10);
                System.out.println(this.nom + " a infligé " + (this.attaque + 10) + " dégâts à " + pokemonCible.nom);
            } else if (this.getAffinite().equals(pokemonCible.getAffinite().getType())) {
                pokemonCible.setVie(pokemonCible.getVie() - this.attaque + 10);
                System.out.println(this.nom + " a infligé " + (this.attaque - 10) + " dégâts à " + pokemonCible.nom);
            } else {
                pokemonCible.setVie(pokemonCible.getVie() - this.attaque);
                System.out.println(this.nom + " a infligé " + this.attaque + " dégâts à " + pokemonCible.nom);
            }
        } else {
            System.out.println("Ce Pokémon a déjà attaqué pendant ce tour !");
        }
    }




    public void afficherCarte() {
        System.out.println("  *--------------------*");
        System.out.printf("  | Attaque: %-2d        |\n", attaque);
        System.out.printf("  | Vie: %-2d/%-3d       |\n", vie, vieMax);
        System.out.printf("  | Affinite : %-6s  |\n", affinite.getClass().getSimpleName());
        System.out.printf("  |     %-10s     |\n", nom);
        System.out.println("  *--------------------*");
    }
}
