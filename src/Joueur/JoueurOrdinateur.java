package Joueur;

import Pouvoir.*;
import Jouer.*;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import Pouvoir.TypePouvoir;

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
        Dresseur joueur1 = jeu.getJoueur(this);

        for (CartePokemon carte : this.getTerrain()) {
            if (carte.getPouvoir() != null && carte.getPouvoir().nbrUtilisation() > 0) {
                Pouvoir pouvoir = carte.getPouvoir();

                // Vérifier s'il y a des Pokémon adverses devant pour utiliser le pouvoir
                boolean adversaireDevant = (adversaire.getTerrain().size() <= 3 && adversaire.getTerrain().size()!=0);

                if (adversaireDevant) {
                    CartePokemon carteCible = null;

                    if (Objects.equals(pouvoir.toString(), "SoinSimple") ) {
                        for (CartePokemon carte2 : joueur1.getTerrain()) {
                            if (carte2.getVie()  < 70) {
                                carteCible = carte2;
                                break;
                            }
                        }
                    }
                    else if (Objects.equals(pouvoir.toString(), "SoinTotal")) {
                        for (CartePokemon carte2 : joueur1.getTerrain()) {
                            if (carte2.getVie() < 70) {
                                carteCible = carte2;
                                break;
                            }
                        }
                    }else if (Objects.equals(pouvoir.toString(), "Resistance")) {
                        for (CartePokemon carte2 : joueur1.getTerrain()) {
                            if (Objects.equals(carte2.getNom(), carte.getNom())) {
                                carteCible = carte2;
                                break;
                            }
                        }
                    } else if (Objects.equals(pouvoir.toString(), "FerveurGuerriere")) {
                        for (CartePokemon carte2 : joueur1.getTerrain()) {
                            if (Objects.equals(carte2.getNom(), carte.getNom())) {
                                carteCible = carte2;
                                break;
                            }
                        }
                    } else if (Objects.equals(pouvoir.toString(), "Peur")) {
                        carteCible = choisirCible(adversaire.getTerrain());
                    } else if (Objects.equals(pouvoir.toString(), "AffiniteEther")) {
                        for (CartePokemon carte2 : joueur1.getTerrain()) {
                            if (Objects.equals(carte2.getNom(), carte.getNom())) {
                                carteCible = carte2;
                                break;
                            }
                        }
                    }
                    else if (Objects.equals(pouvoir.toString(), "AffinitePlomb")) {
                        for (CartePokemon carte2 : adversaire.getTerrain()) {
                            if (Objects.equals(carte2.getNom(), carte.getNom())) {
                                carteCible = carte2;
                                break;
                            }
                        }
                    }else if (Objects.equals(pouvoir.toString(), "Kamikaze")) {
                        carteCible = choisirCible(adversaire.getTerrain());
                    }

                    if (carteCible != null) {
                        pouvoir.utiliserPouvoir(carteCible);
                        System.out.println("Le pouvoir '" + pouvoir.toString() + "' a été utilisé avec succès par " + carte.getNom() + " sur " + carteCible.getNom() + ".");
                    }
                }
            }
        }
    }



    private void placerPokemonsSurTerrain() {
        while (m_terrain.size() < 3 && m_pioche.size() !=0) {
            CartePokemon carte = m_pioche.remove(0);
            m_terrain.add(carte);
        }
    }

    private void attaquerAdversaire(Jeu jeu) {
        Dresseur adversaire = jeu.getJoueurAdverse(this);

        for (CartePokemon carteAttaque : m_terrain) {
            if (!carteAttaque.getADejaAttaque()) {
                CartePokemon carteCible = choisirCible(adversaire.getTerrain());

                if (carteCible != null) {
                    int vieAvantAttaque = carteCible.getVie();
                    carteAttaque.attaquer(carteCible);
                    int degats = vieAvantAttaque - carteCible.getVie();
                    System.out.println(m_nom + " a attaqué " + carteCible.getNom() + " avec " + carteAttaque.getNom() + ". " +
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

        for (CartePokemon carte : m_terrain) {
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

