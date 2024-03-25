package com.java8Features.streams;

public class Movie implements Comparable<Movie> {

	public String name;
	public double rating;
	public int year;
	
	

	public Movie(String name, double rating, int year) {
		super();
		this.name = name;
		this.rating = rating;
		this.year = year;
	}

	@Override
	public int compareTo(Movie o) {
		// TODO Auto-generated method stub
		return Integer.compare(this.year, o.year);
	}

	public String getName() {
		return name;
	}

	public double getRating() {
		return rating;
	}

	public int getYear() {
		return year;
	}

	@Override
	public String toString() {
		return "Movie [name=" + name + ", rating=" + rating + ", year=" + year + "]";
	}
	
	
	

}
