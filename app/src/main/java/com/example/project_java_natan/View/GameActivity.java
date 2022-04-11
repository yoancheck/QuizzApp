package com.example.project_java_natan.View;


import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_java_natan.Model.Question;
import com.example.project_java_natan.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hotmail.or_dvir.easysettings.pojos.EasySettings;
import com.hotmail.or_dvir.easysettings.pojos.SettingsObject;

import java.util.List;


public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    TextView question;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    int indexQuestion;
    int score;
    List<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.indexQuestion = 0;
        this.score = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        String value = EasySettings.retrieveSettingsSharedPrefs(this).getString("theme", "theme");
        db.collection(value)
                .get()
                .addOnSuccessListener(documentSnapshots -> {
                    if (documentSnapshots.isEmpty()) {
                        Log.d(TAG, "onSuccess: LIST EMPTY");
                        return;
                    } else {
                        question.setText(documentSnapshots.getDocuments().get(0).toString());
                        this.questions = documentSnapshots.toObjects(Question.class);
                        this.setQuestion(indexQuestion);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error getting data!!!", Toast.LENGTH_LONG).show();
                    }
                });
        question = findViewById(R.id.question);
        btn1 = findViewById(R.id.op1);
        btn2 = findViewById(R.id.op2);
        btn3 = findViewById(R.id.op3);
        btn4 = findViewById(R.id.op4);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.op1:
                if (this.questions.get(indexQuestion).correctAnswer == 0) {
                    this.score += 10;
                }
            case R.id.op2:
                if (this.questions.get(indexQuestion).correctAnswer == 1) {
                    this.score += 10;
                    Toast.makeText(GameActivity.this, "Great", Toast.LENGTH_SHORT).show();
                }
            case R.id.op3:
                if (this.questions.get(indexQuestion).correctAnswer == 2) {
                    this.score += 10;
                    Toast.makeText(GameActivity.this, "Great", Toast.LENGTH_SHORT).show();
                }
            case R.id.op4:
                if (this.questions.get(indexQuestion).correctAnswer == 3) {
                    this.score += 10;
                    Toast.makeText(GameActivity.this, "Great", Toast.LENGTH_SHORT).show();
                }
        }
        this.indexQuestion++;
        if (indexQuestion < 2)
            setQuestion(indexQuestion);
        else {
            gameOver();
            this.indexQuestion = 0;
        }
    }

    public void setQuestion(int numberOfQuestion) {
        question.setText(this.questions.get(numberOfQuestion).question);
        btn1.setText(this.questions.get(numberOfQuestion).response.get(0));
        btn2.setText(this.questions.get(numberOfQuestion).response.get(1));
        btn3.setText(this.questions.get(numberOfQuestion).response.get(2));
        btn4.setText(this.questions.get(numberOfQuestion).response.get(3));
    }

    private void gameOver() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(GameActivity.this);

        if (this.score == 20) {
            alertDialogBuilder.
                    setMessage("כל הכבוד! התוצאה היא: " + score);
        } else {
            alertDialogBuilder.
                    setMessage("הפעם לא הצלחת, יאללה תנסה שוב \n תוצאה: " + score);
        }
        this.score = 0;
        alertDialogBuilder.setPositiveButton("שחק שוב", (dialogInterface, i) -> startActivity(new Intent(getApplicationContext(), GameActivity.class)))
                .setNegativeButton(" Logout יציאה",
                        (dialogInterface, i) -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}