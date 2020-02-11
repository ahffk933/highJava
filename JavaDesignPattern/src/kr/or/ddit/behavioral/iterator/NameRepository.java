package kr.or.ddit.behavioral.iterator;

public class NameRepository implements Container{

	public String names[] = {"강현철", "박진영", "정보람", "이승재", "연은주", "이병훈", "구한나", "김지선", "주향한"};
	
	
	
	@Override
	public Iterator getIterator() {

		return new NameIterator();
	}
	
	private class NameIterator implements Iterator{

		int index;
		
		@Override
		public boolean hasNext() {
			if(index < names.length) {
				return true;
			}
			
			return false;
		}

		@Override
		public Object next() {
			if(this.hasNext()) {
				return names[index++];
			}

			return null;
		}

	}
}
