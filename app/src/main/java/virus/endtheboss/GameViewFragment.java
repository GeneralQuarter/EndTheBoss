package virus.endtheboss;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quentin Gangler on 11/02/2016.
 * Classe fragment pour la grille du jeu
 */
public class GameViewFragment extends Fragment{

    private GameSurface gs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        gs = new GameSurface(getActivity());
        return gs;
    }
}
