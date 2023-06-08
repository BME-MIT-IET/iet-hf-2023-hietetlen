# Statikus analízis SonarCloud segítségével, kód manuális átnézése 
## _(Borbás Luca - lucyelle)_

Manuálisan átfutottam az egész projektet, és javítottam néhány szembetűnőbb hibát, pl. shotgun surgery, üres TODO-k és függvények. 

Statikus analízis elvégzéséhez a SonarCloud szolgáltatást használtam, illetve Visual Studio Code-hoz a SonarLint-et. Elsőre a SonarCloud önmagában nem jelzett hibát, csak miután összekapcsoltam a SonarLint-tel is. Ezek segítségével ellenőriztem újra a kódot, és néztem át az általuk jelzett hibákat, majd javítottam belőlük néhányat. 

Meglepő volt, hogy egy viszonylag kicsi projektben is mennyi code smell fordulhat elő, illetve szembetűnő, hogy azóta rengeteget fejlődtünk mi is.