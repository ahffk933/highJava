package zipVO;

public class zipVO {
	private String zipcode;
	private String city;
	private String gugun;
	private String dong;
	private String staddr;
	
	
	public zipVO() {
		super();
		this.zipcode = zipcode;
		this.city = city;
		this.gugun = gugun;
		this.dong = dong;
		this.staddr = staddr;
	}


	public String getZip() {
		return zipcode;
	}


	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getGugun() {
		return gugun;
	}


	public void setGugun(String gugun) {
		this.gugun = gugun;
	}


	public String getDong() {
		return dong;
	}


	public void setDong(String dong) {
		this.dong = dong;
	}


	public String getStaddr() {
		return staddr;
	}


	public void setStaddr(String staddr) {
		this.staddr = staddr;
	}
	
	

}
