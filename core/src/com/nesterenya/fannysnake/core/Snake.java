package com.nesterenya.fannysnake.core;

import com.nesterenya.fannysnake.SOUNDS;
import com.nesterenya.fannysnake.SoundsPlayer;

public class Snake{
	
	private Head head;
	private Tail tail;
	
	public Snake(Point initHeadPosition) {
		head = new Head(initHeadPosition, 0);
		tail = new Tail();
	}
	
	public Head getHead() {
		return head;
	}
	
	
	public Tail getTail() {
		return tail;
	}
	
	//TODO ���� �� ���� �����
	public void update(float delta) {
		if(voiceRecharge<=voiceRechargedWhen) {
			voiceRecharge+=delta;
		}
	}
	
	private float voiceRechargedWhen = 1;
	private float voiceRecharge = 1.1f;
	//TODO ����������� ������ � ������ �����
	public void reactionOnWall(SoundsPlayer soundsPlayer) {
		if(voiceRecharge>voiceRechargedWhen) {
			soundsPlayer.play(SOUNDS.BOOM);
			soundsPlayer.play(SOUNDS.OU);
			tail.reduction();
			voiceRecharge = 0;
		}
	}
}
