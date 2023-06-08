# BDD tesztek megvalósítása  
## _(Blága Zsolt László - blagazsolt, Wohlfart Richárd - fargoricsi)_

A BDD megvalósításához a Cucumber eszközét használtuk.
 
A legnagyobb nehézséget az jelentette, hogy magát a Cucumber-t integráljuk a projektbe. Meg kellett ismernünk, hogy milyen mappa szerkezetet vár el a keretrendszer. Mint kiderült a “.feature” fájlokat egy test resource root mappába kell helyezni. Ezt meg is tettük, de így is hibákba ütköztünk. Az első gondot az jelentette, hogy a Gradle a projektet több modulba szervezte. Emiatt a fordító nem találta meg a beimportált a használt Cucumber annotációkat. Mint kiderült, az IntelliJ újabb verzióiban nem lehet kikapcsolni a GUI-n keresztül, a modulok automatikus generálását. Így közvetlen az IDE projekt fájlait szerkesztve tudtuk kikapcsolni ezt a funkciót.  

Sajnos ez nem oldotta meg az összes problémánkat. Bár az intellij felismerte a tesztjeinket, azok mégsem fordultak le. Mint kiderült, itt az okozta a gondot, hogy bár az IntelliJ-ben beállítottuk, hogy melyik mappa a test sources root, ezt elfelejtettük megtenni a build.gradle fájlban. Miután ez javításra került, a Cucumber tesztjeink is végre elkezdtek működni. 

A tesztek megírás különösebb problémák nélkül sikerült. Miután megértettük a Gherkin nyelv működését, hatékonyan ment a tesztek írása. Egyedül az olyan jellegű tesztek jelentettek kisebb problémát, melyek közvetlen a GUI-t “kattintva” kerültek végrehajtásra. Ezeknek a tanulsága az, hogy már a program írásakor is jobban figyelembe kell venni, hogy később teszteket kell majd írni hozzá. Például adhattunk volna a gomboknak könnyen értelmezhető neveket, amik alapján gond nélkül lehet rájuk hivatkozni a tesztekben. 