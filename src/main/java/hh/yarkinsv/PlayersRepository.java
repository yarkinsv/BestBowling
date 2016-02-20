package hh.yarkinsv;

import java.util.Date;

/**
 * Created by RRP-YarkinSV on 02.02.2016.
 * Ничего не делает! И не зачем не нужно! Просто для теста Mock'а
 */
public interface PlayersRepository {
    int getHighestScore(Player player);
    Date getLastGameDate(Player player);
}
