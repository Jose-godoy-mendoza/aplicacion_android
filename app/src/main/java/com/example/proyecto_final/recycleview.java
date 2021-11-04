package com.example.proyecto_final;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.proyecto_final.Interfaz.Post_prueba;
import com.example.proyecto_final.Models.prueba;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class recycleview extends AppCompatActivity {
    LinearLayout segundo_layout;
    TextView txt_id, txt_UserId, txt_title, txt_body, idtexto;
    Button btn_buscar;
    EditText palabra_buscar;
    RecyclerView recycle_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
        segundo_layout=findViewById(R.id.layout_segundo);
        txt_id=findViewById(R.id.txt_Id);
        txt_UserId=findViewById(R.id.txt_UserId);
        txt_title=findViewById(R.id.txt_title);
        txt_body=findViewById(R.id.txt_body);
        btn_buscar=findViewById(R.id.Buscar);
        palabra_buscar=findViewById(R.id.palabra_buscar);
        recycle_view=findViewById(R.id.recycle_view);
        idtexto=findViewById(R.id.Idtexto);

        recycle_view=findViewById(R.id.recycle_view);
        recycle_view.setHasFixedSize(true);
        recycle_view.setLayoutManager(new LinearLayoutManager(this));

       /* prueba[] prueba = new prueba[]
                {
                        new prueba(1,1,"prueba1" ,"body1"),
                        new prueba(2,2,"prueba2" ,"body2")
                };
        Adaptador adaptador = new Adaptador(prueba, recycleview.this);
        recycle_view.setAdapter(adaptador);*/

        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //texto_tarjeta.setText("");
                idtexto.setText("");
                busqueda(palabra_buscar.getText().toString());

            }
        });
    }



    public void busqueda(String id)
    {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(GsonConverterFactory.create()).build();
        Post_prueba post_prueba = retrofit.create(Post_prueba.class);
        Call<List<prueba>> call = post_prueba.find(id);
        call.enqueue(new Callback<List<prueba>>() {
            @Override
            public void onResponse(Call<List<prueba>> call, Response<List<prueba>> response) {
                List<prueba> lista_busqueda = response.body();
                int i=0;
                for(prueba pr:lista_busqueda)
                {
                    /*String content="";
                    content+="UserId: "+pr.getUserId() + "\n";
                    content+="ID: "+pr.getId() + "\n";
                    content+="Title: "+pr.getTitle() + "\n";
                    content+="Body: "+pr.getBody() + "\n\n";
                    idtexto.append(content);*/

                    prueba[] prueba = new prueba[]
                            {
                                    new prueba(Integer.valueOf(pr.getUserId()),Integer.valueOf(pr.getId()),"TITULO: "+pr.getTitle() ,"BODY: "+pr.getBody()),

                            };
                    Adaptador adaptador = new Adaptador(prueba, recycleview.this);
                    recycle_view.setAdapter(adaptador);
                }
            }

            @Override
            public void onFailure(Call<List<prueba>> call, Throwable t) {
                idtexto.setText(t.getMessage());
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void expand(View view) {
        /*int v=(txt_UserId.getVisibility()==View.GONE)?View.VISIBLE:View.GONE;
        TransitionManager.beginDelayedTransition(segundo_layout, new AutoTransition());
        txt_UserId.setVisibility(v);

        int v1=(txt_title.getVisibility()==View.GONE)?View.VISIBLE:View.GONE;
        TransitionManager.beginDelayedTransition(segundo_layout, new AutoTransition());
        txt_title.setVisibility(v1);

        int v2=(txt_body.getVisibility()==View.GONE)?View.VISIBLE:View.GONE;
        TransitionManager.beginDelayedTransition(segundo_layout, new AutoTransition());
        txt_body.setVisibility(v2);*/
    }
}