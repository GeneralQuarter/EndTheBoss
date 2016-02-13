package virus.endtheboss.Vue;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import virus.endtheboss.Modele.Personnage;
import virus.endtheboss.R;

/**
 * Created by Quentin Gangler on 12/02/2016.
 * Classe qui permet d'afficher la barre de vie d'un personnage
 */
public class HealthBar extends View {

    private int maxValue = 1;
    private int currentValue = 1;
    private Personnage p = null;

    public HealthBar(Context context) {
        super(context);
    }

    public HealthBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HealthBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setPersonnage(Personnage p){
        this.p = p;
    }

    public void update(){
        this.postInvalidate();
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);

        if(p != null){
            maxValue = p.getSaVitaliteMaximale();
            currentValue = p.getSaVitaliteCourante();
        }

        canvas.drawColor(getResources().getColor(R.color.redHealthBar));
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(getResources().getColor(R.color.greenHealthBar));
        canvas.drawRect(0, 0, ((float) currentValue / (float) maxValue) * canvas.getWidth(), canvas.getHeight(), p);
        p.setColor(Color.WHITE);
        p.setTypeface(Typeface.SANS_SERIF);
        p.setTextSize(30);
        canvas.drawText(currentValue + "/" + maxValue + " PV", 10, ((canvas.getHeight()-30)/2)+30, p);
    }
}
