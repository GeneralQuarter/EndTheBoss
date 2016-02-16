package virus.endtheboss;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Quentin Gangler on 16/02/2016.
 */
public class ChoixActivity extends Activity {

    Button buttonConnexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix);

        buttonConnexion = (Button) findViewById(R.id.button_connexion);

        buttonConnexion.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToGame();
            }
        });
    }

    private void switchToGame(){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}
