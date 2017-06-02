package q2p.zur2w;

public class TextProc {
	private static final String base = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя +-=_%@.,!?()[]:;\"\'";
	private static final short spacePos = 128;
	
	public static short[] convertString(String str) {
		short[] out = new short[str.length()];
		for(int i = 0; i < str.length(); i++) {
			for(short j = 0; j < base.length(); j++) {
				if(str.charAt(i) == base.charAt(j)) {
					out[i] = j;
					break;
				}
				out[i] = spacePos;
			}
		}
		return out;
	}
}
