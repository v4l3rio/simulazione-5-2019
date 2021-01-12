package e1;

import java.util.ArrayList;
import java.util.List;

public class IteratorOfListsFactoryImpl implements IteratorOfListsFactory {

	@Override
	public <E> IteratorOfLists<E> iterative(E e) {
		return new IteratorOfLists<E>() {

			List<E> listToReturn = new ArrayList<>();

			int firstIteration = 0;

			@Override
			public List<E> nextList() {
				if (firstIteration == 0) {
					firstIteration++;
					return listToReturn;
				}
				listToReturn.add(e);
				return listToReturn;
			}

			@Override
			public boolean hasOtherLists() {
				return true;
			}

			@Override
			public void reset() {
				listToReturn.removeAll(listToReturn);
				firstIteration = 0;
			}
		};
	}

	@Override
	public <E> IteratorOfLists<E> iterativeOnList(List<E> list) {
		return new IteratorOfLists<E>() {

			List<E> listToReturn = new ArrayList<>();

			int firstIteration = 0;
			int i = 0;

			@Override
			public List<E> nextList() {
				if (firstIteration == 0) {
					firstIteration++;
					return listToReturn;
				}
				if(i>=list.size()) {
					i=0;
				}
				listToReturn.add(list.get(i));
				i++;
				return listToReturn;
			}

			@Override
			public boolean hasOtherLists() {
				return true;
			}

			@Override
			public void reset() {
				listToReturn.removeAll(listToReturn);
				firstIteration = 0;
				i=0;
			}
		};
	}

	@Override
	public <E> IteratorOfLists<E> iterativeWithPreamble(E e, List<E> preamble) {
		return new IteratorOfLists<E>() {

			List<E> listToReturn = new ArrayList<>();

			int firstIteration = 0;
			

			@Override
			public List<E> nextList() {
				if (firstIteration == 0) {
					firstIteration++;
					listToReturn.addAll(preamble);
					return listToReturn;
				}
				
				listToReturn.add(e);
				
				return listToReturn;
			}

			@Override
			public boolean hasOtherLists() {
				return true;
			}

			@Override
			public void reset() {
				listToReturn.removeAll(listToReturn);
				firstIteration = 0;

			}
		};
	}

}
