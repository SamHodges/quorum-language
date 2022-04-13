package plugins.quorum.Libraries.Game;

import android.util.Log;
import android.view.MotionEvent;
import java.util.HashMap;

import android.view.ScaleGestureDetector;
import quorum.Libraries.Containers.List_;
import quorum.Libraries.Interface.Events.GestureEvent;
import quorum.Libraries.Interface.Events.GestureEvent_;
import quorum.Libraries.Interface.Events.TouchEvent;
import quorum.Libraries.Interface.Events.TouchEvent_;

/**
 *
 * @author alleew
 */
public class AndroidInput 
{
    public java.lang.Object me_ = null;

    public static final int GESTURE_SINGLE_TAP = 1;
    public static final int GESTURE_DOUBLE_TAP = 2;
    public static final int GESTURE_FLING = 3;
    public static final int GESTURE_LONG_PRESS = 4;
    public static final int GESTURE_SCROLL = 5;
    public static final int GESTURE_SCALE_BEGIN = 6;
    public static final int GESTURE_SCALE_CONTINUE = 7;
    public static final int GESTURE_SCALE_FINISH = 8;

    private List_ touchEvents;
    private List_ gestureEvents;
    private final HashMap<Integer, Coordinates> map = new HashMap<>();
    
    public void InitializePlugin(List_ list, List_ gestureList)
    {
        touchEvents = list;
        gestureEvents = gestureList;
    }
    
    public void AddEvent(TouchEvent_ event)
    {
        touchEvents.Add(event);
    }
    
    public void AddEvent(MotionEvent event)
    {
        TouchEvent_[] events = ConvertToQuorumEvents(event);
        for (TouchEvent_ e : events)
            AddEvent(e);
    }
    
    public TouchEvent_[] ConvertToQuorumEvents(MotionEvent e)
    {
            TouchEvent_[] eventArray;
            TouchEvent event;
            int id;
            int x;
            int y;
            Coordinates coordinates;
            switch(e.getActionMasked())
            {
                    case (MotionEvent.ACTION_DOWN):
                    case (MotionEvent.ACTION_POINTER_DOWN):
                        eventArray = new TouchEvent_[1];
                        event = new TouchEvent();
                        id = e.getPointerId(e.getActionIndex());
            
                        x = (int)e.getRawX();
                        y = GameStateManager.display.GetHeight() - (int)e.getRawY();

                        event.x = x;
                        event.y = y;
                        event.fingerID = id;
                        event.eventType = event.BEGAN;
                        map.put(id, new Coordinates(x, y));
                        event.movementX = 0;
                        event.movementY = 0;
                        eventArray[0] = event;
                        break;
                    case (MotionEvent.ACTION_MOVE):
                    eventArray = new TouchEvent_[e.getPointerCount()];

                    for (int i = 0; i < e.getPointerCount(); i++)
                    {
                        event = new TouchEvent();

                        id = e.getPointerId(i);

                        x = (int)e.getRawX();
                        y = GameStateManager.display.GetHeight() - (int)e.getRawY();

                        event.x = x;
                        event.y = y;
                        event.fingerID = id;
                        event.eventType = event.MOVED;
                        coordinates = map.get(id);
                        event.movementX = event.x - coordinates.x;
                        event.movementY = event.y - coordinates.y;
                        coordinates.x = x;
                        coordinates.y = y;
                        eventArray[i] = event;
                    }
                    break;
                    case (MotionEvent.ACTION_UP):
                    case (MotionEvent.ACTION_POINTER_UP):
                        eventArray = new TouchEvent_[1];
                        event = new TouchEvent();
                        id = e.getPointerId(e.getActionIndex());
            
                        x = (int)e.getRawX();
                        y = GameStateManager.display.GetHeight() - (int)e.getRawY();
                        event.eventType = event.ENDED;
                        coordinates = map.get(id);
                        event.movementX = x - coordinates.x;
                        event.movementY = y - coordinates.y;
                        event.x = x;
                        event.y = y;
                        map.remove(id);
                        eventArray[0] = event;
                        break;
                    case (MotionEvent.ACTION_CANCEL):
                        eventArray = new TouchEvent_[e.getPointerCount()];

                    for (int i = 0; i < e.getPointerCount(); i++)
                    {
                        event = new TouchEvent();

                        id = e.getPointerId(i);

                        x = (int)e.getRawX();
                        y = GameStateManager.display.GetHeight() - (int)e.getRawY();

                        event.x = x;
                        event.y = y;
                        event.fingerID = id;
                        event.eventType = event.CANCELLED;
                        coordinates = map.get(id);
                        event.movementX = x - coordinates.x;
                        event.movementY = y - coordinates.y;
                        map.remove(id);
                        eventArray[i] = event;
                    }
                        break;
                    case (MotionEvent.ACTION_OUTSIDE):
                    default:
                        eventArray = new TouchEvent_[1];
                        event = new TouchEvent();
                        x = (int)e.getRawX();
                        y = GameStateManager.display.GetHeight() - (int)e.getRawY();
                        for(int i = 0; i < e.getPointerCount(); i++)
                        {
                        if (!map.containsKey(e.getPointerId(i)))
                            map.put(e.getPointerId(i), new Coordinates(x, y));
                        }
                        event.eventType = -1;
                        eventArray[0] = event;
            }
        
        return eventArray;
    }
    
    public List_ GetEvents()
    {
        return touchEvents;
    }

    public void AddGestureEvent(GestureEvent_ event)
    {
        gestureEvents.Add(event);
    }

    public void AddSingleTapEvent(MotionEvent event)
    {
        GestureEvent quorumEvent = new GestureEvent();
        quorumEvent.Set_Libraries_Interface_Events_GestureEvent__eventType_(quorumEvent.SINGLE_TAP);

        // TO-DO: Set relevant properties here...

        AddGestureEvent(quorumEvent);
    }

    public void AddDoubleTapEvent(MotionEvent event)
    {
        GestureEvent quorumEvent = new GestureEvent();
        quorumEvent.Set_Libraries_Interface_Events_GestureEvent__eventType_(quorumEvent.DOUBLE_TAP);

        // TO-DO: Set relevant properties here...

        AddGestureEvent(quorumEvent);
    }

    public void AddFlingEvent(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY)
    {
        GestureEvent quorumEvent = new GestureEvent();
        quorumEvent.Set_Libraries_Interface_Events_GestureEvent__eventType_(quorumEvent.SWIPE);

        // TO-DO: Set relevant properties here...

        AddGestureEvent(quorumEvent);
    }

    public void AddLongPressEvent(MotionEvent event)
    {
        GestureEvent quorumEvent = new GestureEvent();
        quorumEvent.Set_Libraries_Interface_Events_GestureEvent__eventType_(quorumEvent.LONG_PRESS);

        // TO-DO: Set relevant properties here...

        AddGestureEvent(quorumEvent);
    }

    public void AddScrollEvent(MotionEvent event1, MotionEvent event2, float distanceX, float distanceY)
    {
        GestureEvent quorumEvent = new GestureEvent();
        quorumEvent.Set_Libraries_Interface_Events_GestureEvent__eventType_(quorumEvent.PAN);

        // TO-DO: Set relevant properties here...

        AddGestureEvent(quorumEvent);
    }

    public void AddScaleBeginEvent(ScaleGestureDetector detector)
    {
        GestureEvent quorumEvent = new GestureEvent();
        quorumEvent.Set_Libraries_Interface_Events_GestureEvent__eventType_(quorumEvent.PINCH);
        quorumEvent.Set_Libraries_Interface_Events_GestureEvent__timingCode_(quorumEvent.BEGIN);

        // TO-DO: Set relevant properties here...

        AddGestureEvent(quorumEvent);
    }

    public void AddScaleContinueEvent(ScaleGestureDetector detector)
    {
        GestureEvent quorumEvent = new GestureEvent();
        quorumEvent.Set_Libraries_Interface_Events_GestureEvent__eventType_(quorumEvent.PINCH);
        quorumEvent.Set_Libraries_Interface_Events_GestureEvent__timingCode_(quorumEvent.CONTINUE);

        // TO-DO: Set relevant properties here...

        AddGestureEvent(quorumEvent);
    }

    public void AddScaleEndEvent(ScaleGestureDetector detector)
    {
        GestureEvent quorumEvent = new GestureEvent();
        quorumEvent.Set_Libraries_Interface_Events_GestureEvent__eventType_(quorumEvent.PINCH);
        quorumEvent.Set_Libraries_Interface_Events_GestureEvent__timingCode_(quorumEvent.FINISH);

        // TO-DO: Set relevant properties here...

        AddGestureEvent(quorumEvent);
    }

    public List_ GetGestureEvents()
    {
        return gestureEvents;
    }
    
    private class Coordinates
    {
        public int x;
        public int y;
        
        public Coordinates(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }
}
