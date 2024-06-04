Depuis le début du projet, plusieurs changements significatifs ont été apportés au code et à son organisation. Ces changements visent à améliorer la clarté, la modularité et l'extensibilité du code, ainsi qu'à introduire de nouvelles fonctionnalités.

Ajout de la classe Joueur.JoueurOrdinateur:
Pour compléter le modèle du jeu, la classe Joueur.JoueurOrdinateur a été ajoutée. Cette classe représente un joueur contrôlé par l'ordinateur et hérite de la classe Joueur.Dresseur.

Mise à jour des méthodes dans les classes existantes:
Les méthodes existantes ont été mises à jour pour refléter les nouveaux besoins du jeu. Par exemple, la méthode jouerTour dans la classe Joueur.JoueurOrdinateur a été ajoutée pour simuler le comportement du joueur ordinateur lors d'un tour de jeu.

Ajout de la classe abstraite Element.Type et de ses sous-classes:
Pour gérer les types de cartes Pokémon et leurs avantages, la classe abstraite Element.Type a été introduite. Les sous-classes Element.Air, Element.Eau, Element.Feu et Element.Terre ont été créées pour représenter différents types de cartes Pokémon et implémenter leurs avantages respectifs.

Modification des relations dans le diagramme UML:
Les relations entre les classes ont été revues pour mieux représenter les interactions entre les différents composants du jeu. Par exemple, une relation d'héritage entre Joueur.Dresseur et Joueur.JoueurOrdinateur a été ajoutée pour indiquer que Joueur.JoueurOrdinateur est une sous-classe de Joueur.Dresseur.

Organisation des fonctions du code:
Les fonctions du code ont été organisées de manière à faciliter la compréhension et la maintenance du code. Les méthodes de la classe Jouer.Jeu gèrent la logique du jeu, celles de la classe Joueur.Dresseur gèrent les actions des joueurs, et celles de la classe Joueur.CartePokemon gèrent les propriétés et les actions des cartes Pokémon.

Ajout de commentaires et de documentation:
Des commentaires ont été ajoutés dans le code pour expliquer le fonctionnement des différentes parties du jeu. De plus, une documentation a été rédigée pour expliquer l'organisation du code et les choix de conception.

Pour la prochaine semaine nous allons revoir notre façon de jouer un tour pour que le joueur ordinateur puisse attaquer et pour qu'il y ait plus de clarté