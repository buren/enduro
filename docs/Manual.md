## Manual 



För att använda formaterare:

1. Kör .jar filen.
2. Tryck på ```"Formaterare"```-knappen.
3. Välj mellan ```"Varv-lopp"```, ```"Tids-lopp"```,  ```"Etapp-lopp"``` och ```"Envarvs-lopp"```.
4. Skriv in antal varv (siffror) eller tidsgräns på format hh.mm.ss beroende på val av lopp.
4b. Önskas en annan varvgräns än 00.15.00, klicka på ```"Ändra Varvgräns"``` och skriv in den nya varvgränsen på format hh.mm.ss.  
5. Tryck på ```"Ladda in "startfil"```-knappen och välj sedan textfilen med starttider.
6. Tryck på ```"Ladda in "målfil"```-knappen och välj sedan textfilen med måltider.
7. Tryck på ```"Ladda in "namnfil"```-knappen och välj sedan textfilen med namn samt klass.
8. Tryck på ```"Spara resultat till fil"```-knappen och välj sedan plats där du vill spara filen och skriv namnet du vill ha på filen.
9. Ange de antal varv som du vill få med i utskriften. Tryck ```"OK"```

För att använda registerare:

1. Kör .jar filen.
2. Tryck på ```"Registrering"```-knappen, eller tryck enter.
3. Välj sökväg till var du vill ha din tempfil. OBS! Om du startar programmet med samma sökväg och namn som på tidigare tempfil så sparas den gamla över och går förlorad.OBS!
4. Skriv in deltagarens siffra i den övre vänstra textrutan och tryck sedan på ```"Registrera"```-knappen för att registrera nuvarade tid på vald deltagare.
5. Om du vill ha masstart så matas stjärna (*) in i den vänstra textrutan.
6. Om du trycker på ```"Registrera"```-knappen först så sparas tiden direkt och det kommer upp en prompt ruta där du kan skriva upp numret i efterhand.
7. Om du vill masstarta en klass, skriv då in klassnamnet och registrera det. OBS! Klassnamnen du vill starta måste skrivas in precis som i namnfilen! 


Filernas formattering:

   Namnfiler har en headerrad och sedan består av rader innehållande liknade det, tex.

    StartNr; Namn; Klubb; MC
    KLASS
    1; Anders Asson; ABC; 125

   Starttids och måltidsfiler har ingen headerrad utan ser ut tex såhär.

    1; 12.00.00
    2; 12.01.00
    3; 12.02.00


   För masstart ser startfilerna ut följande.

    *; 12.00.00
