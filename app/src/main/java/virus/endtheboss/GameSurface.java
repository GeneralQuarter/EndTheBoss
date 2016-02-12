package virus.endtheboss;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import virus.endtheboss.Modele.Archer;

/**
 * Created by Quentin Gangler on 11/02/2016.
 *
 */
public class GameSurface extends SurfaceView implements SurfaceHolder.Callback{

    private Context mContext;
    private GameThread gameThread = null;
    public List<Drawable> layers;

    public GameSurface(Context context) {
        super(context);

        this.mContext = context;

        getHolder().addCallback(this);

        this.layers = new ArrayList<>();
        //layers.add(new Archer(2,2));
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

            // Waiting for the thread to die by calling thread.join,
            // repeatedly if necessary
            boolean retry = true;
            while (retry)
            {
                try
                {
                    gameThread.join();
                    retry = false;
                }
                catch (InterruptedException e)
                {
                }
            }
            gameThread = null;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.setWillNotDraw(false);
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

        //Background color
        canvas.drawRGB(255, 128, 128);

        //Lines
        Paint p = new Paint();
        p.setColor(getResources().getColor(R.color.colorPrimaryDark));
        for(float i = 1f; i <= 19f; i++){
            canvas.drawLine(i*GameValues.tileWidth, 0, i*GameValues.tileWidth, (float) GameValues.HEIGHT, p);
            canvas.drawLine(0, i*GameValues.tileHeight, GameValues.WIDTH, i*GameValues.tileHeight, p);
        }

        //Draw Layers
        for(Drawable d : layers) {
            d.draw(canvas);
        }
    }
}
