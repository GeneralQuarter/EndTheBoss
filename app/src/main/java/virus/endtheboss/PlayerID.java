package virus.endtheboss;

import java.io.Serializable;

/**
 * Created by Quentin Gangler on 17/02/2016.
 */
public class PlayerID implements Serializable{
    private int id;
    private boolean hote;

    public PlayerID(int id){
        this.id = id;
        if(id == 1){
            hote = true;
        }else{
            hote = false;
        }
    }

    public int getId() {
        return id;
    }

    public boolean isHote() {
        return hote;
    }
}
