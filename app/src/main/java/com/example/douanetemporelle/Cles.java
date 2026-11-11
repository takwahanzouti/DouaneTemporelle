package com.example.douanetemporelle;

public class Cles {

    // 1- المطريكول (آخر 4 أرقام من بطاقة الطالب) - بدّلو بتاعك
    public static final String MATRICULE = "5661";

    // 2- النص متاع الدافع "Modification du passé" نكتبوه مرة وحدة هنا
    public static final String MOTIF_MODIF = "Modification du passé";

    // 3- ميثود static تبني المفتاح: المطريكول + _ + الاسم
    //    مثال: cle("5661", "NOM_VOYAGEUR") ترجع "5661_NOM_VOYAGEUR"
    public static String cle(String matricule, String nomCle) {
        return matricule + "_" + nomCle;
    }
}
