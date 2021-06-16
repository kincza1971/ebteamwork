import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameRepository {
    private List<Game> games = new ArrayList<>();
    private boolean countryPlayed;

    public void addGame(Game game) {
        if (game != null) {
            games.add(game);
        }
    }

    public List<Game> getGames() {
        return new ArrayList<>(games);
    }

    public void addGamesFromFile(Path path) {
        try (Stream<String> lines = Files.newBufferedReader(path).lines()) {
            games.addAll(lines
                    .map(Game::parse)
                    .collect(Collectors.toList()));
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Can not read file", ioe);
        }
    }

    public Optional<Game> getGameBiggestDifference() {
        return games.stream()
                .max(Comparator.comparingInt(g -> Math.abs(g.getFirstCountryScore() - g.getSecondCountryScore())));
    }

    public int getNumberOfGoalsByCountryOnAGame(Game game, String country) {
        if (game.getFirstCountry().equals(country)) {
            countryPlayed=true;
            return game.getFirstCountryScore();
        }
        if (game.getSecondCountry().equals(country)) {
            countryPlayed=true;
            return game.getSecondCountryScore();
        }
        return 0;
    }

    public int getNumberOfGoalsByCountry(String country) {
        countryPlayed = false;

        int result = games.stream()
                .mapToInt(g -> getNumberOfGoalsByCountryOnAGame(g,country))
                .sum();

        if (countryPlayed) {
            return result;
        }
        throw new IllegalArgumentException("This country has no games");
     }

     public Optional<String> getCountryWithMostGoals() {
        return games.stream()
                .flatMap(g -> g.getCountries().stream())
                .distinct()
                .max(Comparator.comparingInt(this::getNumberOfGoalsByCountry));
     }

}
