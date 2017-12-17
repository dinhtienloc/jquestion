package vn.locdt.reader;

import vn.locdt.constant.CharConstants;
import vn.locdt.event.CharacterInputEvent;
import vn.locdt.question.Question;
import vn.locdt.util.RawConsoleInput;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/**
 * @author Kantenkugel (Michael Ritter)
 */
public class NonBlockConsoleReader {

    public static void start(Consumer<CharacterInputEvent> consoleHandler) throws IOException {
        loop(consoleHandler, new AtomicBoolean(true));
    }

    public static Runnable startAsync(Consumer<CharacterInputEvent> consoleHandler) throws IOException {
        AtomicBoolean running = new AtomicBoolean(true);
        new Thread(() -> {
            try {
                loop(consoleHandler, running);
            } catch(IOException e) {
                running.set(false);
                e.printStackTrace();
            }
        }).start();
        return () -> running.set(false);
    }

    static void loop(Consumer<CharacterInputEvent> consoleHandler, AtomicBoolean shouldRun) throws IOException {
        int read;
        final StringBuilder b = new StringBuilder();
        while(shouldRun.get()) {
            read = RawConsoleInput.read(true);
//            System.out.println(read);
            if(read == -1)
                read = CharConstants.CHAR_CTRL_D;
            if(read == '\r')
                read = '\n';
            if(read == CharConstants.CHAR_BACKSPACE)
                b.setLength(b.length()-1);
            else
                b.append((char) read);
            CharacterInputEvent event = new CharacterInputEvent(b, (char) read);
            consoleHandler.accept(event);
            if(event.isShouldCancel())
                shouldRun.set(false);
        }
        resetConsoleMode();
    }

    public static void resetConsoleMode() throws IOException {
        RawConsoleInput.resetConsoleMode();
    }

    private NonBlockConsoleReader() {
    }
}
