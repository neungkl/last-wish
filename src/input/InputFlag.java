package input;

public class InputFlag {

	private static final int MAX_INPUT = 256;

	private static boolean[] key = new boolean[MAX_INPUT];
	private static boolean[] keyTrigger = new boolean[MAX_INPUT];
	private static boolean mouseLeft, mouseLeftTrigger, mouseLeftClick;
	private static boolean mouseRight, mouseRightTrigger, mouseRightClick;

	private static int mouseX, mouseY;

	public final static int KEYBOARD = 0;
	public final static int MOUSE_LEFT = 1;
	public final static int MOUSE_RIGHT = 2;
	public final static int MOUSE_LEFT_CLICK = 3;
	public final static int MOUSE_RIGHT_CLICK = 4;

	public static void reset() {
		for (int i = 0; i < key.length; i++) {
			key[i] = keyTrigger[i] = false;
		}
		mouseLeft = mouseLeftTrigger = mouseLeftClick = false;
		mouseRight = mouseRightTrigger = mouseRightClick = false;
	}

	public static void set(int type, boolean value) {
		set(type, 0, value);
	}

	public static void set(int type, int index, boolean value) {
		if (type == KEYBOARD) {
			key[index] = keyTrigger[index] = value;
		} else if (type == MOUSE_LEFT) {
			mouseLeft = mouseLeftTrigger = value;
		} else if (type == MOUSE_RIGHT) {
			mouseRight = mouseRightTrigger = value;
		} else if (type == MOUSE_LEFT_CLICK) {
			mouseLeftClick = value;
		} else if (type == MOUSE_RIGHT_CLICK) {
			mouseRightClick = value;
		}
	}

	public static boolean get(int type) {
		return get(type, 0);
	}

	public static boolean get(int type, int index) {
		if (type == KEYBOARD) {
			return key[index];
		} else if (type == MOUSE_LEFT) {
			return mouseLeft;
		} else if (type == MOUSE_RIGHT) {
			return mouseRight;
		} else if (type == MOUSE_LEFT_CLICK) {
			return mouseLeftClick;
		} else if (type == MOUSE_RIGHT_CLICK) {
			return mouseRightClick;
		}
		return false;
	}

	public static boolean getTrigger(int type) {
		return getTrigger(type, 0);
	}

	public static boolean getTrigger(int type, int index) {
		if (type == KEYBOARD) {
			return keyTrigger[index];
		} else if (type == MOUSE_LEFT) {
			return mouseLeftTrigger;
		} else if (type == MOUSE_RIGHT) {
			return mouseRightTrigger;
		}
		return false;
	}

	public static void clearTrigger() {
		for (int i = 0; i < keyTrigger.length; i++)
			keyTrigger[i] = false;
		mouseLeftTrigger = mouseRightTrigger = false;
		mouseLeftClick = mouseRightClick = false;
	}

	public static void setMousePosition(int x, int y) {
		mouseX = x;
		mouseY = y;
	}

	public static int getMouseX() {
		return mouseX;
	}

	public static int getMouseY() {
		return mouseY;
	}
}
