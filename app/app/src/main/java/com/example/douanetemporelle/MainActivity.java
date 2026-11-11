package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // 1- نصرّحو على العناصر متاع الواجهة
    EditText etNom, etNaissance, etDestination;
    RadioGroup rgMotif;
    Button btnInspecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 2- نرّبطو الكلاس بالـ layout متاعو
        setContentView(R.layout.activity_main);

        // 3- نجيبو كل عنصر بالـ id متاعو
        etNom = findViewById(R.id.et_nom_th);
        etNaissance = findViewById(R.id.et_naissance_th);
        etDestination = findViewById(R.id.et_destination_th);
        rgMotif = findViewById(R.id.rg_motif_th);
        btnInspecter = findViewById(R.id.btn_inspecter_th);

        // 4- كي المستخدم يضغط على الزر يصير هذا الكود
        btnInspecter.setOnClickListener(v -> {

            // 5- نقراو القيم اللي كتبها المستخدم
            String nom = etNom.getText().toString().trim();
            String naissanceTxt = etNaissance.getText().toString().trim();
            String destinationTxt = etDestination.getText().toString().trim();
            int idChoisi = rgMotif.getCheckedRadioButtonId(); // -1 يعني ما اختار حتى حاجة

            // 6- نتأكدو إلي كل الخانات معمّرة
            if (nom.isEmpty() || naissanceTxt.isEmpty()
                    || destinationTxt.isEmpty() || idChoisi == -1) {
                Toast.makeText(this, "عمّر كل الخانات من فضلك", Toast.LENGTH_SHORT).show();
                return; // نوقفو هنا ونطلعو من الدالة
            }

            // 7- نحوّلو السنين من نص إلى رقم
            int naissance = Integer.parseInt(naissanceTxt);
            int destination = Integer.parseInt(destinationTxt);

            // 8- سنة الوجهة لازم تكون أكبر من سنة الولادة
            if (destination <= naissance) {
                Toast.makeText(this, "سنة الوجهة لازم تكون أكبر من سنة الولادة", Toast.LENGTH_SHORT).show();
                return;
            }

            // 9- نعرفو الدافع حسب الـ RadioButton اللي اختارو
            String motif;
            if (idChoisi == R.id.rb_tourisme_th) {
                motif = "Tourisme";
            } else if (idChoisi == R.id.rb_modification_th) {
                motif = Cles.MOTIF_MODIF;
            } else {
                motif = "Fuite";
            }

            // 10- Intent explicite: نقولو من MainActivity إلى VerificationActivity
            Intent intent = new Intent(MainActivity.this, VerificationActivity.class);

            // 11- نبعثو المعلومات، والمفاتيح مبنية بالميثود static متاعنا
            intent.putExtra(Cles.cle(Cles.MATRICULE, "NOM_VOYAGEUR"), nom);
            intent.putExtra(Cles.cle(Cles.MATRICULE, "ANNEE_NAISSANCE"), naissance);
            intent.putExtra(Cles.cle(Cles.MATRICULE, "ANNEE_DESTINATION"), destination);
            intent.putExtra(Cles.cle(Cles.MATRICULE, "MOTIF"), motif);

            // 12- نلانسيو الـ Activity الثانية
            startActivity(intent);
        });
    }
}
