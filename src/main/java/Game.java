public class Game {

    private String firstCountry;
    private String secondCountry;
    private int firstCountryScore;
    private  int secondCountryScore;

    public Game(String firstCountry, String secondCountry, int firstCountryScore, int secondCountryScore) {
        this.firstCountry = firstCountry;
        this.secondCountry = secondCountry;
        this.firstCountryScore = firstCountryScore;
        this.secondCountryScore = secondCountryScore;
    }

    public String getFirstCountry() {
        return firstCountry;
    }

    public String getSecondCountry() {
        return secondCountry;
    }

    public int getFirstCountryScore() {
        return firstCountryScore;
    }

    public int getSecondCountryScore() {
        return secondCountryScore;
    }
}
//## Feladat
//A mai feladatban az EB meccsek eredményeit kell egy alkalmazásban
//tárolnod, és különböző feladatokat elvégezned.
//### Game
//Legyen egy `Game` nevű osztályod a következő attribútumokkal
//+ `firstCountry (String)`
//+ `secondCountry (String)`
//+ `firstCountryScore (int)`
//+ `secondCountryScore (int)`
//### GameRepository
//Legyen egy `GameRepository` nevű osztályod, melynek van egy meccseket
//memóriában tároló listája.
//### GameService
//Legyen egy `GameService` nevű osztályod, ami különböző statisztikai adatokat jelenít meg.
//Legyen egy `GameRepository` attribútuma amin keresztül eléri a benne lévő listát.
