package mastercase;

public class AirUtil {

	public AirUtil() {
		// TODO Auto-generated constructor stub
	}

	public static String minutesToHHMM(int minutes) {
		int H = 0; 
		while(minutes >= 60) {
			H++;
			minutes -= 60;
		}
		
		return String.format("%02d:%02d", H, minutes);
	}
}
