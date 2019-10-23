package com.moviebooking;

public enum CinemaType {
	Gold(1.3f),Platinum(1.0f);
	private float multiplier;
	public float getMultiplier() {
		return multiplier;
	}
	private CinemaType(float f){
		this.multiplier = f;
	}
		
}
