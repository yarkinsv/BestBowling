package hh.yarkinsv;

/**
 * Created by YarkinSV on 2/1/2016.
 */
public class Player {
    private String name;

    public Player(String name) {
        if (name == null || name.length() == 0 || name.length() > 20) {
            throw new IllegalArgumentException("Wrong name provided. Length of a name must be between 1 and 20 characters.");
        }

        this.name = name;
    }

    public String getName() {
        return name;
    }
}
