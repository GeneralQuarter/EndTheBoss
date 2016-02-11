package virus.endtheboss;

import android.content.Context;
import android.view.SurfaceView;

/**
 * Created by Quentin Gangler on 11/02/2016.
 *
 */
public class GameSurface extends SurfaceView{

    private Context mContext;

    public GameSurface(Context context) {
        super(context);

        this.mContext = context;
    }
}
