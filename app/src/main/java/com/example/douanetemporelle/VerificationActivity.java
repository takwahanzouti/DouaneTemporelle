package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
// 4- هذي interface الثالثة
public class VerificationActivity extends AppCompatActivity {
// 10- هذي الشاشة الثانية: الـ passeport اللي نراجعو فيه بيانات المسافر
    TextView tvNom, tvAge, tvTrajet, tvMotif;
    Button btnApprouver, btnRefuser;
    String nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        // 1- نجيبو العناصر من الـ layout
        tvNom = findViewById(R.id.tv_nom_th);
        tvAge = findViewById(R.id.tv_age_th);
        tvTrajet = findViewById(R.id.tv_trajet_th);
        tvMotif = findViewById(R.id.tv_motif_resume_th);
        btnApprouver = findViewById(R.id.btn_approuver_th);
        btnRefuser = findViewById(R.id.btn_refuser_th);

        // 2- نستقبلو الـ Intent اللي جاء من MainActivity
        Intent recu = getIntent();
        nom = recu.getStringExtra(Cles.cle(Cles.MATRICULE, "NOM_VOYAGEUR"));
        int naissance = recu.getIntExtra(Cles.cle(Cles.MATRICULE, "ANNEE_NAISSANCE"), 0);
        int destination = recu.getIntExtra(Cles.cle(Cles.MATRICULE, "ANNEE_DESTINATION"), 0);
        String motif = recu.getStringExtra(Cles.cle(Cles.MATRICULE, "MOTIF"));

        // 3- نحسبو العمر الزمني = سنة الوجهة - سنة الولادة
        int difference = destination - naissance;

        // 4- نعرضو الملخص في الـ "passeport"
        tvNom.setText("Nom : " + nom);
        tvAge.setText("Âge temporel : " + difference + " ans");
        tvTrajet.setText("Trajet : " + naissance + " → " + destination);
        tvMotif.setText("Motif : " + motif);

        // 5- الشرط: إذا الدافع "Modification du passé" و الفرق عدد أولي
        //    نعطّلو زر "Approuver" من أول ما تتحل الشاشة
        if (Cles.MOTIF_MODIF.equals(motif) && estPremier(difference)) {
            btnApprouver.setEnabled(false);
        }

        // 6- زر الموافقة: نبعثو verdict = true
        btnApprouver.setOnClickListener(v -> ouvrirResultat(true));

        // 7- زر الرفض: نبعثو verdict = false
        btnRefuser.setOnClickListener(v -> ouvrirResultat(false));
    }

    // 8- ميثود تحلّ الـ Activity 3 و تبعث النتيجة + الاسم
    private void ouvrirResultat(boolean verdict) {
        Intent intent = new Intent(VerificationActivity.this, ResultActivity.class);
        intent.putExtra(Cles.cle(Cles.MATRICULE, "VERDICT"), verdict);
        intent.putExtra(Cles.cle(Cles.MATRICULE, "NOM_VOYAGEUR"), nom);
        startActivity(intent);
    }

    // 9- ميثود تتأكد إذا الرقم أولي (يتقسم برك على 1 و على روحو)
    private boolean estPremier(int n) {
        if (n < 2) return false; // 0 و 1 و السوالب ماهمش أوليين
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false; // لقينا قاسم، إذن ماهوش أولي
        }
        return true;
    }
}
