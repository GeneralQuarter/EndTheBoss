package virus.endtheboss.Serveur;

import java.io.ObjectOutputStream;

import virus.endtheboss.PlayerID;

/**
 *
 * @author Quentin Gangler
 */
public class Player {
    private PlayerID id;
    private ObjectOutputStream out;

    public Player(PlayerID id, ObjectOutputStream out){
        this.id = id;
        this.out = out;
    }

    public PlayerID getId() {
        return id;
    }

    public ObjectOutputStream getOut() {
        return out;
    }
}
