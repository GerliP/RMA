package rma.project.rma.Controllers;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gerli on 20/03/2018.
 */
public class DeviceIdCheck {

    private final String DEVICEID_PATTERN = "^[A-Fa-f0-9_-]{1,16}$";  // TODO: 20/03/2018: Add pattern for device id
    private Pattern pattern;
    private Matcher matcher;

    public DeviceIdCheck() {
        pattern = Pattern.compile(DEVICEID_PATTERN);
    }

    public boolean validate(final String deviceId) {
        matcher = pattern.matcher(deviceId);
        return matcher.matches();
    }

//    public boolean unique(final String username) {
//        Set<ServerThread> threadList = Server.getThreadList();
//        boolean result = false;
//        for (ServerThread t : threadList) {
//            String name = t.toString();
//            if (name.equals(username)) {
//                result = false;
//            } else {
//                result = true;
//            }
//        }
//        return result;
//    }
}
