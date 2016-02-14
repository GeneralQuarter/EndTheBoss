package virus.endtheboss.Vue;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;

import virus.endtheboss.Enumerations.GameValues;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Formes.Forme;

/**
 * Created by Quentin Gangler on 13/02/2016.
 */
public class FormeVue extends Drawable {

    private Forme f;
    private CaseCarte origine;
    private Bitmap bitmap;
    private boolean ready;
    private boolean formChanging;

    public FormeVue(Forme f, CaseCarte origine){
        this.f = f;
        this.origine = origine;
        this.ready = true;
        this.formChanging = false;
    }

    private void init(){
        if(ready) {
            ready = false;
            bitmap = Bitmap.createBitmap(GameValues.WIDTH, GameValues.HEIGHT, Bitmap.Config.ARGB_8888);
            Canvas mCanvas = new Canvas(bitmap);
            Paint p = new Paint();
            p.setColor(Color.WHITE);
            p.setAlpha(50);
            for (CaseCarte cc : f.getForme(origine)) {
                mCanvas.drawRect(cc.getX() * GameValues.tileWidth + 1, cc.getY() * GameValues.tileHeight + 1, (cc.getX() + 1) * GameValues.tileWidth, (cc.getY() + 1) * GameValues.tileHeight, p);
            }
            ready = true;
        }
    }

    public void setOrigine(CaseCarte origine){
        this.origine = origine;
        init();
    }

    @Override
    public void draw(Canvas canvas) {
        if(this.bitmap == null){
            init();
        }
        if(ready){
            canvas.drawBitmap(bitmap, canvas.getClipBounds(), canvas.getClipBounds(), null);
        }
    }

    public CaseCarte getOrigine() {
        return origine;
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
}
