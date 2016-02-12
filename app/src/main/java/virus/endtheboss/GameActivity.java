package virus.endtheboss;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import virus.endtheboss.Enumerations.Deplacement;
import virus.endtheboss.Modele.Archer;
import virus.endtheboss.Modele.Personnage;

public class GameActivity extends FragmentActivity{

    FragmentManager fm;
    GameViewFragment gvf;
    List<Drawable> layers;
    Archer player;
    GameSurface gs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        player = new Archer(2,2);

        fm = getSupportFragmentManager();
        gvf = (GameViewFragment) fm.findFragmentById(R.id.game_view_fragment);
        gs = (GameSurface) gvf.getView();
        gs.layers.add(player);

        gs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("gs", "click");
                Archer a = (Archer) gs.layers.get(0);
                a.deplacement(Deplacement.DROITE);
                gs.layers.set(0, a);
                gs.postInvalidate();
            }
        });
    }
}
