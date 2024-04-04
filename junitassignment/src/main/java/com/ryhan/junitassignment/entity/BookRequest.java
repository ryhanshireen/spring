package com.ryhan.junitassignment.entity;


public class BookRequest{
		 
	    private Long id;
	    private String name;
	    private String author;
	    private String description;
	    private Double rating;
	    
	    
	    public BookRequest(Long id, String name, String author, String description, Double rating) {
	        this.id = id;
	        this.name = name;
	        this.author = author;
	        this.description = description;
	        this.rating = rating;
	    }

	  public BookRequest() {
		  
	  }
	    
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAuthor() {
			return author;
		}
		public void setAuthor(String author) {
			this.author = author;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public Double getRating() {
			return rating;
		}
		public void setRating(Double rating) {
			this.rating = rating;
		}
	    
		
	    
}
