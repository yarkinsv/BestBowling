package hh.yarkinsv;

import java.util.Date;

/**
 * Created by RRP-YarkinSV on 02.02.2016.
 */
public interface PlayersRepository {
    int getHighestScore(Player player);
    Date getLastGameDate(Player player);
}
