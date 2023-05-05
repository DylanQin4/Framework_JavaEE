<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>index</title>
</head>
<body>
	<h3>Formulaire</h3>
	<form action="save_employer" method="post" enctype="multipart/form-data">
		Id : <input type="number" name="id">
                <br>
		Nom : <input type="text" name="nom">
                <br>
		Date : <input type="date" name="date">
                <br>
                Diplome : 
                Licence<input type="checkbox" name="diplome" value="Licence">
                Bacc<input type="checkbox" name="diplome" value="Bacc">
                Bepc<input type="checkbox" name="diplome" value="Bepc">
                <br>
                Choix :
                1 <input type="checkbox" name="choix" value="1">
                2 <input type="checkbox" name="choix" value="2">
                <br>
                Image :
                <input type="file" name="image">
                <input type="submit" value="Valider">
	</form>
	<br>
	<a href="all_employer">Liste Employer</a>
</body>
</html>