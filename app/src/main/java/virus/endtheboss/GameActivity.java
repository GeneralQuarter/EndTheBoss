package virus.endtheboss;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

import virus.endtheboss.Modele.Archer;

public class GameActivity extends FragmentActivity{

    FragmentManager fm;
    GameViewFragment gvf;
    List<Drawable> layers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Archer player = new Archer(2,2);

        fm = getSupportFragmentManager();
        gvf = (GameViewFragment) fm.findFragmentById(R.id.game_view_fragment);
        GameSurface gs = (GameSurface) gvf.getView();
        gs.layers.add(player);
    }
}
