package com.raywenderlich.galacticon;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.support.v7.widget.RecyclerView.*;


/**
 * Created by alexander on 2017-06-26.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.PhotoHolder>{

    private ArrayList<Photo> mPhotos;


    public RecyclerAdapter(ArrayList<Photo> photos){
        mPhotos = photos;
    }


    @Override
    public RecyclerAdapter.PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_row, parent , false);
        return new PhotoHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.PhotoHolder holder, int position) {
        Photo itemPhoto = mPhotos.get(position);
        holder.bindPhoto(itemPhoto);
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }

    public static class PhotoHolder extends ViewHolder implements View.OnClickListener{

        private ImageView mItemImage;
        private TextView mItemDate;
        private TextView mItemDescription;
        private Photo mPhoto;

        private static final String PHOTO_KEY = "PHOTO";

        public PhotoHolder(View itemView) {
            super(itemView);

            mItemImage = (ImageView) itemView.findViewById(R.id.item_image);
            //mItemDate = (TextView) itemView.findViewById(R.id.item_date);
            //mItemDescription = (TextView) itemView.findViewById(R.id.item_description);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            Context context = itemView.getContext();
            Intent showPhotoIntent = new Intent(context, PhotoActivity.class);
            showPhotoIntent.putExtra(PHOTO_KEY , mPhoto);
            context.startActivity(showPhotoIntent);
        }

        public void bindPhoto(Photo photo) {
            mPhoto = photo;
            Log.d("url", photo.getHumanDate());
            Picasso.with(mItemImage.getContext()).load(photo.getUrl()).into(mItemImage);
           // mItemDate.setText(photo.getHumanDate());
            //mItemDescription.setText(photo.getExplanation());
        }
    }
}
