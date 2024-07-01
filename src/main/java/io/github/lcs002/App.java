package io.github.lcs002;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.lcs002.config.Config;
import io.github.lcs002.config.ConfigController;
import io.github.lcs002.config.GeneratorConfig;
import io.github.lcs002.utils.FileUtils;
import io.github.lcs002.generator.GeneratorController;
import io.github.lcs002.utils.Printer;
import org.jline.jansi.Ansi;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Hello world!
 *
 */
public class App 
{
    public static Terminal terminal;

    public static void main( String[] args ) throws IOException {
        terminal = TerminalBuilder.terminal();
        terminal.puts(InfoCmp.Capability.clear_screen);

        Printer.print(Ansi.ansi().fgBright(Ansi.Color.GREEN).a(
                "  __  __ _  _ ___     _       _       ___             \n" +
                        " |  \\/  | \\| / __|   /_\\ _  _| |_ ___|   \\ ___  __ ___\n" +
                        " | |\\/| | .` \\__ \\  / _ \\ || |  _/ _ \\ |) / _ \\/ _(_-<\n" +
                        " |_|  |_|_|\\_|___/ /_/ \\_\\_,_|\\__\\___/___/\\___/\\__/__/\n" +
                        "                                                      \n"
        ).reset().toString());
        
        ConfigController.getInstance().init();
        GeneratorController.getInstance().init();
        GeneratorController.getInstance().generate();
    }
}
