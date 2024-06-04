# POOkemon Project

## Phase 2
Les Pokémons ont maintenant des pouvoirs qui décuplent leur puissance.
**Choisissez 8 pouvoirs** dans la liste que vous implémenterez.

### Jouer un pouvoir
Au début de son tour, un joueur peut jouer le pouvoir de chaque Pokémon dans l'ordre de son choix avant que ses Pokémons n'attaquent.

### Attribution des pouvoirs
Au début de la partie, lors de la génération des Pokémons, chaque Pokémon se voit attribuer un ou aucun pouvoir.
Le même pouvoir ne peut se voir attribuer qu'à un seul Pokémon dans tout le jeu.

### Liste des pouvoirs
Les pouvoirs peuvent être à utilisation unique ou être réutilisables à chaque tour.
- **Résistance**, à utilisation unique : le Pokémon choisit un Pokémon de son camp (éventuellement lui-même). Jusqu'à la fin de la partie ou à la mort du Pokémon choisi, à chaque attaque reçue celui-ci subit subit 10 dégâts de moins.
- **Ferveur guerrière**, à utilisation unique : le Pokémon choisit un Pokémon de son camp (éventuellement lui-même). Jusqu'à la fin de la partie ou à la mort du Pokémon choisi, les attaques de celui-ci infligent 10 dégâts de plus.
- **Pouvoir.Peur**, à utilisation unique : le Pokémon choisit un Pokémon du camp adverse. Jusqu'à la fin de la partie ou à la mort du Pokémon choisi, les attaques de celui-ci infligent 10 dégats de moins.
- **Pouvoir.Pouvoir.Berserk**, à utilisation unique : le Pokémon choisit un Pokémon de son camp (éventuellement lui-même). Pour le tour en cours, l'attaque de ce Pokémon est doublée.
- **Intimidation**, à utilisation unique : le Pokémon choisit un Pokémon du camp adverse. Pour le prochain tour de l'adversaire, les dégats infligés par le Pokémon choisi sont réduits de moitié.
- **Soin total**, à utilisation unique : le Pokémon choisit un Pokémon de son camp (éventuellement lui-même). Celui-ci regagne toute sa vie.
- **Soin simple**, utilisable à chaque tour : le Pokémon choisit un Pokémon de son camp (éventuellement lui-même). Celui-ci regagne 30 points de vie (mais ne peut pas dépasser son nombre de points de vie initial).
- **Pouvoir.Pouvoir.Kamikaze**, à utilisation unique : le Pokémon choisit un Pokémon du camp adverse. Les deux Pokémons sont alors éliminés.
- **Affinité Element.Ether**, à utilisation unique : le Pokémon choisit un Pokémon de son camp (éventuellement lui-même). Le Pokémon change son affinité pour une affinité avec l'Element.Ether. L'Element.Ether est un nouvel élément ayant un avantage sur tous les autres.
- **Affinité Element.Plomb**, à utilisation unique : le Pokémon choisit un Pokémon adverse. Le Pokémon change son affinité pour une affinité avec le plomb. Le Element.Plomb est un nouvel élément sur lequel tous les autres éléments ont un avantage.
- **Usurpation**, à utilisation unique : le Pokémon choisit un Pokémon (dans son camp ou dans le camp adverse). Il gagne alors le pouvoir du Pokémon choisi et le Pokémon choisi perd son pouvoir (fonctionne aussi pour les pouvoirs à utilisation unique déjà utilisés).
- **Régénération**, à utilisation unique : le Pokémon choisit un Pokémon de son camp. Si le Pokémon a déjà utilisé un pouvoir à utilisation unique, il peut alors l'utiliser une seconde fois.
- **Soin de zone**, utilisable à chaque tour : chaque Pokémon de son camp regagne 10 points de vie.
- **Empoisonnement**, à utilisation unique: le Pokémon choisit un Pokémon du camp adverse à empoisonner. Au début de chaque tour de l'adversaire, le Pokémon empoisonné perd 10 points de vie.
- **Soin permanent**, à utilisation unique : le Pokémon choisit un Pokémon de son camp (éventuellement lui-même). Celui-ci regagne au début de chaque tour 10 points de vie (mais ne peut pas dépasser son nombre de points de vie initial).
- **Protection**, à utilisation unique : le Pokémon choisit un Pokémon de son camp (éventuellement lui-même). Durant le prochain tour de son adversaire le Pokémon choisi ne subit aucune attaque (mais reste sensible aux pouvoirs des Pokémons).
- **Immunité**, à utilisation unique : le Pokémon choisit un Pokémon de son camp (éventuellement lui-même) et du camp adverse. Le Pokémon choisi dans le camp adverse ne peut plus attaquer le Pokémon choisi dans le camp allié durant toute la partie (mais ce dernier reste sensible aux pouvoirs de tous les Pokémons).
- **Déjà-vu?**, à utilisation unique : le Pokémon choisit un Pokémon de son camp (éventuellement lui-même). Le Pokémon choisi peut jouer une seconde fois lors du tour en cours.
- **Blocage**, à utilisation unique : le Pokémon choisit un Pokémon du camp adverse. Le Pokémon ne peut pas jouer lors du tour suivant de l'adversaire.
- **Nécromancie**, à utilisation unique : le Pokémon choisit un Pokémon mort dans sa défausse. Le Pokémon meurt et est remplacé par le Pokémon choisi dans la défausse.
-**Extension du territoire**, à utilisation unique :  le terrain du joueur gagne un quatrième emplacement sur lequel il peut placer immédiatement un Pokémon de sa main. Lorsque le Pokémon qui a utilisé ce pouvoir meurt, son emplacement est perdu et le terrain possède de nouveau trois emplacements.
-**Manipulation**, utilisable à chaque tour : le joueur place un certain nombre de cartes de sa main dans sa pioche, mélange la pioche et pioche dans sa main le même nombre de cartes.
-**Confusion**, utilisable à chaque tour : le joueur adverse doit défausser toutes les cartes de sa main dans sa pioche, mélanger sa pioche et piocher 5 Pokémons.
-**Triche**, à utilisation unique : le joueur place un certain nombre de sa main dans sa pioche et choisit dans sa pioche le même nombre de cartes. Sa pioche est ensuite mélangée.

## Affichage des pouvoirs
Le nom des pouvoirs doit être affiché pour chaque Pokémon des terrains des deux joueurs ainsi que pour les Pokémons de la main du joueur humain.
Les effets actifs des pouvoirs doivent être également visible.
Le joueur humain doit pouvoir consulter l'effet produit par un pouvoir.

## Test des pouvoirs

Pour chaque pouvoir implémenté, des tests doivent montrer le fonctionnement de celui-ci.

## Planning indicatif

C'est un planning indicatif qui donne simplement une trame de ce qu'il faudrait avoir si on a une progression linéaire. Il n'est pas à respecter impérativement.

- **Semaine 1** : Première version de la conception générale du projet et début de l'implémentation.  
_Fonctionalités_ :
  - un Pokémon est capable d'en attaquer un autre sans gestion des affinités, ni d'une pioche, ni d'une défausse, etc.
- **Semaine 2** : Conception générale finalisée et suite de l'implémentation.  
_Fonctionalités_ :
  - gestion des affinités
  - gestion des mains, pioches et défausses et terrain
- **Semaine 3** : Début d'interface utilisateur avec gestion des entrées clavier  
_Fonctionalités_ :
  - gestion des tours de jeux
- **Semaine 4**: Conception des pouvoirs
  - mise en place du code pour intégrer les pouvoirs
  - implémentation des premiers pouvoirs
- **Semaine 5**: Finalisation du projet
  - préparation de la soutenance
  - ajout des autres pouvoirs
