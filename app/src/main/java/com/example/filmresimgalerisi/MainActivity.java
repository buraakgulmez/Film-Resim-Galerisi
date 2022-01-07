 package com.example.filmresimgalerisi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

 public class MainActivity extends AppCompatActivity {
    Spinner spinner1;
    ImageView imageView1;
    TextView textView1;
    ProgressBar progressBar1;
    Button button5,button6;
    EditText editTextText1;
    String[] yon={"İleri","Geri"};
    int [] resimler={R.drawable.mood,R.drawable.portrait,R.drawable.sanstoit,R.drawable.oli,R.drawable.somethinguse,
    R.drawable.oliv,R.drawable.theslience,R.drawable.olive,R.drawable.oncayoksulluk,R.drawable.manchester,
    R.drawable.normalpeople,R.drawable.before,R.drawable.shortfilmaboutlove,R.drawable.soundofmetal,
    R.drawable.thefaultinourstars,R.drawable.beginners1,R.drawable.beginners2};

    String[] aciklamalar={"Wong Kar Wai","Céline Sciamma","Agnes Varda","Jane Anderson","Pelin Esmer",
    "Jane Anderson","Muhsin Mahmelbaf","Jane Anderson","Edoardo Ponti","Kenneth Lonergan","Hettie MacDonald",
    "Richard Linklater","Krzysztof Kieślowski","Darius Marder","Josh Boone","Mike Mills","Mike Mills"};
    int sira=0;
    Timer zamanlayıcı;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        resim_goster();

    }
    public void init()
    {
        spinner1=findViewById(R.id.spinner1);
        imageView1=findViewById(R.id.imageView1);
        textView1=findViewById(R.id.textView1);
        progressBar1=findViewById(R.id.progressBar1);
        button5=findViewById(R.id.button5);
        button6=findViewById(R.id.button6);
        editTextText1=findViewById(R.id.editTextText1);
        ArrayAdapter<String> adaptor=new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_dropdown_item,yon);
        spinner1.setAdapter(adaptor);
        progressBar1.setMax(resimler.length);
        button6.setEnabled(false);
    }
    public void resim_goster()
    {
        imageView1.setImageResource(resimler[sira]);
        textView1.setText(aciklamalar[sira]);
        progressBar1.setProgress(sira+1);
    }

     public void ilk_resim(View view) {
        sira=0;
        resim_goster();
     }

     public void son_resim(View view) {
        sira=resimler.length-1;
        resim_goster();
     }

     public void sonraki_resim(View view) {
        sira++;
        if(sira==resimler.length)
            sira=0;
        resim_goster();
     }

     public void önceki_resim(View view) {
        sira--;
        if(sira==-1)
            sira=resimler.length-1;
        resim_goster();
     }

     public void timer_baslat(View view) {
        int saniye=Integer.parseInt(editTextText1.getText().toString());
        zamanlayıcı=new Timer();
         TimerTask gorev=new TimerTask() {
             @Override
             public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(spinner1.getSelectedItem().toString()=="İleri")
                            sonraki_resim(view);
                        else
                            önceki_resim(view);
                    }
                });
             }
         };
         zamanlayıcı.schedule(gorev,0,saniye*1000);
         button5.setEnabled(false);
         button6.setEnabled(true);
     }

     public void timer_durdur(View view) {
        zamanlayıcı.cancel();
         button5.setEnabled(true);
         button6.setEnabled(false);
     }
 }