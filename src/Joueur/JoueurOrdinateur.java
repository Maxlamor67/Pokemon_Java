package Joueur;

import Pouvoir.*;
import Jouer.*;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class JoueurOrdinateur extends Dresseur {
    public JoueurOrdinateur(String nom) {
        super(nom);
    }

    public void jouerTour(Jeu jeu) {
        placerPokemonsSurTerrain();
        utilisationDesPouvoirs(jeu);
        attaquerAdversaire(jeu);
    }

    private void utilisationDesPouvoirs(Jeu jeu) {
        Dresseur adversaire = jeu.getJoueurAdverse(this);
        CartePokemon carteCible = null;
        for (CartePokemon carte : this.getTerrain()) {
            if (carte.getPouvoir() != null && carte.getPouvoir().nbrUtilisation() > 0) {
                Pouvoir pouvoir = carte.getPouvoir();
                if(Objects.equals(pouvoir.toString(), "Pouvoir.Pouvoir.SoinSimple") || Objects.equals(pouvoir.toString(), "Pouvoir.Pouvoir.SoinTotal")){
                    for(CartePokemon carte2 : this.getTerrain()) {
                        if(carte2.getVie() < carte2.getVie()/2 ){
                            carteCible = carte2;
                            break;
                        }
                    }
                }
                else {
                    switch (pouvoir.getTypePouvoir()) {
                        case ALLIE :
                            for(CartePokemon carte2 : this.getTerrain()) {
                                if(!Objects.equals(carte2.getNom(), carte.getNom())){
                                    carteCible = carte2;
                                    break;
                                }
                            }
                        case ENNEMI:
                            carteCible = choisirCible(adversaire.getTerrain());
                            break;
                        case TOUTCAMP:
                            carteCible = carte;
                            break;
                        case TOUS:
                            carteCible = choisirCible(adversaire.getTerrain());
                            break;
                    }
                }
                if (carteCible != null) {
                    pouvoir.utiliserPouvoir(carteCible);
                    System.out.println("Le pouvoir a été utilisé avec succès sur " + carteCible.getNom() + ".");
                }
            }
        }
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

