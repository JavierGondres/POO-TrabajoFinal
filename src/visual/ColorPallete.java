package visual;

import java.awt.Color;
import java.awt.Font;

public abstract class ColorPallete {
	public static Color mainColor_Light = new Color(212, 240, 235,255);
	public static Color mainColor = new Color(102, 205, 170,255);
	public static Color mainColor_Dark = new Color(12, 115, 80,255);
	public static Color componentColor_Light = new Color(45, 173, 45);
	public static Color componentColor = new Color(0, 128, 0);
	public static Color componentColor_Dark = new Color(0, 69, 0);
	public static Color darkBluePanel = new Color(9, 63, 133);
	public static Color Panel = new Color(102, 141, 192);
	
	public static Font getTitleFont(int size) {
		return new Font("Segoe UI", Font.BOLD, size);
	}
	
	public static Font getBodyFont(int size, int fontType) {
		return new Font("Segoe Print", fontType, size);
	}
}