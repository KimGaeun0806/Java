package application;



public class Review {
	
	private String date;
	private String review;
	
	public Review() {
		
	}
	
	public Review(String date, String review) {
		this.date = date;
		this.review = review;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getReview() {
		return review;
	}
	
	public void setReview(String review) {
		this.review = review;
	}
	
	
}
