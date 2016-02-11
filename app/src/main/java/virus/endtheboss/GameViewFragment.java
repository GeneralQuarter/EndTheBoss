package virus.endtheboss;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Quentin Gangler on 11/02/2016.
 * Classe fragment pour la grille du jeu
 */
public class GameViewFragment extends Fragment implements SurfaceHolder.Callback{

    private GameSurface gs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        gs = new GameSurface(getActivity());
        gs.getHolder().addCallback(this);
        gs.setWillNotDraw(false);
        return gs;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        tryDrawing(holder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        tryDrawing(holder);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    private void tryDrawing(SurfaceHolder holder) {
        Log.i("Draw", "Trying to draw...");

        Canvas canvas = holder.lockCanvas();
        if (canvas == null) {
            Log.e("Draw", "Cannot draw onto the canvas as it's null");
        } else {
            drawMyStuff(canvas);
            holder.unlockCanvasAndPost(canvas);
        }
    }

    private void drawMyStuff(final Canvas canvas) {
        int WIDTH = canvas.getWidth();
        int HEIGHT = canvas.getHeight();

        float tileHeight = HEIGHT/20f;
        float tileWidth = WIDTH/20f;

        //Background color
        canvas.drawRGB(255, 128, 128);

        //Lines
        Paint p = new Paint();
        p.setColor(getResources().getColor(R.color.colorPrimaryDark));
        for(float i = 1f; i <= 19f; i++){
            canvas.drawLine(i*tileWidth, 0, i*tileWidth, (float) HEIGHT, p);
            canvas.drawLine(0, i*tileHeight, WIDTH, i*tileHeight, p);
        }

    }
}
