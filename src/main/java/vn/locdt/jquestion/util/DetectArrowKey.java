package vn.locdt.jquestion.util;


import vn.locdt.jquestion.constant.VirtualKey;

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

    public static VirtualKey.ArrowKey update(int data) {
        DetectArrowKey.step++;

        if (DetectArrowKey.step == 2) {
            reset();
            switch (data) {
                case 65:
                    return VirtualKey.ArrowKey.UP;
                case 66:
                    return VirtualKey.ArrowKey.DOWN;
                case 67:
                    return VirtualKey.ArrowKey.RIGHT;
                case 68:
                    return VirtualKey.ArrowKey.LEFT;
            }
        }

        if (arrowProcess[DetectArrowKey.step] != data)
            reset();

        return null;
    }
}
