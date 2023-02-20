package ist.challenge.andri.dto;

import java.util.Date;
import java.util.List;

public class SwapiResponse {
	public int count;
	public String next;
	public Object previous;
	public List<Result> results;
}

class Result{
	public String name;
	public String height;
	public String mass;
	public String hair_color;
	public String skin_color;
	public String eye_color;
	public String birth_year;
	public String gender;
	public String homeworld;
	public List<String> films;
	public List<String> species;
	public List<String> vehicles;
	public List<String> starships;
	public Date created;
	public Date edited;
	public String url;
}
