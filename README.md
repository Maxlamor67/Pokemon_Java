# POOkemon Project

On souhaite développer une application pour un nouveau un jeu Pokémon.
C'est un jeu à deux joueurs où les dresseurs s'affrontent en plaçant sur leur terrain différents Pokémons.

## Organisation

- Travail en binôme au sein d'un même groupe de TP
- Durée : 5 semaines
- Nombre de séances : 20h encadrées + 8h tutorées + travail non-encadré
- Évaluation : rendu final, régularité des rendus, soutenance
- Sujet dévoilé en deux phases :
  - la phase 1 sur les deux premières semaines
  - la phase 2 à partir du **Lundi 20 mai**.

## Calendrier

- Lundi 22 avril : début du projet
- Lundi 20 mai : début de la phase 2
- Dimanche 9 Juin à minuit : rendu final
- Chaque dimanche à minuit (hors vacances scolaires) : rendu hebdomadaire.

## Le jeu

Il s'agit d'un jeu avec un joueur humain face à l'ordinateur, qui se joue au tour par tour.

### But du jeu

Le gagnant est celui qui parvient à éliminer tous les Pokémons de son adversaire.

### Composition du jeu

Chaque joueur a à sa disposition :

- un terrain pouvant accueillir 3 Pokémons,
- une main de 5 Pokémons maximum,
- une pioche de 20 ou 21 Pokémons (lire partie Mise en place du jeu),
- une défausse contenant les Pokémons éliminés.


### Mise en place du jeu

- Le premier joueur à jouer est choisi aléatoirement.
- Le premier joueur possède une pioche de 20 Pokémons, le second de 21 Pokémons.
- Chaque joueur pioche 5 Pokémons de sa pioche.
- Le premier joueur place 3 Pokémons de sa main sur son terrain face visible, puis le second joueur en fait de même.
- Le premier joueur commence à jouer.

### Déroulement d'un tour

Les joueurs jouent à tour de rôle.

- Au début de son tour, le joueur pioche des Pokémons jusqu'à en avoir 5 en main ou que la pioche soit vide.
- Pour chaque emplacement de Pokémon vide sur le terrain, il place un Pokémon de sa main face visible.
- Il peut ensuite jouer chacun des Pokémons de son terrain dans l'ordre de son choix.
- Chaque Pokémon attaque un Pokémon de son choix du terrain adverse dont le nombre de points de vie est diminué de la valeur de l'attaque du Pokémon joué.
- Si à l'issue d'une attaque un Pokémon du terrain adverse n'a plus de points de vie, il est placé dans la défausse de l'adversaire.

##### Le joueur géré par l'ordinateur

L'ordinateur suivra les règles suivantes :

- lorsqu'il place des Pokémons sur le terrain, il prend le premier Pokémon de sa main.
- pour attaquer, il joue toujours dans le même ordre les Pokémons de son terrain.

Un Pokémon joué par l'ordinateur :

- s'attaquera en priorité à un Pokémon dont l'affinité l'avantagera
- si cela ne suffit, il attaquera le Pokémon du joueur humain possédant le moins de points de vie.
- si cela ne suffit, il attaquera l'un des Pokémon possédant le moins de points de vie au hasard.


### Les Pokémons

Chaque Pokémon possède, un nom, un nombre de points de vie, une valeur d'attaque et une affinité avec un élément.

##### Génération procédurale des Pokémons

À chaque partie, les Pokémons de chaque pioche sont générées selon l'algorithme suivant :

- leur nom est tiré aléatoirement parmi une liste de noms fixe, mais deux Pokémons ne peuvent pas avoir le même nom,
- leur nombre de points de vie est un multiple de 10, compris entre 100 et 200 et déterminé aléatoirement
- leur valeur d'attaque est un multiple de 10 compris entre 10 et 40 et déterminé aléatoirement
- leur affinité est tiré aléatoirement parmi la terre, l'eau, le feu et l'air.

##### Les éléments

Il y a 4 éléments : la terre, l'eau, le feu et l'air.

- Chaque élément a un avantage sur un autre élément : la terre sur l'eau, l'eau sur le feu, le feu sur l'air et l'air sur la terre.
- Lorsqu'un Pokémon attaque un Pokémon sur lequel son affinité a un avantage, son attaque est augmentée de 10.
- Lorsqu'un Pokémon attaque un Pokémon sur lequel son affinité a un désavantage, son attaque est diminuée de 10.

## L'application

L'application doit être réalisée en Java et proposer une interface textuelle.
Elle doit permettre de visualiser les Pokémons sur les terrains et ceux en main du joueur humain (mais pas celles de l'ordinateur).
Elle doit également indiquer clairement quelles sont les actions possibles et les entrées clavier correspondantes pour les effectuer.
Au tour de l'ordinateur, ses actions seront affichées.

##### Exemple d'affichage

```console
********************************************************************************
Tour 3:
                                    Joueur 2

********************************************************************************

                                    Joueur 1

pioche: 15 pokemons
defausse: 0 pokemons
  *--------------------*    *--------------------*    *--------------------*  
  | Attaque: 20        |    | Attaque: 40        |    | Attaque: 20        |  
  | Vie: 50/120        |    | Vie: 50/150        |    | Vie: 50/130        |  
  | Affinite : Element.Air     |    | Affinite : Element.Air     |    | Affinite : Element.Feu     |  
  |     Herbizarre     |    |     Florizarre     |    |     Salamèche      |  
  *--------------------*    *--------------------*    *--------------------*  

----------------------------------------------------------------------------------------------------

  *--------------------*    *--------------------*    *--------------------*  
  |     Dracaufeu      |    |     Bulbizarre     |    |      Pikachu       |  
  | Affinite : Element.Feu     |    | Affinite : Element.Eau     |    | Affinite : Element.Terre   |  
  | Vie: 50/180        |    | Vie: 50/150        |    | Vie: 50/100        |  
  | Attaque: 40        |    | Attaque: 30        |    | Attaque: 10        |  
  *--------------------*    *--------------------*    *--------------------*  

pioche: 12 pokemons
defausse: 0 pokemons

En main:
- Reptincel, Element.Feu, Vie: 160, Attaque: 20
- Carapuce, Element.Eau, Vie: 100, Attaque: 40
- Tortank, Element.Eau, Vie: 200, Attaque: 20
- Papilusion, Element.Air, Vie: 120, Attaque: 20
- Roucool, Element.Air, Vie: 150, Attaque: 30

                                    Joueur 2

Quel pokemon souhaitez-vous jouer? (Dracaufeu/Bulbizarre/Pikachu):
```

Il n'est pas nécessaire de reproduire le visuel tel quel mais toutes les informations doivent être présentes.

##### Fin du jeu

À la fin du jeu, un écran affiche "Vous avez perdu" ou "Vous avez gagné" et indique le nombre de Pokémons restants au gagnant.

## Rendus

Votre projet doit être un fork de ce dépôt dans un groupe ayant pour nom `<nom_etudiant_1>-<nom_etudiant_2>`.
Votre enseignant en TP et le responsable du module doivent être ajoutés comme Reporter à votre projet.

Il y a un rendu par semaine à produire (la régularité des rendus sera notée) à déposer au plus tard le dimanche soir à minuit.
Une branche doit être créée pour chaque rendu avec pour nom `Rendu<numéro-rendu>`.
Le dernier rendu sera évalué en tant que rendu final.

Chaque rendu devra contenir :

- un programme qui compile dont les sources sont dans le répertoire `src/`,
- un diagramme de classes à jour placé dans le répertoire `uml/` ayant pour nom `semaine<numero>.puml`,
- un rapport dans le fichier `rapport.md` indiquant vos choix de conception actuels, quelles fonctionnalités ont été implémentées, les difficultés rencontrées et ce que vous planifiez de faire pour le rendu suivant.

La structure du dépôt git sera la suivante :
```bash
.
├── README.md
├── rapports/
    ├── semaine1.md
    ├── ...
├── .gitignore
├── src/
    ├── ...
├── uml/
    ├── semaine1.puml
    ├──...
```

## Planning indicatif
- rendu 1 : affichage permettant seulement d'afficher le plateau des Pokémons (terrain et main)
- rendu 2 : le jeu est jouable : les Pokémons peuvent s'attaquer, il est possible de piocher des Pokémons et de les placer sur le terrain.


## Quelques consignes

- Vous veillerez à respecter les [P21 Guidelines](https://moodle.unistra.fr/pluginfile.php/2390971/mod_resource/content/2/p21_guidelines.pdf)
- Vous veillerez à la bonne conception du code : le code doit être lisible,  facile à corriger, facile à réutiliser, facile à modifier et facile à étendre.

## Quelques conseils

- N'essayez pas d'implémenter toutes les fonctionnalités en une seule fois. Commencez par un programme simple mais fonctionnel et intégrez progressivement les fonctionnalités.
- Faites des commits très réguliers sur vos branches de travail, dès que vous avez intégré une nouvelle fonctionnalité par exemple.
- Concevez votre code de façon à ce qu'il soit facile de le modifier et d'y ajouter de nouvelles fonctionnalités, notamment en prévision de la phase 2.
- La qualité de la conception et du code produit est plus importante que le nombre de fonctionnalités intégrées.
