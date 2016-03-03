package com.myflappybird.game.other;


import java.awt.Rectangle;



/**
 * Created by foolishzy on 2016/1/4.
 * <p/>
 * funcction:
 * <p/>
 * others:
 */
public class rectangleIsOverLad {
    private boolean isOverLad;
    private Rectangle r1;
    private Rectangle r2;
    public  rectangleIsOverLad(Rectangle r1,Rectangle r2) {
        //math.pow(a,b)a的b次方
        this.r1=r1;
        this.r2=r2;
    }
    public boolean isOverLad(){
        if(r1.x>=r2.x-r1.getWidth()&&r1.x<=r2.x+r2.getWidth()&&
                r1.y>=r2.y-r1.getHeight()&&r1.y<=r2.y+r2.getHeight())
            isOverLad=true;
        else isOverLad=false;
        return isOverLad;
    }
//            System.out.println((r2.x-r1.getWidth())+"  "+r1.x+"  " +(r2.x+r2.getWidth()));

//            System.out.println((r2.x-r1.getWidth())+"  "+r1.x+"  " +(r2.x+r2.getWidth()));
//            System.out.println("x colides  birdx: "+r1.getX()+"  birdx2  "+r1.getX()+r1.getWidth()+"tubex1  "+r2.getX()+"tubex2  "+r2.getX()+r2.getWidth());
}
