package virus.endtheboss.Vue;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import virus.endtheboss.Enumerations.GameValues;
import virus.endtheboss.Modele.Personnage;

/**
 * Created by Quentin Gangler on 14/02/2016.
 */
public class PersonnageVueAvecBarreVie extends PersonnageVue {

    public PersonnageVueAvecBarreVie(Context context, Personnage personnage){
        super(context, personnage);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        Paint textp = new Paint(Paint.ANTI_ALIAS_FLAG);
        textp.setColor(Color.WHITE);
        textp.setTypeface(Typeface.SANS_SERIF);
        textp.setTextSize(12);

        canvas.drawText(
                personnage.getSaVitaliteCourante() + "/" + personnage.getSaVitaliteMaximale() + " PV",
                personnage.getX() * GameValues.tileWidth,
                ((personnage.getY()-1) * GameValues.tileWidth)-30,
                textp
        );

    }
}
