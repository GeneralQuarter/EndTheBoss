package virus.endtheboss.Vue;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Movie;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.support.annotation.RawRes;

import virus.endtheboss.Enumerations.GameValues;
import virus.endtheboss.Modele.Personnages.Personnage;

/**
 * Created by Quentin Gangler on 12/02/2016.
 * Classe utilisée pour afficher un personnage à l'écran
 */
public class PersonnageVue extends Drawable {

    private Context mContext;
    protected Personnage personnage;

    private Movie mMovie = null;
    private Bitmap movieBitmap = null;
    private long mMoviestart = 0;
    private long startTime = 0;
    private @RawRes int currentAnimation = -1;

    public PersonnageVue(Context context, Personnage personnage){
        this.mContext = context;
        this.personnage = personnage;

        updateAnimation(personnage.getIdle());
    }

    public synchronized void updateAnimation(@RawRes int resId){
        startTime = SystemClock.uptimeMillis();
        if(mMovie == null || movieBitmap == null || currentAnimation == -1 || resId != currentAnimation) {
            currentAnimation = resId;
            mMovie = Movie.decodeStream(mContext.getResources().openRawResource(resId));// context.getResources().getAssets().open("PostLoadingAnimation.gif"));
        }
    }

    @Override
    public void draw(Canvas canvas) {
        //Paint p = new Paint();
        //p.setColor(Color.GREEN);
        //canvas.drawRect(saPositionX * GameValues.tileWidth+1, saPositionY * GameValues.tileHeight+1, (saPositionX+1)*GameValues.tileWidth, (saPositionY+1)*GameValues.tileHeight, p);

        final long now = SystemClock.uptimeMillis();

        if (mMoviestart == 0) {
            mMoviestart = now;
        }

        int relTime = 0;
        if(now-startTime>500)
            mMovie.setTime(0);
        else {
            if(mMovie.duration() != 0)
                relTime = (int) ((now - mMoviestart) % mMovie.duration());
            mMovie.setTime(relTime);
        }
        // mMovie.draw(canvas, 0, 0);

        movieBitmap = Bitmap.createBitmap(mMovie.width(), mMovie.height(), Bitmap.Config.ARGB_8888);

        Canvas movieCanvas = new Canvas(movieBitmap);
        mMovie.draw(movieCanvas, 0, 0);

        //Log.i("PersonnageVue", "Movie height : " + mMovie.height());
        //Log.i("PersonnageVue", "Movie width : " + mMovie.width());

        float newMovieWidth = GameValues.tileHeight/((float) (mMovie.height()) / (float) (mMovie.width()));

        //Log.i("PersonnageVue", "TileHeight : " + GameValues.tileHeight);
        //Log.i("PersonnageVue", "TileWidth : " + GameValues.tileWidth);

        //Log.i("PersonnageVue", "newMovieWidth : " + newMovieWidth);

        Rect src = new Rect(0, 0, mMovie.width(), mMovie.height());
        Rect dst = new Rect(
                (int) (personnage.getX() * (GameValues.tileWidth) + ((GameValues.tileWidth-newMovieWidth) / 2)) + 1,
                (int) (personnage.getY() * GameValues.tileHeight) + 1,
                (int) ((personnage.getX() + 1) * GameValues.tileWidth - ((GameValues.tileWidth-newMovieWidth) / 2)),
                (int) ((personnage.getY() + 1) * GameValues.tileHeight)
        );
        canvas.drawBitmap(movieBitmap, src, dst, null);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

    public Personnage getPersonnage(){
        return personnage;
    }
}
