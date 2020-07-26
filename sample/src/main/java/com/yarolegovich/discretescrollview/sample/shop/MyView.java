package com.yarolegovich.discretescrollview.sample.shop;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MyView extends View {

    int height;
    int width;

    int arc1Padding = 50;
    int arc2Padding = 30;
    int topViewCenterGap = 80;
    int topViewLeftGap = 30;
    int arcGap = 50;
    int buttonGapFromCenter = 70;
    int increaseHeightWithCurve = 12;

    int leftArc1YStartView = 80;
    int leftArc2YStartView = 50;
    int letftArcXStartView = 30;
    int leftArc2StartView = 80;

    Context cc;
    public MyView(Context context) {
        super(context);
        cc = context;

        init((Activity)cc, null);
    }


    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        cc = context;

        init( (Activity)cc, attrs);
    }

    private void init(Activity cc, Object o) {

        DisplayMetrics displaymetrics = new DisplayMetrics();
        cc.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
         height = displaymetrics.heightPixels;
         width = displaymetrics.widthPixels;
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint drawPaint = new Paint();
        drawPaint.setColor(Color.YELLOW);
        drawPaint.setStyle(Paint.Style.FILL);
        canvas.drawPaint(drawPaint);


// cicrcle
        float cRadius = (float) (width/4);
        float cWidth = width/2;
        float cHeight = height/4;  // height top to the center point

        Paint circlePaint = new Paint();
        circlePaint.setColor(Color.BLACK);
        circlePaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(cWidth, cHeight , cRadius,  circlePaint);


        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8);

        Path path = new Path();

        //============== LEFT VIEW ===========================================//

        path.moveTo(( cWidth - cRadius ) -letftArcXStartView, ( cHeight - cRadius ) + leftArc1YStartView);
        path.quadTo((cWidth - cRadius ) -80, cHeight, (cWidth - cRadius ) -letftArcXStartView,  ( cHeight + cRadius ) - leftArc1YStartView);
        // path.close();

        path.moveTo(( cWidth - cRadius ) -leftArc2StartView, ( cHeight - cRadius ) + leftArc2YStartView);
        path.quadTo((cWidth - cRadius ) -130, cHeight, (cWidth - cRadius ) -80,  ( cHeight + cRadius ) - leftArc2YStartView);
        // path.close();
        path.moveTo(( cWidth - cRadius ) -letftArcXStartView, ( cHeight - cRadius ) + leftArc1YStartView); //starting of my ist view (starting)
        path.lineTo(( cWidth - cRadius ) -leftArc2StartView, ( cHeight - cRadius ) + leftArc2YStartView);  // ending at 2nd (starting)

        path.moveTo((cWidth - cRadius ) -letftArcXStartView,  ( cHeight + cRadius ) - leftArc1YStartView);
        path.lineTo((cWidth - cRadius ) -80,  ( cHeight + cRadius ) - leftArc2YStartView);

        canvas.drawPath(path, paint);

        //============== TOP VIEW ===========================================//

        path.moveTo((cWidth -cRadius) + arc1Padding, ( cHeight - cRadius ) -topViewLeftGap);
        path.quadTo(cWidth,  ( cHeight - cRadius ) - topViewCenterGap, (cWidth + cRadius) - arc1Padding,  ( cHeight - cRadius ) - topViewLeftGap);
        // path.close();

        path.moveTo((cWidth -cRadius) + arc2Padding, ( cHeight - cRadius ) - (topViewLeftGap + arcGap));
        path.quadTo(cWidth,  ( cHeight - cRadius ) - (topViewCenterGap + arcGap), (cWidth + cRadius) - arc2Padding,  ( cHeight - cRadius ) -  (topViewLeftGap + arcGap));


        // both side arc closer  (left)
        path.moveTo((cWidth -cRadius) + arc1Padding, ( cHeight - cRadius ) - topViewLeftGap);
        path.lineTo((cWidth -cRadius) + arc2Padding, ( cHeight - cRadius ) - (topViewLeftGap +arcGap));

        // (right)
        path.moveTo((cWidth + cRadius) - arc1Padding,  ( cHeight - cRadius ) - topViewLeftGap);
        path.lineTo((cWidth + cRadius) - arc2Padding,  ( cHeight - cRadius ) - (topViewLeftGap +arcGap));

        // left and right button
        //left box
        path.moveTo(((cWidth -cRadius) + arc1Padding) + buttonGapFromCenter, (( cHeight - cRadius ) - topViewLeftGap ) - increaseHeightWithCurve );
        path.lineTo(((cWidth -cRadius) + arc2Padding) + buttonGapFromCenter, ( cHeight - cRadius ) - (topViewLeftGap +arcGap) - increaseHeightWithCurve );
// left triangle button
        path.lineTo((cWidth -cRadius) + arc1Padding, ( cHeight - cRadius ) - topViewLeftGap - (arcGap/2));
        path.lineTo(((cWidth -cRadius) + arc1Padding) + buttonGapFromCenter, (( cHeight - cRadius ) - topViewLeftGap ) - increaseHeightWithCurve );


        // right
        path.moveTo((cWidth + cRadius) - arc1Padding - buttonGapFromCenter,  ( cHeight - cRadius ) - topViewLeftGap - increaseHeightWithCurve );
        path.lineTo((cWidth + cRadius) - arc2Padding - buttonGapFromCenter,  ( cHeight - cRadius ) - (topViewLeftGap +arcGap) - increaseHeightWithCurve);
        path.lineTo((cWidth + cRadius) - arc1Padding ,  ( cHeight - cRadius ) - topViewLeftGap - (arcGap/2) );
        path.lineTo((cWidth + cRadius) - arc1Padding - buttonGapFromCenter,  ( cHeight - cRadius ) - topViewLeftGap - increaseHeightWithCurve );

        canvas.drawPath(path, paint);


        //============== BOTTOM  VIEW ===========================================//

        // arcs
        path.moveTo((cWidth -cRadius) + arc1Padding, ( cHeight + cRadius ) + topViewLeftGap);
        path.quadTo(cWidth,  ( cHeight + cRadius ) + topViewCenterGap, (cWidth + cRadius) - arc1Padding,  ( cHeight + cRadius ) + topViewLeftGap);
        // path.close();

        path.moveTo((cWidth -cRadius) + arc2Padding, ( cHeight + cRadius ) + (topViewLeftGap +arcGap));
        path.quadTo(width * 0.49f,  ( cHeight + cRadius ) + (topViewCenterGap +arcGap ) , (cWidth + cRadius) - arc2Padding,  ( cHeight + cRadius ) + (topViewLeftGap +arcGap));

        // both side arc closer  (left)
        path.moveTo((cWidth -cRadius) + arc1Padding, ( cHeight + cRadius ) + topViewLeftGap);
        path.lineTo((cWidth -cRadius) + arc2Padding, ( cHeight + cRadius ) + (topViewLeftGap +arcGap));

        // (right)
        path.moveTo((cWidth + cRadius) - arc1Padding,  ( cHeight + cRadius ) + topViewLeftGap);
        path.lineTo((cWidth + cRadius) - arc2Padding,  ( cHeight + cRadius ) + (topViewLeftGap +arcGap));

        // left and right button
   //left box
        path.moveTo(((cWidth -cRadius) + arc1Padding) + buttonGapFromCenter, (( cHeight + cRadius ) + topViewLeftGap ) + increaseHeightWithCurve );
        path.lineTo(((cWidth -cRadius) + arc2Padding) + buttonGapFromCenter, ( cHeight + cRadius ) + (topViewLeftGap +arcGap) + increaseHeightWithCurve );
// left triangle button
        path.lineTo((cWidth -cRadius) + arc1Padding, ( cHeight + cRadius ) + topViewLeftGap + (arcGap/2)); // arc 1 startign point + increase height by half of arc b
        path.lineTo(((cWidth -cRadius) + arc1Padding) + buttonGapFromCenter, (( cHeight + cRadius ) + topViewLeftGap ) + increaseHeightWithCurve ); // back to arc1 + padding from starting point


        // right
        path.moveTo((cWidth + cRadius) - arc1Padding - buttonGapFromCenter,  ( cHeight + cRadius ) + topViewLeftGap +  + increaseHeightWithCurve );
        path.lineTo((cWidth + cRadius) - arc2Padding - buttonGapFromCenter,  ( cHeight + cRadius ) + (topViewLeftGap +arcGap) + + increaseHeightWithCurve);
        path.lineTo((cWidth + cRadius) - arc1Padding ,  ( cHeight + cRadius ) + topViewLeftGap + (arcGap/2) );
        path.lineTo((cWidth + cRadius) - arc1Padding - buttonGapFromCenter,  ( cHeight + cRadius ) + topViewLeftGap +  + increaseHeightWithCurve );





        canvas.drawPath(path, paint);

        //============== Right VIEW ===========================================//

        path.moveTo(( cWidth + cRadius ) + 30, ( cHeight - cRadius ) + 10);
        path.quadTo((cWidth + cRadius ) +80, cHeight, (cWidth + cRadius ) +30,  ( cHeight + cRadius ) - 10);
        // path.close();

        path.moveTo(( cWidth + cRadius ) +80, ( cHeight - cRadius ));
        path.quadTo((cWidth + cRadius ) +130, cHeight, (cWidth + cRadius ) +80,  ( cHeight + cRadius ));

        path.moveTo(( cWidth + cRadius ) + 30, ( cHeight - cRadius ) + 10);
        path.lineTo(( cWidth + cRadius ) +80, ( cHeight - cRadius ));

        path.moveTo((cWidth + cRadius ) +30,  ( cHeight + cRadius ) - 10);
        path.lineTo((cWidth + cRadius ) +80,  ( cHeight + cRadius ));

        canvas.drawPath(path, paint);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean value = super.onTouchEvent(event);

        int x = (int) event.getX();
        int y = (int) event.getY();

        int percentageX= (int)((x*100)/width);
        int percentageY= (int)((y*100)/height);


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
              //  Log.i("TAG", "touched down");
             //   Toast.makeText(cc,"touched down",Toast.LENGTH_LONG).show();

                if(percentageX > 30 && percentageX < 70 && percentageY > 15 & percentageY <21) {
                    Log.d("ajaymehta", "touched "+percentageX +" - "+percentageY);
                }


                break;
            case MotionEvent.ACTION_MOVE:
                Toast.makeText(cc,"moving: (" + percentageX + "," + percentageY +")",Toast.LENGTH_LONG).show();
                break;
            case MotionEvent.ACTION_UP:
               // Log.i("TAG", "touched up");
              //  Toast.makeText(cc,"touched up",Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }

}
