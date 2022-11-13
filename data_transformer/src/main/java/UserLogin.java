import com.amazonaws.services.sqs.model.Message;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class UserLogin {
    JSONParser parser = new JSONParser();
    private String appVer;
    private String deviceId;
    private String userId;
    private String ip;
    private String deviceType;
    private String locale;

    private JSONObject jsonObject;

    public UserLogin(Message message) {
        try {
            this.jsonObject = (JSONObject) parser.parse(message.getBody());
        } catch (Exception e) {
        e.printStackTrace();
        }
        this.appVer = jsonObject.get("app_version").toString();
        this.deviceId = jsonObject.get("device_id").toString();
        this.userId = jsonObject.get("user_id").toString();
        this.ip = jsonObject.get("ip").toString();
        this.deviceType = jsonObject.get("device_type").toString();
        if (jsonObject.get("locale") != null) {
            this.locale = jsonObject.get("locale").toString();

        }else{
            this.locale = "null";
        }
    }


    public String getAppVer() {return appVer;}

    public Integer getAppMajorVer() {
        // extract major version from string
        return Integer.valueOf(String.valueOf(appVer.charAt(0)));
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getMaskedDeviceId() {
        return deviceId.replaceAll("((.+?)-){2}",
                "****-**-");
    }

    public String getUserId() {
        return userId;
    }

    public String getIp() {
        return ip;
    }

    public String getMaskedIp() {
        return ip.replaceAll("(?:[0-9]{1,3}\\.){1}[0-9]{1,3}\\.",
                "***.***.");
    }

    public String getDeviceType() {
        return deviceType;
    }

    public String getLocale() {
        return locale;
    }
}
