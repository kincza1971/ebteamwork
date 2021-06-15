import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameRepository {
    private List<Game> games = new ArrayList<>();

    public void addGame(Game game) {
        games.add(game);
    }



    public void addGamesFromFile(Path path){
        try(Stream<String> lines = Files.newBufferedReader(path).lines()){
            games.addAll(lines
                    .map(Game::parse)
                    .collect(Collectors.toList()));
        }catch (IOException ioe){
            throw new IllegalArgumentException("Can not read file",ioe);
        }
    }

}
