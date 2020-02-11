package exam;

public class Assignment0109 {
	/*
	 * 문제) 태양계 행성을 나타내는 enum Planet을 이용하여 구하시오. (단, enum 객체 생성시 반지름을 이용하도록 정의하시오.)
	 * 
	 * 예) 행성의 반지름(KM): 수성(2439), 금성(6052), 지구(6371), 화성(3390), 목성(69911), 토성(58232),
	 * 천왕성(25362), 해왕성(24622)
	 */
	static final double PI = Math.PI;

	public enum planet {
		수성(2439), 금성(6052), 지구(6371), 화성(3390), 목성(69911), 토성(58232), 천왕성(25362), 해왕성(24622);
		private int uni;

		planet(int data) {
			uni = data;
		}

		public long getuni() {
			return (Math.round(4 * PI * (Math.pow(uni, 2))));
		}

	}

	public static void main(String[] args) {
		planet[] pn = planet.values();

		for (int i = 0; i < pn.length; i++) {
			System.out.println(pn[i].name() + "의 면적: " + pn[i].getuni() + "km²");
		}
	}
}
