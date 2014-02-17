#Teknisk Dokumentation

##Package controllers:

	###FormatterController:
	Klassen tar in en parameter av typen String. Denna parameter är de antal varv som tävlingen ska innefatta.
	FormatterController finns till för att skilja vyn från modellen, den tar därför endast emot String som parameter.

	###RegisterController:
	Kommunikationslänk mellan vy och modell. Tar endast emot String som parametrar i samtliga metoder.

##Package main:

	###Main:
	Instansierar programmet.
	Tar emot en String parameter från Java.Swings.utils inputDialog som bestämmer huruvida det är registrerings- eller formateringsprogrammet som ska köras.

##Package models:

	###Time:
	Består av ett int attribut som representerar en tid i sekunder från tiden 00.00.00.
	Konstruktorn tar emot en String parameter på formatet HH.MM.SS.
	Har metoder för jämföring av två tider samt en toString.

	###Lap:
	Modellering av ett varv eller en etapp.
	Består av två Time-objekt där den ena representerar starttiden för varvet och den andra sluttiden.
	Har metoder för att sätta och hämta start respektive sluttid.
	
	###Participant:
	Modellering av en deltagare.
	När en participant skapas anges en int parameter i konstruktorn som blir deltagarens tävlingsId.
	I participant finns också möjlighet att lägga till ett namn.
	Klassen innehåller också metoder för jämföring samt getters för namn och id, samt setter för namn.
	
	###Race:
	Modellering av en Participants tävlingsinsats.
	Består av flera Laps.
	Klasser har också getters och setters för start och sluttid, men även för enskilda Laps.
	Har också metod för totaltiden för alla Laps i Racet.
	
	###RaceEvent:
	Modellering av ett tävlingstilfälle med flera tävlare.
	Konstruktorn tar emot en int parameter som anger hur många varv som tävligen består av.
	Består av en samling Participants med deras tillhörande Race.
	Har metoder för att hantera tillägg av Participants och tider.
	
##Package sorter:
	
	###Sorter: 
	Sortern är en abstrakt klass med subklasser (SortFinishTime, SortName, SortStartTime) enligt template method. Samtliga subklasser har en separat skuggning av metoden addInfo(int, Iterator<String>, RaceEvent).
	Det som skiljer sig åt subklasserna emellan är vilken metod i klassen RaceEvent som anropas för att spara information om Times och Participants på rätt plats.
	Man anropar funktionen insertInfo som har tre parametrar (String filePath, String column, RaceEvent raceEvent) där filePath är URL-adress till filen som ska läsas in. 
	Column är namnet på motsvarande kolumn som man vill läsa från och RaceEvent är det RaceEvent som informationen ska sparas till.

    ###SortFinishTime:
    SortFinishTime kan användas för att läsa flera måltider. För att läsa flera måltider så skickar man in en sträng-vektor till insertInfo istället för en vanlig sträng.


##Package utils:
	
	###Enduro:
	Singeltonklass som kan generera en absolut URL-adress till en mapp i projektet.
	Används för att inte relativa vägar ska användas då det blir fel om samma adress anropas på olika konton.
	
	###FileReader:
	Läser innehållet på den fil vars URL-adress anges i konstruktorn och returnerar en Iterator<String> av innehållet.
	
	###FileWriter:
	Skriver ut innehållet i andra argumentet i konstruktorn (antingen en String eller en Iterator<String>) på platsen (URL-adress) som anges i det första argumentet.

	###Formatter:
	Konstruktorn tar emot ett String argument som är det antal varv som tävlingen ska bestå av.
	Den skapar ett RaceEvent som den sedan lägger in information som ligger i diverse filer. Informationen kan vara namn och tider.
	Klassen har metoder för att generera och returnera resultat på String-format utav den inmatade informationen.
	Har också metoder för att returnera tiden för samtliga varv som en Participant har kört, samt att returnera tiden för de x första varven.
	
##Package views:

	###GUIFormatter:
	Skapar ett användargränssnitt för formattering av informationsfiler innehållande namn och tider.
	När gränssnittet startas ges det möjlighet att ange hur många varv som ingår i den tävling som ska formatteras.
	
	###LoadStartButton:
	Öppnar ett "file chooser"-fönster där en fil med starttider ska väljas.
	
	###LoadFinishButton:
	Öppnar ett "file chooser"-fönster där en fil med sluttider ska väljas.
	
	###LoadNamesButton:
	Öppnar ett "file chooser"-fönster där en fil med namn ska väljas.
	
	###PrintButton:
	Öppnar ett "file chooser"-fönster där platsen för var resultatfilen ska sparas väljs.
	Frågar hur många varv som ska vara med i resultatfilen.
	Skapar en fil på vald plats med en resultatlista baserad på det som laddats in sen tidigare.
	
	###GUIRegister:
	Skapar ett användargränssnitt för registrering av tider med tillhörande tävlingsid.
	Öppnar ett "file chooser"-fönster där önskad plats för sparning av registeringar ska väljas.
	
	###RegisterButton:
	Hämtar innehållet i registeringsfältet från GUIRegister.
	Innehållet från registeringsfälet tillsammans med den aktuella tiden skrivs ut på nästa nya rad på den filplats som valdes i GUIRegisters file chooser.
	Om filen inte existerar så skapas en ny fil vid första registreringen.
	
	
	
	
	