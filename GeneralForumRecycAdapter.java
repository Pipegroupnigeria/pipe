package com.pipe.www.pipe.adapter;

import android.content.Context;
import android.net.Uri;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.google.android.gms.maps.internal.IMapFragmentDelegate;
import com.pipe.www.pipe.R;
import com.pipe.www.pipe.model.Post;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Set;

public class GeneralForumRecycAdapter extends RecyclerView.Adapter {


    private final LayoutInflater inflater;
    private Context context;
    private ArrayList<Post> postSet;


    private final int TEXT = 0;
    private final int IMAGE = 1;
    private final int VIDEO = 2;

    private int type = 0;



    public GeneralForumRecycAdapter(Context context, ArrayList<Post> postSet){

        this.context = context;
        this.postSet = postSet;
        inflater = LayoutInflater.from(context);

    }





    class ViewHolderText extends RecyclerView.ViewHolder{


        private final ImageView childimage;
        private final TextView childname;
        private final TextView textpost;
        private final ImageView likeIcon;
        private final ImageView commenticon;
        private final ImageView replyicon;
        private final TextView likecount;
        private final TextView commentcount;
        private final TextView commentname;
        private final TextView commentcontent;



        public ViewHolderText(View i) {
            super(i);

            childimage = (ImageView)i.findViewById(R.id.general_child_image);
            childname = (TextView)i.findViewById(R.id.general_child_name);

            textpost = (TextView)i.findViewById(R.id.general_child_text_post);

            likeIcon = (ImageView)i.findViewById(R.id.general_child_likes_icon);
            commenticon = (ImageView)i.findViewById(R.id.general_child_comment_icon);
            replyicon = (ImageView)i.findViewById(R.id.general_child_reply_icon);

            likecount = (TextView)i.findViewById(R.id.general_child_likes_count);
            commentcount = (TextView)i.findViewById(R.id.general_child_comment_count);

            commentname = (TextView)i.findViewById(R.id.general_child_comment_name);
            commentcontent = (TextView)i.findViewById(R.id.general_child_comment_content);




        }
    }

    class ViewHolerImage extends RecyclerView.ViewHolder{


        private final ImageView childimage;
        private final TextView childname;
        private final ImageView imagepost;
        private final ImageView likeIcon;
        private final ImageView commenticon;
        private final ImageView replyicon;
        private final TextView likecount;
        private final TextView commentcount;
        private final TextView commentname;
        private final TextView commentcontent;

        public ViewHolerImage(View i) {
            super(i);


            childimage = (ImageView)i.findViewById(R.id.general_child_image);
            childname = (TextView)i.findViewById(R.id.general_child_name);

            imagepost = (ImageView) i.findViewById(R.id.general_child_image_post);

            likeIcon = (ImageView)i.findViewById(R.id.general_child_likes_icon);
            commenticon = (ImageView)i.findViewById(R.id.general_child_comment_icon);
            replyicon = (ImageView)i.findViewById(R.id.general_child_reply_icon);

            likecount = (TextView)i.findViewById(R.id.general_child_likes_count);
            commentcount = (TextView)i.findViewById(R.id.general_child_comment_count);

            commentname = (TextView)i.findViewById(R.id.general_child_comment_name);
            commentcontent = (TextView)i.findViewById(R.id.general_child_comment_content);











        }
    }


    //TODO: Do this if it is necessary else leave it for future implementation

    class ViewHolderVideo extends RecyclerView.ViewHolder{

        public ViewHolderVideo(View itemView) {
            super(itemView);
        }
    }







    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        type = viewType;

        if(type == TEXT){
            View view = inflater.inflate(R.layout.child_general_forum,parent,false);
            return new ViewHolderText(view);


        }else if (type == IMAGE){

            View view = inflater.inflate(R.layout.child_general_forum,parent,false);
            return new ViewHolerImage(view);


        }else {

            View view = inflater.inflate(R.layout.child_general_forum,parent,false);
            return new ViewHolderText(view);


        }



    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Post post = postSet.get(position);

        if(type == TEXT){

            bindText((GeneralForumRecycAdapter.ViewHolderText)holder, post);

        }else if (type == IMAGE){

            bindimage((GeneralForumRecycAdapter.ViewHolerImage)holder, post);




        }else{


            bindText((GeneralForumRecycAdapter.ViewHolderText)holder, post);


        }


    }

    @Override
    public int getItemCount() {
        return postSet.size();
    }


    @Override
    public int getItemViewType(int position) {

        Post post =postSet.get(position);

        if(post.getType().equalsIgnoreCase(context.getString(R.string.text))){
            return TEXT;
        }else if (post.getType().equalsIgnoreCase(context.getString(R.string.image))){
            return IMAGE;

        }else if (post.getType().equalsIgnoreCase(context.getString(R.string.video))){
            return VIDEO;

        }else{
            return TEXT;
        }

    }


    private void bindviews(ImageView childimage, TextView childname, TextView likecount, TextView commentcount, TextView commentname, TextView commentcontent, ImageView likeicon, ImageView commenticon, ImageView replyicon, Post post){


        try{

            Glide.with(context).load(Uri.parse(post.getUserphotourl())).asBitmap().into(childimage);

        }catch (Exception e){

            childimage.setImageResource(R.drawable.ic_user);

        }


        childname.setText(post.getUsername());
        likecount.setText(String.valueOf(post.getLikecount()));
        commentcount.setText(String.valueOf(post.getCommentcount()));
        commentname.setText(post.getCommentname() != null ? post.getCommentname() : " ");
        commentcontent.setText(post.getCommentcontent() != null ? post.getCommentcontent(): " ");


        childimage.setOnClickListener((vim) -> {

            // TODO: Implement the feature that allows user to view the profile of the person that posted this post

        });

        childname.setOnClickListener((vna) -> {

            // TODO: Implement the feature that allows user to view the profile of the person that posted this post


        });

        likeicon.setOnClickListener((vli) -> {

            // TODO: Increament LIke


        });

        commenticon.setOnClickListener((vco) -> {

            // TODO: Take the user to the Comment activity for the post


        });

        replyicon.setOnClickListener((vre) -> {

            // TODO: Enable the user to chat with the user that posted the post



        });


    }

    private void bindText(GeneralForumRecycAdapter.ViewHolderText holder, Post post){

        ImageView childimage = holder.childimage;
        TextView childname = holder.childname;
        TextView likecount = holder.likecount;
        TextView commentcount  = holder.commentcount;
        TextView commentname = holder.commentname;
        TextView commentcontent = holder.commentcontent;
        ImageView likeicon = holder.likeIcon;
        ImageView commenticon = holder.commenticon;
        ImageView replyicon = holder.replyicon;

        bindviews(childimage,childname,likecount,commentcount,commentname,commentcontent,likeicon,commenticon,replyicon,post);

        // display the Text post
        holder.textpost.setText(post.getTextmessage());

    }

    private void bindimage(GeneralForumRecycAdapter.ViewHolerImage holder, Post post){
        ImageView childimage = holder.childimage;
        TextView childname = holder.childname;
        TextView likecount = holder.likecount;
        TextView commentcount  = holder.commentcount;
        TextView commentname = holder.commentname;
        TextView commentcontent = holder.commentcontent;
        ImageView likeicon = holder.likeIcon;
        ImageView commenticon = holder.commenticon;
        ImageView replyicon = holder.replyicon;

        bindviews(childimage,childname,likecount,commentcount,commentname,commentcontent,likeicon,commenticon,replyicon,post);


        try{

            Glide.with(context).load(Uri.parse(post.getImageurl())).asBitmap().into(holder.imagepost);

        }catch (Exception e){

            holder.imagepost.setImageResource(R.mipmap.pipecollab);

        }


    }

    // TODO: IMplement this only when necessary
    private void bindvideo(){}
}
