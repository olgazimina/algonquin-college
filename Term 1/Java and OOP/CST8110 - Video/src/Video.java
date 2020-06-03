
public class Video {
	private String name;
	private String uploadedBy;
	private int year;
	private int size;
	private int framesPerSecond;
	private int resolution;

	public Video() {

	}

	public Video(String name, String uploadedBy, int year, int size, int framesPerSecond, int resolution) {
		this.name = name;
		this.uploadedBy = uploadedBy;
		this.year = year;
		this.size = size;
		this.framesPerSecond = framesPerSecond;
		this.resolution = resolution;
	}

	public int getAge(int currentYear) {
		return currentYear - this.year;
	}

	public double getSize() {
		return this.size;
	}

	public boolean overOneGigaByte() {
		if (size >1048) { 
			return true;	
		} else {
			return false;
		}
	}
}
