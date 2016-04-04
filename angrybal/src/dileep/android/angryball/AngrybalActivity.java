package dileep.android.angryball;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class AngrybalActivity extends Activity implements View.OnClickListener  {
    /** Called when the activity is first created. */
	Button btn1;
	    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirstPage();
        
    }
    
    public void FirstPage(){
    	setContentView(R.layout.main);
    	btn1=(Button)findViewById(R.id.btn1);
    	btn1.setOnClickListener(this);
    	    }

	public void onClick(View v) {
		if(v==btn1)
			setContentView(new myView(this));
		
	}
	public class myView extends View
	{
		float x=240,y=600,px=0;
		int start=0,hit=0,col=0;
		Paint mypaint = new Paint();
		public myView(Context con){
			super(con);
		}
		public void onDraw(Canvas canvas){
				Arena(canvas);
				shoot();
				collision(canvas);
		
				//invalidate();
				
		}
		
	public void Arena(Canvas canvas){
		mypaint.setColor(Color.WHITE);
		canvas.drawLine(0,600,480, 600, mypaint);
		mypaint.setColor(Color.CYAN);
		canvas.drawCircle(x, y, 15, mypaint);
		mypaint.setColor(Color.RED);
		canvas.drawRect(30, 50, 450 , 120, mypaint);
	    mypaint.setColor(Color.CYAN);
		canvas.drawLine(240,600,x, y, mypaint);
		}
	@Override
	public boolean onTouchEvent (MotionEvent me)
	{
		
		int act= me.getAction();
		
		if(act==MotionEvent.ACTION_UP)
		{
			if(me.getY()>600){
			start=1;
			hit=0;
		
			px=me.getX();
			x=240;
			y=600;}
		}
		if(act== MotionEvent.ACTION_DOWN){
			
			if(col==1)
			{
				if(x<190 && x>290 && y<420 && y>380)
					invalidate();
			}
			}
		if(act == MotionEvent.ACTION_MOVE){
			if(me.getX()>600){
			x=me.getX();
			y=me.getY();}
			}
		invalidate();
		return true;					
	}
	
	public void shoot(){
    	if(start==1){
    		if(px<240&&hit==0)
    		{
    			y=y-5;
    			x=x+5;
    			if(x==480)
    				hit=1;
    				}
    		
    		if(px>240&&hit==0)
    		{
    			y=y-5;
    			x=x-5;
    			if(x==0)
    				hit=2;
    		}
    		if(hit==1){

    			y=y-5;
    			x=x-5;
    		}
    		if(hit==2){

    			y=y-5;
    			x=x+5;
    		}

    		
    	}
    	 }
	public void collision(Canvas canvas){
		if(start==1){
		if(x<450 && x>0 && y<120 && y>0 ){
			start=0;
			col=1;
		Toast.makeText(getBaseContext(), "You hit the goal :-)",Toast.LENGTH_LONG).show();
		x=240;
		y=600;
		restart(canvas);
			/*mypaint.setColor(Color.BLACK);
			canvas.drawColor(Color.BLACK);
			canvas.drawRect(30, 50, 450 , 120, mypaint);
			delay(1000);
			canvas.drawRect(30, 50, 450 , 120, mypaint);
			canvas.drawColor(Color.BLACK);
				
			
			*/
			}
		}
		}
	public void restart(Canvas canvas){
		canvas.drawColor(Color.BLUE);
		mypaint.setColor(Color.MAGENTA);
		canvas.drawRect(190,380, 290 , 420, mypaint);
		mypaint.setColor(Color.RED);
		canvas.drawText("Restart", 215, 390, mypaint);}
	}
	}
	
	

   
