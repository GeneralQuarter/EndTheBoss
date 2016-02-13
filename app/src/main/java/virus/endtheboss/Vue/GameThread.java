package virus.endtheboss.Vue;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by Quentin Gangler on 11/02/2016.
 * Thread du jeu
 */
public class GameThread extends Thread {
    private final static int SLEEP_TIME = 40;

    private boolean running = false;
    private GameSurface gs = null;
    private SurfaceHolder surfaceHolder = null;

    public GameThread(GameSurface gs)
    {
        super();
        this.gs = gs;
        this.surfaceHolder = gs.getHolder();
    }

    public void startThread()
    {
        running = true;
        super.start();
    }

    public void stopThread()
    {
        running = false;
    }

    public void run()
    {
        Canvas c = null;
        while (running)
        {
            c = null;
            try
            {
                c = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder)
                {
                    if (c != null)
                    {
                        gs.draw(c);
                    }
                }
                sleep(SLEEP_TIME);
            }
            catch(InterruptedException ie)
            {
            }
            finally
            {
                // do this in a finally so that if an exception is thrown
                // we don't leave the Surface in an inconsistent state
                if (c != null)
                {
                    surfaceHolder.unlockCanvasAndPost(c);
                    gs.postInvalidate();
                }
            }
        }
    }
}
