TD 8 - Java, jsoN, Last.fm et MongoDB
---

**Auteurs :** Charlotte Fernandes et Maxime Kubasiak

L'objectif de l'application est de pouvoir **récupérer les informations d'un album ou d'un titre (track)
au format JSON**, via l'API Web du site https://www.last.fm 
et de les **ajouter** ou de les **supprimer** d'une base de données **MongoDB**.

###Lancement
A l'execution du main, vous avez un argument à saisir :
* **fill**, remplira votre base de données avec nos données
* **noFill**, le programme s'executera sans remplir votre base de données

Le répertoire **ressources** contient les fichiers :
- albums.json
- tracks.json

Il contiennent l'export de notre base de données, soit **10 tracks et 8 albums**.
