package com.gservfocus.qwc.map;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gservfocus.qwc.R;

public class TextPopup
    extends MapPopupBase
{
	public final static int ZERO = 0;
	public final static int PADDING_BOTTOM = 11;
	public final static int PADDING_TOP = 5;
	public final static int PADDING_LEFT = 15;
	public final static int PADDING_RIGHT = 10;
	public final static float DEF_TEXT_SIZE = 16;
	public final static int IMAGE_SIZE = 30;
	public final static int MAX_EMS = 14;	
	
    private TextView text;

    public TextPopup(Context context, ViewGroup parentView) 
    {
        super(context, parentView);
        
        text = new TextView(context);
        
        text.setPadding((int)(PADDING_LEFT * dipScaleFactor),
                        (int)(PADDING_TOP * dipScaleFactor),
                        (int)(PADDING_RIGHT  * dipScaleFactor), 
                        (int)(PADDING_BOTTOM * dipScaleFactor));
        
        text.setBackgroundResource(R.drawable.map_description_background);
        text.setTextSize(DEF_TEXT_SIZE);
        text.setGravity(Gravity.CENTER);
        text.setMaxEms(MAX_EMS);
        text.setTextColor(Color.WHITE);
        
        container.addView(text);
    
        text.setFocusable(true);
        text.setClickable(true);
    }       

 
    public void moveBy(int dx, int dy)
    {
        if (lastX != -1 && lastY != -1){
            int paddingBottom = 0;
            int paddingRight = 0;
            if(container.getPaddingTop() > (screenHeight - (text.getHeight() + 3))){
                paddingBottom = (container.getPaddingBottom() - dy);
            }
            
            if(container.getPaddingLeft() > (screenWidth - (text.getWidth() + 3))){
                paddingRight = container.getPaddingRight() - dx;
            }
            
            container.setPadding(container.getPaddingLeft() + dx,
                                 container.getPaddingTop() + dy, 
                                 paddingRight, paddingBottom);
        }
    }
 
    
    public void setText(String theText)
    {
        text.setPadding((int)(PADDING_LEFT * dipScaleFactor),
                (int)(PADDING_TOP * dipScaleFactor),
                (int)(PADDING_RIGHT  * dipScaleFactor), 
                (int)(PADDING_BOTTOM * dipScaleFactor));
        
        text.setText(theText + "   ");
    }
    
    
    public void setIcon(BitmapDrawable theDrawable)
    {
        if (theDrawable != null) {
            theDrawable.setBounds(0,0, (int) (theDrawable.getBitmap().getWidth()),
                                        (int)(theDrawable.getBitmap().getHeight()));
        }
        
        text.setCompoundDrawables(null, null, theDrawable, null);
    }
    
    
    public void removeIcon()
    {
        text.setCompoundDrawables(null, null, null, null);
    }
    
    
    public void setOnClickListener(View.OnTouchListener listener)
    {
        if(text != null){
            text.setOnTouchListener(listener);
        }
    }
}
