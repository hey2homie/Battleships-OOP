package battleships;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Timer {

    private int millis;
    private Label timer;
    private int turn;
    private final Player player;

    public Timer(int millis, int turn, Player player, Label label) {
        this.millis = millis;
        this.turn = turn;
        this.player = player;
        this.timer = label;
    }

    public void setTimer(Label timer) {
        this.timer = timer;
    }

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> CountDown()));

    public void CountDown() {
        if (millis != 0) {
            millis -= 1000;
            if (turn == 1) {
                Utilities.getPlayer1().addTime(1);
            } else {
                Utilities.getPlayer2().addTime(1);
            }
        }
        timer.setText(String.valueOf(millis / 1000));
        if (millis == 0) {
            runOutOfTime();
            timeline.stop();
        }
    }

    public void runTimer() {
        timeline.setCycleCount(millis);
        timeline.playFromStart();
    }

    public void stop() {
        timeline.stop();
    }

    private void runOutOfTime() {
        player.setClickAllowance(false);
        if (turn == 2) {
            Utilities.getPlayer2().addMisHits();
        } else {
            Utilities.getPlayer1().addMisHits();
        }
    }

    public void newMove() {
        timer.setText(String.valueOf(millis / 1000));
        setTimer(timer);
        runTimer();
    }
}
