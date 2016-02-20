package hh.yarkinsv;

import java.time.Instant;
import java.util.Date;

/**
 * Created by RRP-YarkinSV on 02.02.2016.
 * Только для теста Mock'а!
 */
public class JustForFunPlayersRepository implements PlayersRepository {

    @Override
    public int getHighestScore(Player player) {
        return 250;
    }

    @Override
    public Date getLastGameDate(Player player) {
        return Date.from(Instant.now());
    }
}
