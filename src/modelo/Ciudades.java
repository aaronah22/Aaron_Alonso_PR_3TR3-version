package modelo;

public class Ciudades {
	private String city;
	private String country;
	private String district;
	private int population;
	private String continente;
	private String idioma;

	public Ciudades(String city, String country, String district, int population, String continente, String idioma) {
		this.city = city;
		this.country= country;
		this.district = district;
		this.population = population;
		this.continente = continente;
		this.idioma = idioma;
	}
	
	public Ciudades() {
		city = null;
		country= null;
		district = null;
		population = 0;
		continente = null;
		idioma = null;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public String getContinente() {
		return continente;
	}

	public void setContinente(String continente) {
		this.continente = continente;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	



	

	

	
	
	
}
