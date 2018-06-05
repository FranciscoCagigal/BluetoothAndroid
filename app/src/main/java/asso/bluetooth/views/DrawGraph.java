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
import android.util.DisplayMetrics;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import asso.bluetooth.R;
import asso.bluetooth.logic.DevicePosition;
import asso.bluetooth.logic.MyBluetoothDevice;

public class DrawGraph extends View {
    Paint paint = new Paint();
    private int device_dimension,device_width,device_height;
    private int image_dimension = 38;
    private ArrayList<DevicePosition> images;

    private void init() {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(6);
        paint.setTextSize(20);
    }

    public DrawGraph(Context context) {
        super(context);
        this.setBackgroundColor(Color.WHITE);
        this.setClickable(true);
        getDeviceMeasures(context);
        init();
    }

    public void setDevices(List<MyBluetoothDevice> devices){
        images = new ArrayList<>();
        for(int i=0;i<devices.size();i++){
            //System.out.println()
            images.add(new DevicePosition(devices.get(i),(int) (Math.cos(i*360/devices.size()*Math.PI/180)*(device_dimension/2-image_dimension)*devices.get(i).getRssi()/100+device_width/2-image_dimension),(int)(Math.sin(i*360/devices.size()*Math.PI/180)*(device_dimension/2-image_dimension)*devices.get(i).getRssi()/100+device_height/2-image_dimension)));
        }
    }

    private void getDeviceMeasures(Context context){
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        device_width = displayMetrics.widthPixels;
        device_height = displayMetrics.heightPixels;
        device_dimension = Math.min(device_width,device_height);
    }

    @Override
    public void onDraw(Canvas canvas) {

        Bitmap image2 = BitmapFactory.decodeResource(getResources(), R.mipmap.laptop);
        Bitmap image_default = BitmapFactory.decodeResource(getResources(), R.mipmap.question);


        for(int i=0;i<images.size();i++){
            paint.setColor(Color.LTGRAY);
            canvas.drawLine(device_width/2,device_height/2,images.get(i).getPositionX()+image_dimension,images.get(i).getPositionY()+image_dimension,paint);
            Bitmap image = BitmapFactory.decodeResource(getResources(), images.get(i).getDevice().getImage());
            canvas.drawBitmap(image,images.get(i).getPositionX(),images.get(i).getPositionY(),paint);
            paint.setColor(Color.RED);
            canvas.drawText(images.get(i).getDevice().getName(),images.get(i).getPositionX(),images.get(i).getPositionY()+image_dimension*2+20,paint);
        }
        paint.setColor(Color.GREEN);
        canvas.drawCircle(device_width/2,device_height/2,20,paint);
        paint.setColor(Color.BLACK);
        canvas.drawText("Me",device_width/2-12,device_height/2+8, paint);
    }

    public String getDevice(float x, float y){
        Bitmap image = BitmapFactory.decodeResource(getResources(),R.mipmap.smartphone);
        for(int i=0;i<images.size();i++){
            if(images.get(i).getPositionX()<=x && images.get(i).getPositionX() + image.getWidth()>=x
                    && images.get(i).getPositionY()<=y && images.get(i).getPositionY() + image.getHeight()>=y){
                return images.get(i).getDevice().getName();
            }
        }
        return null;
    }

    public MyBluetoothDevice getAllDevice(float x, float y){
        Bitmap image = BitmapFactory.decodeResource(getResources(),R.mipmap.smartphone);
        for(int i=0;i<images.size();i++){
            if(images.get(i).getPositionX()<=x && images.get(i).getPositionX() + image.getWidth()>=x
                    && images.get(i).getPositionY()<=y && images.get(i).getPositionY() + image.getHeight()>=y){
                return images.get(i).getDevice();
            }
        }
        return null;
    }

}