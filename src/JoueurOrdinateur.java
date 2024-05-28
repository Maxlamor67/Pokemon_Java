import java.util.List;
import java.util.Random;

public class JoueurOrdinateur extends Dresseur {
    public JoueurOrdinateur(String nom) {
        super(nom);
    }

    public void jouerTour(Jeu jeu) {
        placerPokemonsSurTerrain();
        attaquerAdversaire(jeu);
    }

    private void placerPokemonsSurTerrain() {
        while (terrain.size() < 3 && pioche.size() !=0) {
            CartePokemon carte = pioche.remove(0);
            terrain.add(carte);
        }
    }

    private void attaquerAdversaire(Jeu jeu) {
        Dresseur adversaire = jeu.getJoueurAdverse(this);

        for (CartePokemon carteAttaque : terrain) {
            if (!carteAttaque.getADejaAttaque()) {
                CartePokemon carteCible = choisirCible(adversaire.getTerrain());

                if (carteCible != null) {
                    int vieAvantAttaque = carteCible.getVie();
                    carteAttaque.attaquer(carteCible);
                    int degats = vieAvantAttaque - carteCible.getVie();
                    System.out.println(nom + " a attaqué " + carteCible.getNom() + " avec " + carteAttaque.getNom() + ". " +
                            "Il reste " + carteCible.getVie() + " points de vie à " + carteCible.getNom() +
                            " (-" + degats + " points)");

                    if (carteCible.getVie() <= 0) {
                        System.out.println("Le pokemon " + carteCible.getNom() + " a été mis K.O. !");
                        adversaire.defausserPokemon(carteCible);
                        adversaire.getTerrain().remove(carteCible);
                    }

                    carteAttaque.setADejaAttaque(true);
                }
            }
        }

        for (CartePokemon carte : terrain) {
            carte.setADejaAttaque(false);
        }
    }

    private CartePokemon choisirCible(List<CartePokemon> terrainAdversaire) {
        if (terrainAdversaire.isEmpty()) {
            return null;
        }

        Random random = new Random();
        return terrainAdversaire.get(random.nextInt(terrainAdversaire.size()));
    }
}
