import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Braille {
	public static String[] TEXT = {
	        ".  . .    . .   .  . . .  .. .  .  . .   .  . .. .   . .   .  . .  .   .. .   .  . .  .. .   .  . ",
	        " ..  .  .     .  .  .    ..      .. .. .  .      .  .  . .. .. ..  .   .  .    .   . ..  . .. .   ",
	        ".        .  ..    .  .    .  ..   .  .     .. .      .    .  .  .             . .  .        .     ",
	};
	
	public static class Letter {
		private String[] dots;
		private String ascii;
		
		public Letter(String[] dots, String ascii) {
			this.dots = dots;
			this.ascii = ascii;
		}

		public boolean matches(String[] dots) {
			return Arrays.equals(this.dots, dots);
		}
		
		public String getAscii() {
			return ascii;
		}
	}

	private static List<Letter> ENCODE = new ArrayList<>();
	
	private static void registerLetter(String ascii, String... dots) {
		ENCODE.add(new Letter(dots, ascii));
	}
	
	static {
		registerLetter("A", ". ", "  ", "  ");
		registerLetter("B", ". ", ". ", "  ");
		registerLetter("C", "..", "  ", "  ");
		registerLetter("D", "..", " .", "  ");
		registerLetter("E", ". ", " .", "  ");
		registerLetter("F", "..", ". ", "  ");
		registerLetter("G", "..", "..", "  ");
		registerLetter("H", ". ", "..", "  ");
		registerLetter("I", " .", ". ", "  ");
		registerLetter("J", " .", "..", "  ");
		
		registerLetter("K", ". ", "  ", ". ");
		registerLetter("L", ". ", ". ", ". ");
		registerLetter("M", "..", "  ", ". ");
		registerLetter("N", "..", " .", ". ");
		registerLetter("O", ". ", " .", ". ");
		registerLetter("P", "..", ". ", ". ");
		registerLetter("Q", "..", "..", ". ");
		registerLetter("R", ". ", "..", ". ");
		registerLetter("S", " .", ". ", ". ");
		registerLetter("T", " .", "..", ". ");
		
		registerLetter("U", ". ", "  ", "..");
		registerLetter("V", ". ", ". ", "..");
		registerLetter("X", "..", "  ", "..");
		registerLetter("Y", "..", " .", "..");
		registerLetter("Z", ". ", " .", "..");
		registerLetter("ç", "..", ". ", "..");
		registerLetter("é", "..", "..", "..");
		registerLetter("à", ". ", "..", "..");
		registerLetter("è", " .", ". ", "..");
		registerLetter("ù", " .", "..", "..");
		
		registerLetter("â", ". ", "  ", " .");
		registerLetter("ê", ". ", ". ", " .");
		registerLetter("î", "..", "  ", " .");
		registerLetter("ô", "..", " .", " .");
		registerLetter("û", ". ", " .", " .");
		registerLetter("ë", "..", ". ", " .");
		registerLetter("ï", "..", "..", " .");
		registerLetter("ü", ". ", "..", " .");
		registerLetter("oe", " .", ". ", " .");
		registerLetter("W", " .", "..", " .");
		
		registerLetter(",", "  ", ". ", "  ");
		registerLetter("?", "  ", ". ", ". ");
		registerLetter(":", "  ", "..", "  ");
		registerLetter(".", "  ", "..", " .");
		registerLetter("?", "  ", ". ", " .");
		registerLetter("!", "  ", "..", ". ");
		registerLetter("(", "  ", "..", "..");
		registerLetter("«", "  ", ". ", "..");
		registerLetter("*", "  ", " .", ". ");
		registerLetter("»", "  ", " .", "..");
		
		registerLetter("/", " .", "  ", ". ");
	}
	
	public static void main(String[] args) {
		System.out.println(new Braille().decode(TEXT));
	}
	
	
	public String decodeChar(String[] dots) {
		return ENCODE.stream()
				.filter(l -> l.matches(dots))
				.findAny()
				.map(l -> l.getAscii())
				.orElse(" ");
	}
	
	public String decode(String[] chars) {
		StringBuilder sb = new StringBuilder();
		for (int i = chars[0].length() - 1; i-1 > 0; i-=2) {
			String[] dots = new String[3];
			for (int j = 0; j < 3; j++) {
				dots[j] = new String(new char[] { chars[j].charAt(i), chars[j].charAt(i-1) } );
			}
			
			sb.append(decodeChar(dots));
		}
		
		return sb.toString();
	}
}
