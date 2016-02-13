package virus.endtheboss.Vue;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import java.util.List;

import virus.endtheboss.Enumerations.GameValues;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Formes.Forme;

/**
 * Created by Quentin Gangler on 13/02/2016.
 */
public class FormeVue extends Drawable {

    private Forme f;
    private CaseCarte origine;
    private Canvas canvas;
    private Bitmap bitmap;
    private boolean ready;

    public FormeVue(Forme f, CaseCarte origine){
        this.f = f;
        this.origine = origine;
        this.ready = false;
    }

    private void init(){
        Paint p = new Paint();
        p.setColor(Color.WHITE);
        p.setAlpha(50);
        for(CaseCarte cc : f.getForme(origine)){
            canvas.drawRect(cc.getX() * GameValues.tileWidth+1, cc.getY() * GameValues.tileHeight+1, (cc.getX()+1)*GameValues.tileWidth, (cc.getY()+1)*GameValues.tileHeight, p);
        }
        ready = true;
    }

    @Override
    public void draw(Canvas canvas) {
        if(this.canvas == null){
            bitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
            this.canvas = new Canvas(bitmap);
            init();
        }
        if(ready){
            canvas.drawBitmap(bitmap, this.canvas.getClipBounds(), canvas.getClipBounds(), null);
        }
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
