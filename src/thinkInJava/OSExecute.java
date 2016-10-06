package thinkInJava;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by liuda on 2016/10/6.
 */
public class OSExecute {

    public static void command(String command) throws Exception {
        boolean err = false;
        try {
            Process process =
                    new ProcessBuilder(command.split(" ")).start();
            BufferedReader results = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String s;
            while ((s = results.readLine()) != null) {
                System.out.println(s);
            }
            BufferedReader errors = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((s = errors.readLine()) != null) {
                System.out.println(s);
                err = true;
            }
        } catch (Exception e) {
            if(!command.startsWith("CMD/C"))
                command("CMD/C"+command);
            else
                throw new RuntimeException(e);
        }
        if(err)
            throw new Exception("Errors excuting"+command);
    }
}
