## Álláskeresési alkalmazás, amely lehetővé teszi a felhasználók számára, hogy regisztráljanak, munkahelyeket hozzanak létre és kereséseket végezzenek a rendszerben található álláshirdetések között.
## Java 11

Az alkalmazás jelenlegi verziója működőképes, de számos továbbfejlesztési lehetőség ajánlott, hogy production ready legyen.

## Felhasználói felület
Javasolt lehet a felhasználói felület további javítása és testreszabása azért, hogy felhasználóbarát legyen és jobb felhasználói élményt nyújtson. Például: további interaktivitás hozzáadása, reszponzív dizájn biztosítása különböző eszközökön való megfelelő megjelenítés érdekében.

## Adattárolás
A felhasználókat (client) és állásokat (job) érdemes persistent adatbázisba menteni. Az API kulcsot érdemes egy külön táblába tárolni a client id-val és egy időponttal együtt. Amennyiben az adott időponttól számolva (pl.) 10 perc eltelt, az API kulcs lejár.
Biztonság: Fontos, hogy az alkalmazás biztonságos legyen. Ehhez érdemes lehet bevezetni az OAuth vagy más autentikációs és autorizációs mechanizmusokat az azonosítás és hozzáféréskezelés céljából.

## Tesztelés
Biztosítani kell a kód minőségét és stabilitását, ezért szükséges a különböző típusú tesztek (egységtesztek, integrációs tesztek, elfogadási tesztek stb.) végrehajtása. (CI/CD)

## Dokumentáció
Dokumentáció: dokumentáció készítése a telepítési, konfigurációs és használati útmutatókról. Ez segít az új fejlesztőknek és az üzemeltetési csapatnak.

## Alkalmazás konfigurálása és futtatása

Környezet: szükséges JDK és Maven telepítése

Projekt letöltése és betöltése: A repository-ban megtalálható a java11 branch-ben Java 11 nyelven a forráskód. Letöltést követően importálható a projekt a fejlesztőkörnyezetedbe (pl. IntelliJ IDEA)

Függőségek telepítése: mvn install

A projekt beállításokban érdemes ellenőrizni a projekt beállítását.
Java 11 esetén szükséges: SDK -> JDK 11, Language Level -> 11 - Local variable syntax for lambda parameters.
Java 19 esetén szükséges: SDK -> JDK 19, Language Level -> 17 - Sealed types, always strict floating-point semantics

### License

Gergő Kerényi 2024