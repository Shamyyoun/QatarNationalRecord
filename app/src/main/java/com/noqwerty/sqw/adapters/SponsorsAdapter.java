package com.noqwerty.sqw.adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.noqwerty.sqw.AppController;
import com.noqwerty.sqw.R;
import com.noqwerty.sqw.datamodels.Constants;
import com.noqwerty.sqw.datamodels.Sponsor;
import com.noqwerty.sqw.utils.FileUtil;
import com.noqwerty.sqw.utils.ViewUtil;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

/**
 * Created by Shamyyoun on 2/8/2015.
 */
public class SponsorsAdapter extends RecyclerView.Adapter<SponsorsAdapter.ViewHolder> {
    private Activity activity;
    private List<Sponsor> data;
    private int layoutResourceId;

    private OnItemClickListener onItemClickListener;

    public SponsorsAdapter(Activity activity, List<Sponsor> data, int layoutResourceId) {
        this.activity = activity;
        this.data = data;
        this.layoutResourceId = layoutResourceId;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Sponsor sponsor = data.get(position);

        holder.textName.setText(sponsor.getName());
        holder.textPosition.setText("" + position);
        sponsor.setVisible(true);

        // check if logo exists in HD or not
        String logoPath = activity.getFilesDir() + "/"
                + Constants.FOLDER_SPONSORS_IMAGES + "/"
                + sponsor.getLogoName();
        File file = new File(logoPath);

        if (file.exists()) {
            // load it from HD
            new LogoLoader(sponsor, file, holder).execute();
        } else {
            // download it first
            new LogoDownloader(sponsor, holder).execute();
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                layoutResourceId, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    public void setOnItemClickListener(
            final OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textName;
        ImageView imageThumbnail;
        TextView textPosition;

        public ViewHolder(View v) {
            super(v);
            textName = (TextView) v.findViewById(R.id.text_name);
            imageThumbnail = (ImageView) v.findViewById(R.id.image_thumbail);
            textPosition = (TextView) v.findViewById(R.id.text_position);

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, getPosition());
            }
        }
    }

    /*
     * used to load logo from HD
	 */
    private class LogoLoader extends AsyncTask<Void, Void, Void> {
        private Sponsor sponsor;
        private File logoFile;
        private ViewHolder viewHolder;

        private Bitmap logo;

        public LogoLoader(Sponsor sponsor, File logoFile, ViewHolder viewHolder) {
            this.sponsor = sponsor;
            this.logoFile = logoFile;
            this.viewHolder = viewHolder;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            ViewUtil.showView(viewHolder.imageThumbnail, false, 0);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                logo = Picasso.with(activity).load(logoFile).get();
            } catch (Exception e) {
                e.printStackTrace();
                logo = BitmapFactory.decodeResource(activity.getResources(),
                        R.drawable.def_photo);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            // check if item is visible
            if (sponsor.isVisible()) {
                viewHolder.imageThumbnail.setImageBitmap(logo);
                ViewUtil.showView(viewHolder.imageThumbnail, true, 0);
            }
        }
    }

    /*
     * used to download logo from server
     */
    private class LogoDownloader extends AsyncTask<Void, Void, Void> {
        private Sponsor sponsor;
        private ViewHolder viewHolder;

        private Bitmap logo;

        public LogoDownloader(Sponsor sponsor, ViewHolder viewHolder) {
            this.sponsor = sponsor;
            this.viewHolder = viewHolder;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            ViewUtil.showView(viewHolder.imageThumbnail, false);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                int width = (int) activity.getResources().getDimension(
                        R.dimen.sponsors_item_thumbnail_width);

                logo = Picasso
                        .with(activity)
                        .load(AppController.SPONSORS_INAGES_LINK
                                + sponsor.getLogoName()).resize(width, width)
                        .get();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            // check if logo has been downloaded or not
            if (logo != null) {
                // downloaded >> save it
                FileUtil.saveImage(activity, logo,
                        Constants.FOLDER_SPONSORS_IMAGES, sponsor.getLogoName());
            } else {
                // download failed >> get default image
                logo = BitmapFactory.decodeResource(activity.getResources(),
                        R.drawable.def_photo);
            }

            // check if item visible
            if (sponsor.isVisible()) {
                viewHolder.imageThumbnail.setImageBitmap(logo);
                ViewUtil.showView(viewHolder.imageThumbnail, true);
            }
        }
    }

}