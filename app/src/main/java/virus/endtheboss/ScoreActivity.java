package virus.endtheboss;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import virus.endtheboss.Modele.Score;

/**
 * Created by Quentin Gangler on 17/03/2016.
 */
public class ScoreActivity extends Activity {

    private ScoreHelper scoreHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        scoreHelper = new ScoreHelper(this);

        (findViewById(R.id.buttonResetScores)).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreHelper.resetScores();
                updateListScores();
            }
        });

        (findViewById(R.id.buttonScoreRetourMenu)).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoreActivity.this, ChoixActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        updateListScores();
    }

    private void updateListScores(){
        List<Score> scores = scoreHelper.getScores();

        LinearLayout scoresContainer = (LinearLayout) findViewById(R.id.scoreContainerLayout);

        scoresContainer.removeAllViews();

        for(Score score : scores){
            TextView scoreTextView = new TextView(this);
            scoreTextView.setText(score.getNomGagnant() + " à gagné en " + score.getTempsJeu());
            if(score.isWinner()){
                scoreTextView.setTextColor(Color.GREEN);
            }else{
                scoreTextView.setTextColor(Color.RED);
            }
            scoresContainer.addView(scoreTextView);
        }
    }
}
