/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Sound.IOS;

import org.robovm.apple.foundation.NSObject;
import plugins.quorum.Libraries.Sound.Data;

/**
 *
 * @author alleew
 */
class IOSStreamData extends Data 
{
    private final OALAudioTrack track;
    //OnCompletionListener onCompletionListener;
    
    public IOSStreamData(OALAudioTrack track) 
    {
        this.track = track;
        this.track.setDelegate(new AVAudioPlayerDelegateAdapter() 
        {
            @Override
            public void didFinishPlaying(NSObject player, boolean success)
                {
                    /*
                    final OnCompletionListener listener = onCompletionListener;
                    if (listener != null) 
                    {
                        Gdx.app.postRunnable(new Runnable() 
                        {
                            @Override
                            public void run () 
                            {
                                listener.onCompletion(IOSMusic.this);
                            }
                        });
                    }
                    */
                }
        });
    }
    
    @Override
    public void Play() 
    {
        if (track.isPaused()) 
        {
            track.setPaused(false);
        }
        else if (!track.isPlaying()) 
        {
            track.play();
        }
    }
    
    @Override
    public void Pause() 
    {
        if (track.isPlaying()) 
        {
            track.setPaused(true);
        }
    }
    
    @Override
    public void Stop() 
    {
        track.stop();
    }
    
    @Override
    public boolean IsPlaying()
    {
        return track.isPlaying() && !track.isPaused();
    }
    
    @Override
    public void SetLooping(boolean isLooping) 
    {
        track.setNumberOfLoops(isLooping ? -1 : 0);
    }
    
    @Override
    public boolean IsLooping() 
    {
        return track.getNumberOfLoops() == -1;
    }

    @Override
    public void SetVolume(float volume) 
    {
        track.setVolume(volume);
    }
    
    @Override
    public float GetVolume () 
    {
        return track.getVolume();
    }
    
    @Override
    public void Dispose() 
    {
        track.clear();
    }

    @Override
    public void SetHorizontalPosition(float pan) 
    {
        track.setPan(pan);
    }
    
    /*
	@Override
	public void setPosition (float position) {
		track.setCurrentTime(position);
	}

	@Override
	public float getPosition () {
		return (float)(track.getCurrentTime());
        }

	@Override
	public void setOnCompletionListener (OnCompletionListener listener) {
		this.onCompletionListener = listener;
	}
    */

    @Override
    public void Resume() {
        if (track.isPaused())
            Play();
    }

    @Override
    public void SetPitch(float pitch) 
    {
        // Need to determine proper way to set pitch for this type of audio.
    }

    @Override
    public void SetFade(float position) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void SetX(float newX) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void SetY(float newY) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void SetZ(float newZ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void SetPosition(float newX, float newY, float newZ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean IsStreaming() 
    {
        return true;
    }

    @Override
    public void Update() 
    {
        // Do nothing on iOS.
    }
    
}
