package vn.locdt.util;

import vn.locdt.constant.CharConstants;

public class DetectArrowKey {
    public static boolean detecting = false; //1: Up, 2: Down, 3: Left, 4: Right
    private static int step = -1;
    private static int[] arrowProcess = new int[]{27, 91};

    public static void detect() {
        DetectArrowKey.detecting = true;
        DetectArrowKey.step = 0;
    }

    public static void reset() {
        DetectArrowKey.detecting = false;
        DetectArrowKey.step = -1;
    }

    public static CharConstants.ArrowKey update(int data) {
        DetectArrowKey.step++;

        if (DetectArrowKey.step == 2) {
            reset();
            switch (data) {
                case 65:
                    return CharConstants.ArrowKey.UP;
                case 66:
                    return CharConstants.ArrowKey.DOWN;
                case 68:
                    return CharConstants.ArrowKey.LEFT;
                case 67:
                    return CharConstants.ArrowKey.RIGHT;
            }
        }

        if (arrowProcess[DetectArrowKey.step] != data)
            reset();

        return null;
    }
}
