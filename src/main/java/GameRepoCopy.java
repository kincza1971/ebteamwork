import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameRepoCopy {
    private List<Game> games = new ArrayList<>();

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
        return games.stream().sorted((g1, g2) ->
                Math.abs(g2.getFirstCountryScore()-g2.getSecondCountryScore())
                        - Math.abs(g1.getFirstCountryScore()-g1.getSecondCountryScore())
        ).findFirst();
    }

}
