

Monsieur, 

Si vous d�cidez d'effectuer les tests CRUD via les fichiers dans le folder "testDAO" et les fichiers dans le folder "testManyToMany", 
veillez � bien respecter l'ordre des enregistrements dans la Database.
De plus, certaines tables sont contitu�es d'un PK compos�e unique et la table "localexpo" est en relation One To One avec la table
"adresse". Il ne faut donc pas lancer plusieurs fois les test CRUD pour ces tables car deux enregistrements identiques ne peuvent
�tre effectu�s.
De plus, pour que les enregistrements puissent s'effectuer correctement dans ces tables sp�cifiques, il est n�cessaire qu'il y ait
suffisamment d'enregistrements dans les autres tables.
Je tiens � souligner que j'ai effectuer moi-m�me plusieurs les tests compl�tement et tous les enregsitrements ont �t� effectu�s avec
succ�s.
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
Artiste --> Effectuer le test 2 fois (Remarque: j'ai modifi� le toString)
Exposition --> Effectuer le test 2 fois
Newsletter --> Effectuer le test 2 fois
Oeuvre --> Effectuer le test 2 fois
Facture --> Effectuer le test 2 fois
DetailFacture --> Effectuer le test 1 fois!
Reception --> Effectuer le test 1 fois!
Participation --> Effectuer le test 1 fois!
Situation --> Effectuer le test 1 fois!

Bien � vous.
Brieuc Sculier