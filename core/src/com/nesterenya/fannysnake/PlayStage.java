package com.nesterenya.fannysnake;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PlayStage extends Stage {

    public PlayStage(Viewport viewport) {
        super(viewport);
    }

    // Прослушивает события нажатия клавиш пользователем
    @Override
    public boolean keyDown(int keyCode) {
        if (keyCode == Keys.BACK) {
        	if (getHardKeyListener() != null)
                getHardKeyListener().onHardKey(keyCode, 1);
        }
        return super.keyDown(keyCode);
    }

    @Override
    public boolean keyUp(int keyCode) {
        if (keyCode == Keys.BACK){
            if (getHardKeyListener() != null)
                getHardKeyListener().onHardKey(keyCode, 0);
        }
        return super.keyUp(keyCode);
    }   
    
    public interface OnHardKeyListener {
        // Теперь, при нажатии пользователем кнопки Назад, будет выполняться код из этого метода (который мы пишем в теле экрана)
        public abstract void onHardKey(int keyCode, int state);
    }
    private OnHardKeyListener _HardKeyListener = null;  
    public void setHardKeyListener(OnHardKeyListener HardKeyListener) {
        _HardKeyListener = HardKeyListener;
    }       
    public OnHardKeyListener getHardKeyListener() {
        return _HardKeyListener;
    }
}