package virus.endtheboss;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import virus.endtheboss.Vue.GameSurface;

/**
 * Created by Quentin Gangler on 11/02/2016.
 * Classe fragment pour la grille du jeu
 */
public class GameViewFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return new GameSurface(getActivity());
    }
}
