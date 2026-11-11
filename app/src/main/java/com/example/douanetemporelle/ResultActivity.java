package com.example.douanetemporelle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        LinearLayout racine = findViewById(R.id.ll_racine_th);
        TextView tvMessage = findViewById(R.id.tv_message_th);
        Button btnNouveau = findViewById(R.id.btn_nouveau_th);

        // 1- نستقبلو النتيجة والاسم (false هي القيمة الافتراضية)
        Intent recu = getIntent();
        boolean verdict = recu.getBooleanExtra(Cles.cle(Cles.MATRICULE, "VERDICT"), false);
        String nom = recu.getStringExtra(Cles.cle(Cles.MATRICULE, "NOM_VOYAGEUR"));

        // 2- نبدّلو لون الخلفية والرسالة حسب النتيجة
        if (verdict) {
            racine.setBackgroundColor(Color.parseColor("#2E7D32")); // أخضر
            tvMessage.setText("Bon voyage " + nom + " !");
        } else {
            racine.setBackgroundColor(Color.parseColor("#C62828")); // أحمر
            tvMessage.setText(nom + " est en état d'arrestation temporelle !");
        }

        // 3- زر "Nouveau voyageur": نرجعو لـ MainActivity ونسكرو كل اللي قبل
        btnNouveau.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            // CLEAR_TASK + NEW_TASK = نفسخو كل الـ Activities اللي فاتت ونبداو من جديد
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish(); // نسكرو هاذي زادة
        });
    }
}
