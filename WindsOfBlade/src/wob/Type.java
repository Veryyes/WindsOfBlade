package wob;

public class Type {
	
	private static float[][] table = new float[][]
		{
			{0.75f,	1.5f,	1,		1,		1,		1,		1,		1,	0.5f,	1,		1,		1      },
			{1.5f,	0.75f,	1,		1,		1,		1,		1,		1,	0.5f,	1,		1,		1      },
			{1,		1,		0.75f,	1.5f,	1.5f,	1.2f,	1,		1,	1,		0.5f,	1,		0.5f   },
			{1,		1,		0.25f,	0.75f,	1,		1,		1.3f,	1,	1,		1.5f,	1,		1      },
			{1,		1,		0.5f,	1,		0.75f,	1.25f,	1.5f,	1,	1,		0.5f,	1,		1.5f   },
			{1,		1,		1.2f,	1,		0.75f,	0.75f,	0.75f,	1,	1,		1,		1,		1.5f   },
			{1,		1,		1.1f,	1,		0.5f,	1.25f,	0.75f,	1,	1,		1,		1,		1.5f   },
			{1,		1,		1,		1,		1,		1,		0.5f,	1,	1,		1,		1,		1      },
			{1.5f,	1.5f,	1,		1,		1,		1,		1,		1,	1.5f,	1,		1,		1      },
			{1,		1,		1.5f,	0.5f,	1.5f,	1,		1,		1,	1,		0.75f,	1,		0.5f   },
			{1,		1,		0.75f,	1,		1,		1,		0.5f,	1,	1,		1,		0.75f,	1      },
			{1,		1,		1.5f,	1,		0.5f,	0.5f,	0.5f,	1,	1,		1.5f,	1,		0.75f  }
		};
	public static final byte DARK = 0;
	public static final byte DIVINE = 1;
	public static final byte EARTH = 2;
	public static final byte ELECTRIC = 3;
	public static final byte FIRE = 4;
	public static final byte ICE = 5;
	public static final byte METAL = 6;
	public static final byte NORMAL = 7;
	public static final byte VOID = 8;
	public static final byte WATER = 9;
	public static final byte WIND = 10;
	public static final byte WOOD = 11;
	
	public static float effectiveness(int attackType, int defenceType){
		return table[attackType][defenceType];
	}
	public static String toString(int type){
		switch (type){
		case DARK:
			return "Dark";
		case DIVINE:
			return "Divine";
		case EARTH:
			return "Earth";
		case ELECTRIC:
			return "Electric";
		case FIRE:
			return "Fire";
		case ICE:
			return "Ice";
		case METAL:
			return "Metal";
		case NORMAL:
			return "Normal";
		case VOID:
			return "Void";
		case WATER:
			return "Water";
		case WIND:
			return "Wind";
		case WOOD:
			return "Wood";
		default:
			return null;
		}
	}
	public static byte parseType(String str){
		String type = str.toLowerCase();
		switch(type){
		case "dark":
			return DARK;
		case "divine":
			return DIVINE;
		case "earth":
			return EARTH;
		case "electric":
			return ELECTRIC;
		case "fire":
			return FIRE;
		case "ice":
			return ICE;
		case "metal":
			return METAL;
		case "normal":
			return NORMAL;
		case "void":
			return VOID;
		case "water":
			return WATER;
		case "wind":
			return WIND;
		case "wood":
			return WOOD;
		default:
			return -1;
		}
	}
}
