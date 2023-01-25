## Ülesanne
### Realiseeri õppeinfosüsteem (ÕIS).

### Kasutatud tehnoloogiad:
- Java

#### Lisaks, kasuta OOP printsiipe, kus tundub mõistlik:
- abstraktsus
- klassil võiks olla üks peamine ülesanne
- null asemel kasuta Optional (kui tahad tähistada, et mingi väärtus ei pruugi olemas olla)
- kasuta erindeid
- kasuta disainimustreid (strateegia, builder, factory jne)

### Miinimumnõuded (160p)
Selleks, et ülesande eest üldse punkte saada, tuleks realiseerida vähemalt järgnev:
- ülikoolis toimuvad kursused
- tudengil on nimi ja vanus
- tudeng saab ülikooli registreeruda
- tudeng saab korraga olla vaid ühes ülikoolis
- aine on seotud ülikooliga, sellel on nimi, ainepunktide arv (ECTS), hindeline/arvestuslik aine
- tudeng saab ainele registreeruda (ainult juhul, kui ta on ülikooli registreerunud)
- tudeng saab aine sooritada või läbi kukkuda
- kui tudeng sooritab aine, siis ta saab selle eest kas hinde (kui on hindeline aine) või arvestuse (kui on arvestuslik aine)
- hinded on 0 (läbi kukkunud), 1, 2, 3, 4, 5.
- tudengil on ülevaade sooritatud (ja mitte sooritatud) ainetest
- ülikoolil on ülevaade tudengitest, ainetest ja õppivatest tudengitest.

Ülesandes tuleks kontrollida mõistlikkuse piires erinevaid piiranguid. Näiteks tudengi vanus võiks olla näiteks suurem kui 18. Aine ainepunktide arv peaks olema mitte-negatiivne. Sisestatud hinne ei saa olla väljaspool määratud vahemikku jne.

### Õppekava, aine moodul, õppejõud, deklareerimine (80p)
Realiseeri õppekava. Ülikoolis on õppekavad (näiteks IAIB) ja selle sees on kursused. Kui tudeng tuleb ülikooli, siis ta valib õppekava. Tudengil peab olema ülevaade, palju tal õppekavast on läbitud. Üks kursus võib esineda ka mitmes õppekavas (ehk siis ei ole vaja teha mingit piirangut selles osas).
Õppekava koosneb erinevatest moodulitest (vabaõpe, eriala, üldõpe jne). Aine kuulub moodulisse.

Ainetel on tüübid:
- tavaline - deklareeritakse, saab tulemuse määrata.
- lõputöö - seda ei saa deklareerida. Seda saab sisuliselt otse "võtta" (avaldusega)
- praktika - seda ei saa deklareerida. Seda saab samamoodi otse "võtta" (avaldusega), kui õppekava on täidetud (kui tudengil on kõik vajalikud ainepunktid täis)
- lisa veel üks aine tüüp, mis käitub kuidagi erinevalt
- Ainel on õppejõud. Õppejõul on ülevaade tema ainetest.

Tudeng saab aineid deklareerida (muudmoodi aineid "võtta" ei saa, välja arvatud teatud tüüpi ained). Selleks täidetakse ära deklaratsiooni objekt. Sinna saab panna ained. Kui deklaratsioon on valmis, siis tuleb see esitada. Nüüd on valitud ained aktiivsed (tudeng õpib). Iga aine kohta tuleb lisada tulemus (soortiatud või mitte). Uut deklaratsiooni ei saa teha, kui tudengil on mõni aine veel aktiivne.

### Tudengi deklaratsiooni strateegia (80p)
- lihtsamad ained enne - tudeng valib need sooritamata ained õppekavast, mis annavad vähem ainepunkte ning mis tal on tegemata (st tulemus ei ole positiivne). Sealjuures on määratud minimaalne ja maksimaalne deklaratsiooni ainepunktide arv - tudeng proovib täitsa minimaalse nõude.
- erineva mooduli ained - tudeng valib need sooritamata ained õppekavast, mis tagaks selle, et igast moodulist aineid oleks võetud. Kui mõnda sellist ainet pole (või see on sooritatud), siis seda ei pea võtma. Deklaratsioonil on ülempiir, mitu EAP-d deklareeritakse.
- populaarsed ained - tudeng valib need sooritamata ained õppekavast, mis on kõige populaarsemad - mida on kõige rohkem deklareeritud (vahet pole, kas sooritatud, läbi kukutud või aktiivseid). Seal juures on määratud minimaalne ainepunktide arv, mida tudeng peab deklareerima. Kui õppekava saab täidetud (näiteks vaja on 18 EAP, aga õppekavas on vaid 12EAP järel) tuleb võimalusel leida suvaline muu aine ülikoolist, mida tudeng ei ole sooritanud (n-ö vaba aine).

### Õpilaste pingerida, stipendium (80p)
Ülikool väljastab järjestatud tudengite järjendi. Tudengeid järjestatakse järgmiste kriteeriumite alusel (eespool on tähtsamad kriteeriumid):

- Kaalutud keskhinne. Iga hindelise aine kaal on tema ainepunktide arv. Ebaõnnestunud hindelised ained (kus hinne on "0") lähevad arvutusse sisse poole koormusega. Näiteks kui tudengil on ained (EAP, hinne): (5, "5") ja (3, "4"), siis tema kaalutud keskmine on: 4.625. Kui on sellised tulemused: (5 EAP, "4"), (3 EAP, "0"), siis KKH=3.077 ("0" tulemusega aine läheb arvesse kui 1.5 EAP).
- KKH arvestatakse 1 komakoha täpsusega, ümardatakse alla. Seega 4.625 ja 4.6 on samaväärsed.
- Kui KKH on samaväärne, siis on eespool see tudeng, kellel on õppekava täituvuse % suurem (näiteks üks on täitnud 30%, teine 20%, siis 30% täituvusega tudeng on eespool)
- Kui ka õppekava täitvutuse % on sama, siis võrreldakse EAP-de arvu (kellel on rohkem, see on eespool)
- Kui kõik eelnev on võrdne, siis on eespool tudeng, kelle nimi on tähestikus eespool.

Parimad tudengid saavad stipendiumi, stipendiumil on summa. Ülikoolil on määratud miinimumnõuded (KKH ja/või EAP) stipendiumi saamiseks. Samuti on määratud stipendiumide hulk - vaid parimad tudengid saavad stipendiumi. Kui sobivaid tudengeid on vähem kui stipendiumi kohti, siis saavad kõik sobivad tudengid stipendiumi. Kui sobivaid tudengeid on rohkem, siis saavad vaid nii mitu parimat tudengit, kui palju on ülikoolis ette nähtud.
Tudengil peab olema teada, kas ta on saanud stipendiumi. Ülikoolil on ülevaade, kes on stipendiumi saanud.
Kirjuta selle osa kohta testid

