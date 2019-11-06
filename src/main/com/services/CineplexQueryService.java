package main.com.services;

public class CineplexQueryService{
    private CineplexRepository _repo = CineplexRepository.getInstance();

    public List<Cineplex> GetCineplex(String name){
        return _repo.???; //repo method not done
    }
    public List<Cineplex> GetCineplexes(){
        return _repo.??? //repo not done
    }
    public List<Cinema> GetCinema(Cineplex Cineplex, String CinemaID){
        return null; //repo not done
    }
    public List<Cinema> GetCinema(Cineplex Cineplex){
        return null;  //repo not done
    }

    public List<Showtime> GetShows(Cinema cinema){
        return null;  //from which repo
    }
}