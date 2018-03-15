package asso.bluetooth.views;

/**
 * Created by franc on 15/03/2018.
 */


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;

import java.util.ArrayList;

import asso.bluetooth.R;
import asso.bluetooth.logic.DevicePosition;
import asso.bluetooth.logic.MyBluetoothDevice;

public class DrawGraph extends View {
    Paint paint = new Paint();
    private static final int SQUARE_SIZE =100;
    private ArrayList<DevicePosition> images;

    private void init() {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(6);
    }

    public DrawGraph(Context context) {
        super(context);
        init();
        this.setBackgroundColor(Color.WHITE);
        this.setClickable(true);
    }

    public void setDevices(ArrayList<MyBluetoothDevice> devices){
        images = new ArrayList<>();
        for(int i=0;i<devices.size();i++){
            images.add(new DevicePosition(devices.get(i),100*i,100*i));
        }
    }

    public DrawGraph(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawGraph(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    public void onDraw(Canvas canvas) {
        //canvas.drawLine(0, 0, 20, 20, paint);
        //canvas.drawLine(20, 0, 0, 20, paint);
        System.out.println("vou desenhar");
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        for(int i=0;i<images.size();i++){
            canvas.drawBitmap(image,images.get(i).getPositionX(),images.get(i).getPositionY(),paint);
        }
        //canvas.drawBitmap(image,0,0,paint);
        //canvas.drawBitmap(image,100,300,paint);
    }

    public String getDevice(float x, float y){
        Bitmap image = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        for(int i=0;i<images.size();i++){
            if(images.get(i).getPositionX()<=x && images.get(i).getPositionX() + image.getWidth()>=x
                    && images.get(i).getPositionY()<=y && images.get(i).getPositionY() + image.getHeight()>=y){
                return images.get(i).getDevice().getName();
            }
        }
        return null;
    }

}