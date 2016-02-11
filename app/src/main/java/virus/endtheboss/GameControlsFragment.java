package virus.endtheboss;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Quentin Gangler on 11/02/2016.
 * Classe pour la vue des controles du jeu
 */
public class GameControlsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.game_controls, container, false);
    }
}
