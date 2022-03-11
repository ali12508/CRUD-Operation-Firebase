package com.ui.firebasecrud;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.Inflater;

import de.hdodenhof.circleimageview.CircleImageView;

public class UsersAdapter extends FirebaseRecyclerAdapter<User,UsersAdapter.myviewholder> {

   Context context;
   ArrayList<User>users;

    public UsersAdapter(@NonNull FirebaseRecyclerOptions<User> options, Context context) {
        super(options);
        this.context = context;
    }




//
//    public UsersAdapter(FirebaseRecyclerOptions<User> options, RecylerViewActivity context) {
//        super(options);
//        this.context = context;
//    }


    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull final User model) {
//         User user=users.get(position);
         holder.t1.setText(model.getName());
        holder.t2.setText(model.getPhone());
         holder.t3.setText(model.getAge());
        Picasso.get().load(model.getImage()).error(R.drawable.ic_launcher_background).into(holder.img);
        holder.updat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {            //update dialog box
                DialogPlus dialog = DialogPlus.newDialog(holder.img.getContext()).setContentHolder(new ViewHolder(R.layout.dialog)).setExpanded(true,800)
                        .create();
                View view1=dialog.getHolderView();
                EditText imgurl=view1.findViewById(R.id.pimglink);     //views dialogs.xml
                EditText name=view1.findViewById(R.id.pname);
                EditText age=view1.findViewById(R.id.page);
                EditText pno=view1.findViewById(R.id.pphone);
                Button button=view1.findViewById(R.id.buttonupdate);
                imgurl.setText(model.getImage());       //get data from modal
                name.setText(model.getName());
                age.setText(model.getAge());
                pno.setText(model.getPhone());
                dialog.show();
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        HashMap<String, Object> obj = new HashMap<>();        //update values in child
                        obj.put("name", name.getText().toString());
                        obj.put("image",imgurl.getText().toString());
                        obj.put("phone", age.getText().toString());
                        obj.put("age", pno.getText().toString());
                        FirebaseDatabase.getInstance().getReference().child("Users").child(getRef(position).getKey())    //gets position of current child by key value
                                .updateChildren(obj).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                dialog.dismiss();
//                                Toast.makeText(UsersAdapter.this, " Data Updated....", Toast.LENGTH_LONG).show();

                            }


                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });

                    }
                });
            }
        });

        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialog=new AlertDialog.Builder(holder.img.getContext());
                alertDialog.setTitle("Delete Data");
                alertDialog.setMessage("Delete");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Users").child(getRef(position).getKey()).removeValue();
                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                 alertDialog.show();
            }
        });
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context,RecylerViewOnClick_Activity.class);
                intent.putExtra("name",model.getName());
                intent.putExtra("image",model. getImage());
                intent.putExtra("phone",model.getPhone());
                intent.putExtra("age",model.getAge());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
       return new myviewholder(v);
//        return v;
    }

    public class myviewholder extends RecyclerView.ViewHolder{
       TextView t1,t2,t3 ,updat,del;
       CircleImageView img;
        public myviewholder(@NonNull View itemView) {


                super(itemView);
                img=itemView.findViewById(R.id.fimg);
                t1=itemView.findViewById(R.id.fnametext);
                t2=itemView.findViewById(R.id.fphoneNo);
                t3=itemView.findViewById(R.id.fAge);
               updat=itemView.findViewById(R.id.update);
               del=itemView.findViewById(R.id.delete);

        }
    }
}
