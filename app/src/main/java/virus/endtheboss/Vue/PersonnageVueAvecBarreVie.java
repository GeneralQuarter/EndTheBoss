package virus.endtheboss.Vue;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import virus.endtheboss.Enumerations.GameValues;
import virus.endtheboss.Modele.Personnages.Personnage;

/**
 * Created by Quentin Gangler on 14/02/2016.
 * Rajoute une barre de vie au personnage
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
        textp.setTextSize(20);

        canvas.drawText(
                personnage.getSaVitaliteCourante() + "/" + personnage.getSaVitaliteMaximale() + " PV",
                personnage.getX() * GameValues.tileWidth-15,
                (personnage.getY() * GameValues.tileHeight)-10,
                textp
        );

    }
}
