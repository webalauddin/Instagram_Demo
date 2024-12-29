package com.mastercode.instagram;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainFragment extends Fragment {




    RecyclerView recyclerView;
    HashMap<String, String> hashMap;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

    RecyclerView recyclerView2;
    HashMap<String, String> hashMap2;
    ArrayList<HashMap<String, String>> arrayList2 = new ArrayList<>();
    ProgressBar progress;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_main, container, false);

        recyclerView = myView.findViewById(R.id.recyclerView);
        recyclerView2 = myView.findViewById(R.id.recyclerView2);
        progress = myView.findViewById(R.id.progress);


        progress.setVisibility(View.VISIBLE);
        String url = "https://webalauddin.com/practise/instagram.json";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progress.setVisibility(View.GONE);

                Log.d("response", response.toString());

                try {

                    for(int x=0; x<response.length(); x++){


                        JSONObject jsonObject = response.getJSONObject(x);

                        String name = jsonObject.getString("name");
                        String location = jsonObject.getString("location");
                        String profileImage = jsonObject.getString("profileImage");
                        String image = jsonObject.getString("image");
                        String storyImage = jsonObject.getString("storyImage");
                        String storyText = jsonObject.getString("storyText");


                        hashMap = new HashMap<>();
                        hashMap.put("storyImage", storyImage);
                        hashMap.put("storyText", storyText);
                        arrayList.add(hashMap);

                        hashMap2 = new HashMap<>();
                        hashMap2.put("name", name);
                        hashMap2.put("location", location);
                        hashMap2.put("profileImage", profileImage);
                        hashMap2.put("image", image);
                        arrayList2.add(hashMap2);



                    }

                    recyclerView.setAdapter(new MyAdapter());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

                    recyclerView2.setAdapter(new MyAdapter2());
                    recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));




                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progress.setVisibility(View.GONE);
                Log.d("response", error.getMessage().toString());

            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);



        return myView;
    }
    //=================================================================onCreate END
    //=================================================================onCreate END


    private class MyAdapter extends RecyclerView.Adapter <MyAdapter.ViewHolder>{



        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        private class ViewHolder extends RecyclerView.ViewHolder{
            CircleImageView storyImage;
            TextView storyText;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                storyImage = itemView.findViewById(R.id.storyImage);
                storyText = itemView.findViewById(R.id.storyText);

            }
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater inflater = getLayoutInflater();
            View myView = inflater.inflate(R.layout.horizontallayout, parent, false);


            return new ViewHolder(myView);


        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


            hashMap= arrayList.get(position);
            String storyImage = hashMap.get("storyImage");
            String storyText = hashMap.get("storyText");



            holder.storyText.setText(storyText);
            Picasso.get().load(storyImage).into(holder.storyImage);

        }

    }
//================================================================================================//

    private class MyAdapter2 extends RecyclerView.Adapter <MyAdapter2.ViewHolder>{


        @Override
        public int getItemCount() {
            return arrayList2.size();
        }

        private class ViewHolder extends RecyclerView.ViewHolder{
            ImageView image;
            CircleImageView profileImage;
            TextView name, location;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                profileImage = itemView.findViewById(R.id.profileImage);
                image = itemView.findViewById(R.id.image);
                name = itemView.findViewById(R.id.name);
                location = itemView.findViewById(R.id.location);
            }
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater inflater = getLayoutInflater();
            View myView = inflater.inflate(R.layout.item_layout, parent, false);


            return new ViewHolder(myView);


        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


            hashMap2 = arrayList2.get(position);
            String name = hashMap2.get("name");
            String location = hashMap2.get("location");
            String image = hashMap2.get("image");
            String profileImage = hashMap2.get("profileImage");


            holder.name.setText(name);
            holder.location.setText(location);
            Picasso.get().load(image).into(holder.image);
            Picasso.get().load(profileImage).into(holder.profileImage);

        }

    }
//================================================================================================//





}