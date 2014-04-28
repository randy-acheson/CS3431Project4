package gui.main;

public class CD {
	final String genre;
	final String artist;
	final String album;
	
	public CD (String genre, String artist, String album) {
		this.genre = genre;
		this.artist = artist;
		this.album = album;
	}
	
	public String getGenre() {
		return genre;
	}

	public String getArtist() {
		return artist;
	}

	public String getAlbum() {
		return album;
	}

}
