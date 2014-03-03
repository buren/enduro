#Teknisk Dokumentation

##Package controllers:

	###FormatterController:
	Klassen tar in diverse parametrar som beskriver vilken racetyp som ska köras, samt en relevant begränsning för 
        t.ex. hur många	varv loppet ska bestå av. Den tar även in sökvägar till filer som innehåller: namn, starttider,   
        måltider. FormatterController finns till för att skilja vyn från modellen.

	###RegisterController:
	Kommunikationslänk mellan vy och modell. Tar endast emot String som parametrar i samtliga metoder. Vid       
	instansiering så ges en sökväg till en tempfil.

##Package main:

	###Main:
	Instansierar programmet.
	Tar emot en String parameter från Java.Swings.utils inputDialog som bestämmer huruvida det är registrerings- eller         formateringsprogrammet som ska köras.

##Package models:

	###Time:
	Består av ett Integer attribut som representerar en tid i sekunder från tiden 00.00.00.
	Konstruktorn tar emot en String parameter på formatet HH.MM.SS.
	Har metoder för jämföring av två tider samt en toString.

	###Lap:
	Modellering av ett varv eller en etapp.
	Består av två Time-objekt där den ena representerar starttiden för varvet och den andra sluttiden.
	Har metoder för att sätta och hämta start respektive sluttid.
	
	###Participant:
	Modellering av en deltagare.
	När en participant skapas anges en Integer parameter i konstruktorn som blir deltagarens tävlingsId.
	I participant finns också möjlighet att lägga till ett namn och ett race.
	Klassen innehåller också metoder för jämföring samt getters för race, namn och id, samt setter för namn, klass och race.
	
	###ModelInitiator:
	Den tar in en deltagarlista och raceEvent. Klassen används för att samla värden och sortera dem.

	###RaceEvent:
	Modellering av ett tävlingstilfälle med flera tävlare.
	Konstruktorn tar emot en int parameter som anger hur många varv som tävligen består av.
	Består av en samling Participants med deras tillhörande Race.
	Har metoder för att hantera tillägg av Participants.

##Package Race:
	
	###Race:
	Generell modellering av en Participants tävlingsinsats.
	Består av flera Laps.
	Klasser har också getters och setters för start och sluttid, men även för enskilda Laps.
	Har också metod för totaltiden för alla Laps i Racet.
	
	###LapRace:
	Subklass till Race, används för varvbaserade lopp. Får en Integer som inparameter för att begränsa hur många varv         loppet ska bestå av.
	
	
	###SimpleRace:
	Subklass till Race, används för 'envarvslopp'.
	
	###TimeRace:
	Subklass till Race, används för tidsbaserade lopp. Får en Time som inparameter för att begränsa hur länge loppet          ska köras.
	
	

##Package utils:
	
	###Enduro:
	Singeltonklass som kan generera en absolut URL-adress till en mapp i projektet.
	Används för att inte relativa vägar ska användas då det blir fel om samma adress anropas på olika konton.
	
	###FileReader:
	Läser innehållet på den fil vars URL-adress anges i konstruktorn och returnerar en Iterator<String> av innehållet.
	
	###FileWriter:
	Skriver ut innehållet i andra argumentet i konstruktorn (antingen en String eller en Iterator<String>) på platsen         (URL-adress) som anges i det första argumentet.

	
##Package views:

	###GUIFormatter:
	Skapar ett användargränssnitt för formattering av informationsfiler innehållande namn och tider.
	Vid instansiering så tar den in en FormatterController som håller all relevant information.
	
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
	Innehållet från registeringsfälet tillsammans med den aktuella tiden skrivs ut på nästa nya rad på den filplats 	        som valdes i GUIRegisters file chooser.
	Om filen inte existerar så skapas en ny fil vid första registreringen.
	
	###RegisterField:
	Ett textfält som tar in RegisterButton i konstruktorn, och lägger till en KeyEvent-lyssnare(enter) till
	den, så att även enter funkar att registrera tävlanden med. 
	
	
	
	
	
