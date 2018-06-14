
/**
 * @author Darshan B., David T., Benn O., Sudeep R.
 */

public class Player {
    private String name;

    Player(String name) {
        this.name = name;
    }

    void reset() {
        name = "";
    }

    void setName(String name) {
        this.name = name;
    }

    String getName(){
        return name;
    }
    
}
