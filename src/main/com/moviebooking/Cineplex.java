package main.com.moviebooking;


public class Cineplex {
	
	private Cinema[] cinemas;
    private String cineplexName;
     
    
	public Cineplex(String cineplexName, Cinema[] cinemas) {
		cinemas = new Cinema[3];
		this.cineplexName = cineplexName;
        System.arraycopy(cinemas, 0, this.cinemas, 0, 3);
	}
	
	public Cinema[] getCinemas() {
		return cinemas;
	}

    public String getCineplexName() {
		return cineplexName;
	}
  
}
