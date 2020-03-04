package com.pipe.www.pipe.model;

import java.util.ArrayList;

public class IsContain {


    public boolean isPostContain(ArrayList<Post> postset, Post post){

        boolean check = false;


        for (int i = 0; i< postset.size(); i++){


            if(postset.get(i).getPostid().equalsIgnoreCase(post.getPostid())){
                check = true;
                break;
            }

            if (i == postset.size()-1 && !postset.get(i).getPostid().equalsIgnoreCase(post.getPostid())){
                check = false;
            }



        }



        return check ;


    }





}
