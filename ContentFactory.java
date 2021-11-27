import java.io.File;
import java.util.HashMap;

public class ContentFactory {
    /*
	For any given title, there can only ever be one Movie object with that title in your application.
	Implement this constraint for TV Shows as well.
	Hint: you should not throw exceptions when the title already exists in the library

	Note: I chose to let TV Shows and Movies have the same name
	 */

    private static final HashMap movieCache = new HashMap();
    private static final HashMap showCache = new HashMap();

    /**
     * method to create or pull from the cache, a movie with the given title and return to client
     *
     * @param pPath location of the movie on the file system.
     * @param pTitle official title of the movie in its original language
     * @param pLanguage language of the movie
     * @param pStudio studio which originally published the movie
     * @return created or pulled movie
     */
    public static Watchable getMovie(File pPath, String pTitle, Language pLanguage, String pStudio) {
        Movie mov = (Movie) movieCache.get(pTitle);

        if(mov==null) {
            mov = new Movie(pPath, pTitle, pLanguage, pStudio);
            movieCache.put(pTitle, mov);
        }
        return mov;
    }

    /**
     * method to create or pull from the cache, a movie with the given title and return to client
     *
     * @param pTitle official title of the TVShow
	 * @param pLanguage language of the movie, in full text (e.g., "English")
	 * @param pStudio studio which originally published the movie
     */
    public static Watchable getTVShow(String pTitle, Language pLanguage, String pStudio) {
        TVShow show = (TVShow) showCache.get(pTitle);

        if(show==null) {
            show = new TVShow(pTitle, pLanguage, pStudio);
            showCache.put(pTitle, show);
        }
        return show;
    }
}