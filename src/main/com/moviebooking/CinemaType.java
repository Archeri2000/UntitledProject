package main.com.moviebooking;

public enum CinemaType {
	Gold(1.3f),Platinum(1.0f);
	private final float multiplier;
	public float getMultiplier() {
		return multiplier;
	}
	CinemaType(float f){
		this.multiplier = f;
	}
		
}
