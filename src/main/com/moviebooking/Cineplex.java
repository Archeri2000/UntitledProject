package main.com.moviebooking;


public class Cineplex {
	
	private Cinema[] cinemas;
    private String cineplexName;
     
    
	public Cineplex(String cineplexName, Cinema[] cinemas) {
		cinemas = new Cinema[3];
		this.cineplexName = cineplexName;
		for (int i=0;i<3;i++){
			this.cinemas[i]=cinemas[i];
		}
	}
	
	public Cinema[] getCinemas() {
		return cinemas;
	}

    public String getCineplexName() {
		return cineplexName;
	}
  
}
