package Jouer;

import Joueur.CartePokemon;
import Joueur.Dresseur;
import Joueur.JoueurOrdinateur;

public class Pokemon {
  public static void main(String[] args) {
    // Initialisation des joueurs
    JoueurOrdinateur joueur1 = new JoueurOrdinateur("Joueur 1");
    Dresseur joueur2 = new Dresseur("Joueur 2");

    // Création du jeu
    Jeu jeu = new Jeu(joueur1, joueur2);

    // Génération de 20 Pokémon pour la pioche du joueur 2 (humain)
    for (int i = 0; i < 20; i++) {
      CartePokemon cartePokemon = jeu.genererPokemon();
      joueur2.ajouterCarteAPioche(cartePokemon);
    }

    // Génération de 21 Pokémon pour la pioche du joueur 1 (ordinateur)
    for (int i = 0; i < 21; i++) {
      CartePokemon cartePokemon = jeu.genererPokemon();
      joueur1.ajouterCarteAPioche(cartePokemon);
    }

    // Chaque joueur pioche 5 cartes
    for (int i = 0; i < 5; i++) {
      joueur1.piocher();
      joueur2.piocher();
    }

    // Assertions pour vérifier que chaque joueur a bien pioché 5 cartes
    assert joueur1.getMain().size() == 5 : "Joueur 1 n'a pas 5 cartes en main";
    assert joueur2.getMain().size() == 5 : "Joueur 2 n'a pas 5 cartes en main";

    // Joueur 1 (ordinateur) joue son tour
    joueur1.jouerTour(jeu);

    // Assertions pour vérifier le déroulement du tour du joueur 1
    assert jeu.getNumeroTour() == 1 : "Le numéro de tour n'est pas correct après le tour de Joueur 1";

    // Joueur 2 (humain) joue son tour
    jeu.jouerTour();

    // Assertions pour vérifier le déroulement du tour du joueur 2
    assert jeu.getNumeroTour() == 2 : "Le numéro de tour n'est pas correct après le tour de Joueur 2";

    // Détecter et gérer la fin du jeu
    if (jeu.estTermine()) {
      System.out.println("Le jeu est terminé.");
    } else {
      System.out.println("Le jeu continue.");
    }

    System.out.println("Tous les tests sont réussis !");
  }
}
