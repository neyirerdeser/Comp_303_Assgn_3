
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a sequence of watchables to watch in FIFO order.
 */
public class WatchList implements Bingeable<Watchable> {

	/*
	Implement an equality criteria for watchlists based on their content.
	Two watchlists are considered duplicates if they aggregate the same Watchable objects in the same order,
	regardless of the name of the watchlists
	 */
	
	private final List<Watchable> aList = new LinkedList<>();
	private String aName;
	private int aNext;
	
	/**
	 * Creates a new empty watchlist.
	 * 
	 * @param pName the name of the list
	 * @pre pName!=null;
	 */
	public WatchList(String pName) {
		assert pName != null;
		aName = pName;
		aNext = 0;
	}
	
	public String getName() {
		return aName;
	}
	
	/**
	 * Changes the name of this watchlist.
	 * 
	 * @param pName the new name
	 * @pre pName!=null;
	 */
	public void setName(String pName) {
		assert pName != null;
		aName = pName;
	}
	
	/**
	 * Adds a watchable at the end of this watchlist.
	 * 
	 * @param pWatchable the watchable to add
	 * @pre pWatchable!=null;
	 */
	public void addWatchable(Watchable pWatchable) {
		assert pWatchable != null;
		aList.add(pWatchable);
	}
	
	/**
	 * Retrieves and removes the next watchable to watch from this watchlist. Watchables are retrieved in FIFO order.
	 */
	public Watchable removeNext() {
		return aList.remove(0);
	}
	
	/**
	 * @return the total number of valid watchable elements
	 */
	public int getValidCount() {
		int count = 0;
		for (Watchable item : aList) {
			if (item.isValid()) {
				count++;
			}
		}
		return count;
	}
	
	@Override
	public int getTotalCount() {
		return aList.size();
	}
	
	@Override
	public int getRemainingCount() {
		return aList.size() - aNext;
	}
	
	@Override
	public Watchable next() {
		assert getRemainingCount() > 0;
		Watchable next = aList.get(aNext);
		aNext++;
		if (aNext >= aList.size()) {
			aNext = 0;
		}
		return next;
	}
	
	@Override
	public void reset() {
		aNext = 0;
	}
	
	@Override
	public Iterator<Watchable> iterator() {
		return Collections.unmodifiableList(aList).iterator();
	}

	/**
	 * takes a Watchlist (this) and an object
	 * compares the contents of both to determine whether they're equivalent
	 *
	 * @param obj variable to compare against 'this'
	 * @return true if they contain the same content in the same order, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof WatchList)) return false;
		WatchList comp = (WatchList) obj;

		Iterator<Watchable>originalIter = this.iterator();
		Iterator<Watchable>comparedIter = comp.iterator();

		while(originalIter.hasNext() && comparedIter.hasNext())
			if(!originalIter.next().equals(comparedIter.next())) return false;

		return !originalIter.hasNext() && !comparedIter.hasNext(); // checks if there are left over items in either watchlist
	}
}
