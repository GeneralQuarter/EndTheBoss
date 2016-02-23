package virus.endtheboss.Vue;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import virus.endtheboss.Enumerations.GameValues;
import virus.endtheboss.R;

/**
 * Created by Quentin Gangler on 11/02/2016.
 *
 */
public class GameSurface extends SurfaceView implements SurfaceHolder.Callback{

    private GameThread gameThread = null;
    public List<Drawable> layers;
    private Bitmap fond;

    private Paint p;
    private Rect src;
    private Rect dst;

    public GameSurface(Context context) {
        super(context);

        getHolder().addCallback(this);

        this.layers = new ArrayList<>();

        p = new Paint();
        p.setColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));

        fond = BitmapFactory.decodeResource(getResources(), R.drawable.fond);

        src = new Rect(0, 0, fond.getWidth(), fond.getHeight());
    }

    public void startGame()
    {
        if (gameThread == null)
        {
            gameThread = new GameThread(this);
            gameThread.startThread();
        }
    }

    public void stopGame()
    {
        if (gameThread != null)
        {
            gameThread.stopThread();

            boolean retry = true;
            while (retry)
            {
                try
                {
                    gameThread.join();
                    retry = false;
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            }
            gameThread = null;
        }
    }

    public synchronized void removePersonnageVue(int personnageId){
        try {
            for(Drawable d : layers){
                if(d instanceof PersonnageVue){
                    PersonnageVue pv = (PersonnageVue) d;
                    if(pv.getPersonnage().getId() == personnageId){
                        layers.remove(d);
                    }
                }
            }
        }catch(ConcurrentModificationException e){
            removePersonnageVue(personnageId);
        }
    }

    private synchronized void drawLayers(Canvas canvas){
        try {
            for (Drawable d : layers) {
                d.draw(canvas);
            }
        }catch(ConcurrentModificationException e){
            Log.i("GameSurface", "Concurrent modification skipped");
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.setWillNotDraw(false);
        dst = new Rect(0, 0, getWidth(), getHeight());
        startGame();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        stopGame();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        GameValues.updateSurface(canvas.getWidth(), canvas.getHeight());

        canvas.drawBitmap(fond, src, dst, null);

        for(float i = 1f; i <= 19f; i++){
            canvas.drawLine(i*GameValues.tileWidth, 0, i*GameValues.tileWidth, (float) GameValues.HEIGHT, p);
            canvas.drawLine(0, i*GameValues.tileHeight, GameValues.WIDTH, i*GameValues.tileHeight, p);
        }

        //Draw Layers
        drawLayers(canvas);
    }
}
