package visual.utils;

import java.awt.Color;

public abstract class ColorPallete {
	public static Color mainColor_Light = Color.decode("#668dc0").brighter().brighter();
	public static Color mainColor = Color.decode("#668dc0");
	public static Color mainColor_Dark = Color.decode("#668dc0").darker();
	public static Color componentColor_Light = Color.WHITE; //PlaceHolder
	public static Color componentColor = Color.WHITE; //PlaceHolder;
	public static Color componentColor_Dark = Color.WHITE; //PlaceHolder;
	public static Color transparent = new Color(255,255,255,0);
}
