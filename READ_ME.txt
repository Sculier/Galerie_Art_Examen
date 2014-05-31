

Monsieur, 

Si vous décidez d'effectuer les tests CRUD via les fichiers dans le folder "testDAO" et les fichiers dans le folder "testManyToMany", 
veillez à bien respecter l'ordre des enregistrements dans la Database.
De plus, certaines tables sont contituées d'un PK composée unique et la table "localexpo" est en relation One To One avec la table
"adresse". Il ne faut donc pas lancer plusieurs fois les test CRUD pour ces tables car deux enregistrements identiques ne peuvent
être effectués.
De plus, pour que les enregistrements puissent s'effectuer correctement dans ces tables spécifiques, il est nécessaire qu'il y ait
suffisamment d'enregistrements dans les autres tables.
Je tiens à souligner que j'ai effectuer moi-même plusieurs les tests complètement et tous les enregsitrements ont été effectués avec
succès.
Veillez donc respecter rigoureusement les tests dans l'ordre suivant:

Pays --> Effectuer le test 2 fois
Adresse --> Effectuer le test 2 fois
Localexpo --> Effectuer le test 1 fois!
Technique --> Effectuer le test 2 fois
Categorie --> Effectuer le test 2 fois
Courant --> Effectuer le test 2 fois
Transport --> Effectuer le test 2 fois
Horaire --> Effectuer le test 2 fois
Client --> Effectuer le test 2 fois
Artiste --> Effectuer le test 2 fois (Remarque: j'ai modifié le toString)
Exposition --> Effectuer le test 2 fois
Newsletter --> Effectuer le test 2 fois
Oeuvre --> Effectuer le test 2 fois
Facture --> Effectuer le test 2 fois
DetailFacture --> Effectuer le test 1 fois!
Reception --> Effectuer le test 1 fois!
Participation --> Effectuer le test 1 fois!
Situation --> Effectuer le test 1 fois!

Bien à vous.
Brieuc Sculier