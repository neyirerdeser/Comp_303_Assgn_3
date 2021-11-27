
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a movie library, with individual movie titles and watch lists.
 */
public class Library {



	/*
	Allow only one Library object to ever be created in your application.
	Add two fields -- a Name and an EmailID -- to the library,
	both of which can be changed by the client.
	The Library must always have a Name, but the EmailID may be missing.
	Ensure you implement appropriate getters and setters
	 */

	/**
	 * Default constructor just as before
	 * private so the client cannot access it and create a new library
	 */
	private Library() {}
	
	private Set<Movie> aMovies = new HashSet<>();
	private Set<WatchList> aWatchLists = new HashSet<>();
	private Set<Episode> aEpisodes = new HashSet<>();
	private Set<TVShow> aTVShows = new HashSet<>();
	private String aName;
	private String aEmailID;
	// a new library is created when the Class Library is accessed only.
	private static final Library INSTANCE = new Library();

	/**
	 * it lets the client name the library INSTANCE and returns it so that they can use it
	 * they have to use this method and name the library in order to access INSTANCE since it is otherwise private
	 *
	 * @param pName the new name for the library instance
	 * @pre pName!=null
	 */
	public static void renameLibrary(String pName) {
		assert pName != null;
		INSTANCE.aName = pName;
	}

	/**
	 * returns the library instance for client to use
	 * can only be used after naming the library
	 *
	 * @pre INSTANCE.aName!=null
	 * @return
	 */
	public static Library getLibrary() {
		assert INSTANCE.aName != null;
		return INSTANCE;
	}

	// all rest of the methods will have a 'this' that has a name
	// since the only way to access to an instance (the INSTANCE) is to rename it first

	/**
	 * sets the aEmailID to a new value
	 *
	 * @pre pEmailID!=null
	 * @param pEmailID new email to be set to the attribute aEmailID
	 * @throws IllegalArgumentException if the email entered is not in the format ____@___.__
	 */
	public void setEmailID(String pEmailID) {
		assert pEmailID != null;

		String error = "the entered string is not a valid email address";
		if(!pEmailID.contains("@"))
			throw new IllegalArgumentException(error);
		int indexAT = pEmailID.indexOf('@');
		String domain = pEmailID.substring(indexAT);
		if(indexAT == 0 || !domain.contains("."))
			throw new IllegalArgumentException(error);
		int indexDOT = domain.indexOf(".");
		if(indexDOT == 0 || indexDOT == domain.length()-1)
			throw new IllegalArgumentException(error);
		// obviously this is not amazingly checking for all invalidating factors
		// but I think it's a good midway between checking for absolutely everything and not checking at all.

		aEmailID = pEmailID;
	}

	/**
	 * @pre aEmailID!=null
	 * @return aEmailID
	 */
	public String getEmailID() {
		assert aEmailID != null;
		return aEmailID;
	}

	/**
	 * @return aName
	 */
	public String getName() {
		return aName;
	}
	
	/**
	 * Adds a movie to the library. Duplicate movies aren't added twice.
	 *
	 * @param pMovie the movie to add
	 * @pre pMovie!=null
	 */
	public void addMovie(Movie pMovie) {
		assert pMovie != null;
		aMovies.add(pMovie);
	}
	
	/**
	 * Adds a watchlist to the library. All movies from the list are also added as individual movies to the library.
	 *
	 * @param pList the watchlist to add
	 * @pre pList!=null;
	 */
	public void addWatchList(WatchList pList) {
		assert pList != null;
		aWatchLists.add(pList);
		for (Watchable watchable : pList) {
			if(watchable instanceof Movie) addMovie((Movie) watchable);
			if(watchable instanceof TVShow) addTVShow((TVShow) watchable);
			if(watchable instanceof Episode) aEpisodes.add((Episode) watchable);
		}
	}
	
	/**
	 * Adds a TVShow to the library. All Episodes from the list are also added as individual episodes to the library.
	 *
	 * @param pTVShow the TVShow to add
	 * @pre pTVShow!=null;
	 */
	public void addTVShow(TVShow pTVShow) {
		assert pTVShow != null;
		aTVShows.add(pTVShow);
		for (Episode episode : pTVShow) {
			aEpisodes.add(episode);
		}
	}
	
	/**
	 * Method to generate a new watchlist based on some filtering mechanism
	 *
	 * @param pName the name of the watchlist to create
	 * @param pGenerationParameters the generation parameters
	 * @pre pName!=null && pFilter!=null;
	 */
	public WatchList generateWatchList(String pName, WatchListGenerationInfo pGenerationParameters) {
		assert (pName != null) && (pGenerationParameters != null);
		List<Watchable> items = new ArrayList<>();
		for (TVShow show : aTVShows) {
			if (pGenerationParameters.filter(show)) {
				for (Episode episode : show) {
					if (pGenerationParameters.filter(episode)) {
						items.add(episode);
					}
				}
			}
		}
		for (Movie movie : aMovies) {
			if (pGenerationParameters.filter(movie)) {
				items.add(movie);
			}
		}
		Collections.sort(items, pGenerationParameters);
		WatchList watchlist = new WatchList(pName);
		for (Watchable item : items) {
			watchlist.addWatchable(item);
		}
		return watchlist;
	}
}
