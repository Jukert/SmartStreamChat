package parser.pojo;

public class Chat {
    private Permission permission;
    private SettingText settingText;
    private String background;
    private String censor;
    private String shape;
    private String analytic;

    public Chat() {
    }

    public Chat(Permission permission, SettingText settingText, String background, String censor, String shape, String analytic) {
        this.permission = permission;
        this.settingText = settingText;
        this.background = background;
        this.censor = censor;
        this.shape = shape;
        this.analytic = analytic;
    }

    public Chat(Chat chat) {
        this.permission = chat.getPermission();
        this.settingText = chat.getSettingText();
        this.background = chat.getBackground();
        this.censor = chat.getCensor();
        this.shape = chat.getShape();
        this.analytic = chat.getAnalytic();
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public SettingText getSettingText() {
        return settingText;
    }

    public void setSettingText(SettingText settingText) {
        this.settingText = settingText;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getCensor() {
        return censor;
    }

    public void setCensor(String censor) {
        this.censor = censor;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getAnalytic() {
        return analytic;
    }

    public void setAnalytic(String analytic) {
        this.analytic = analytic;
    }
}
