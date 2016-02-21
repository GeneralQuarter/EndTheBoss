package virus.endtheboss.Vue;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import virus.endtheboss.Modele.Personnages.Personnage;
import virus.endtheboss.R;

/**
 * Created by Quentin Gangler on 12/02/2016.
 * Classe qui permet d'afficher la barre de vie d'un personnage
 */
public class HealthBar extends View {

    private int maxValue = 1;
    private int currentValue = 1;
    private Personnage p = null;
    private Paint paintBar;
    private Paint paintText;

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

    private synchronized void init(){
        paintBar = new Paint();
        paintBar.setColor(ContextCompat.getColor(this.getContext(), R.color.greenHealthBar));

        paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintText.setTypeface(Typeface.SANS_SERIF);
        paintText.setTextSize(30);
        paintText.setColor(Color.WHITE);
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);

        if(p != null){
            maxValue = p.getSaVitaliteMaximale();
            currentValue = p.getSaVitaliteCourante();
        }

        canvas.drawColor(ContextCompat.getColor(this.getContext(), R.color.redHealthBar));
        if(paintBar == null && paintText == null)
            init();
        if(paintBar != null && paintText != null) {
            canvas.drawRect(0, 0, ((float) currentValue / (float) maxValue) * canvas.getWidth(), canvas.getHeight(), paintBar);
            canvas.drawText(currentValue + "/" + maxValue + " PV", 10, ((canvas.getHeight() - 30) / 2) + 30, paintText);
        }
    }
}
