package animation;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Element<T extends Node> {

    private Duration duration = Duration.seconds(1);
    private int byAngle = 0;
    private int toAngle = 360;
    private int cycle = 1;
    private RotateTransition rtElement = null;


    public Element() {

    }

    public Element(int duration) {
        this.duration = Duration.seconds(duration);
    }

    public Element(int duration, int byAngle, int toAngle, int cycle) {
        this.duration = Duration.seconds(duration);
        this.byAngle = byAngle;
        this.toAngle = toAngle;
        this.cycle = cycle;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public void setByAngle(int byAngle) {
        this.byAngle = byAngle;
    }

    public void setToAngle(int toAngle) {
        this.toAngle = toAngle;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }

    public void setRtElement(RotateTransition rtElement) {
        this.rtElement = rtElement;
    }

    public Duration getDuration() {
        return duration;
    }

    public int getByAngle() {
        return byAngle;
    }

    public int getToAngle() {
        return toAngle;
    }

    public int getCycle() {
        return cycle;
    }

    public RotateTransition getRtElement() {
        return rtElement;
    }

    public void pause(){
        rtElement.pause();
    }

    public void rotate(T el){
        if (rtElement == null) {
            rtElement = new RotateTransition(duration, el);
            rtElement.setByAngle(byAngle);
            rtElement.setToAngle(toAngle);
            rtElement.setCycleCount(800);
        }
        rtElement.play();
    }

    public void fade(T el){
        FadeTransition fade = new FadeTransition();
        fade.setDuration(duration);
        fade.setNode(el);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    public void colorTransition( T enable){
        enable.setStyle("-fx-background-color: rgb(48,48,48)");
    }



}
