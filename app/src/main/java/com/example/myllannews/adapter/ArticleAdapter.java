package com.example.myllannews.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myllannews.R;
import com.example.myllannews.model.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {
    private List<Article> articles;
    private int rowLayout;
    private Context context;

    public static final String IMAGE_URL_BASE_PATH = "https://techcrunch.com/wp-content/uploads/2018/03/gettyimages-669889288.jpg?w=593";

    public ArticleAdapter(List<Article> articles, int rowLayout,Context context){
        this.articles = articles;
        this.rowLayout = rowLayout;
        this.context = context;
    }
    public static class ArticleViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView title;
        TextView author;
        TextView description;
        ImageView newsImage;

        public ArticleViewHolder(View v){
            super(v);
            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            newsImage = (ImageView) v.findViewById(R.id.news_image);
            title = (TextView) v.findViewById(R.id.title);
            author = (TextView) v.findViewById(R.id.author);
            description = (TextView) v.findViewById(R.id.description);
        }
    }
    @Override
    public ArticleAdapter.ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
        return new ArticleViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ArticleViewHolder holder, final int position){
        String image_url =  articles.get(position).getUrlToImage();
        Picasso.get()
                .load(image_url)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(holder.newsImage);
        holder.title.setText(articles.get(position).getTitle());
        holder.author.setText(articles.get(position).getAuthor());
        holder.description.setText(articles.get(position).getDescription());
    }
    @Override
    public int getItemCount(){
        return articles.size();
    }
}
