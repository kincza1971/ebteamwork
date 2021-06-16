import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class GameRepositoryTest {
    GameRepository repo;
    List<Game> games;

    @BeforeEach
    void init() {
        repo = new GameRepository();
        games = Arrays.asList(
                new Game("Turkey", "Italy", 0, 3),
                new Game("Wales", "Switzerland", 1, 1),
                new Game("Denmark", "Finnland", 0, 1));

        Path path = Path.of("results.csv");
        repo.addGamesFromFile(path);
    }

    @RepeatedTest(3)
    void testAdd(RepetitionInfo ri) {
        int i = ri.getCurrentRepetition() - 1;
        repo.addGame(games.get(i));
        assertEquals(16, repo.getGames().size());
    }

    @Test
    void testAddWithNull() {
        repo.addGame(null);
        assertEquals(15, repo.getGames().size());
    }

    @Test
    void testAddGamesFromFile() {
        assertThat(repo.getGames())
                .hasSize(15)
                .extracting("firstCountry")
                .contains("Italy");
    }

    @Test
    void testAddGamesFromFileWithWrongPath() {
        Path path = Path.of("/results.csv");
        assertThrows(IllegalArgumentException.class,()->repo.addGamesFromFile(path));
    }

    @Test
    void testGetGameBiggestDifference() {
        games.forEach(g -> repo.addGame(g));
        Optional<Game> result = repo.getGameBiggestDifference();
        if(result.isPresent()) {
            assertEquals(games.get(0),result.get());
        } else {
            fail();
        }
    }
    private static Stream<Arguments> createGames() {
        return Stream.of(
                arguments("Turkey", 1),
                arguments("Italy", 5),
                arguments("Hungary", 1));
    }

    @ParameterizedTest
    @MethodSource("createGames")
    void testGetNumberOfGoalsByCountry(String country,int goals) {
        assertEquals(goals,repo.getNumberOfGoalsByCountry(country));
    }


    @Test
    void testCountScoresByCountryInvalidCountry() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->repo.getNumberOfGoalsByCountry("Ragnarök"));
        assertEquals("This country has no games", ex.getMessage());
    }

}