package hh.yarkinsv;

public class FrameView {

    public static String getFrameText(Frame frame) {
        if (frame instanceof FinalFrame) {
            String x = getFinalFrameString(frame);
            if (x != null) return x;
        } else {
            String x = getCommonFrameString(frame);
            if (x != null) return x;
        }
        return "";
    }

    private static String getCommonFrameString(Frame frame) {
        switch (frame.getFrameType()) {
            case NotCompleted:
                return (frame.getFirstBall() != -1 ? "" + (frame.getFirstBall() == 0 ? "-" : frame.getFirstBall()) : "");
            case OpenFrame:
                return (frame.getFirstBall() == 0 ? "-" : frame.getFirstBall()) + "  " +
                        (frame.getSecondBall() == 0 ? "-" : frame.getSecondBall());
            case Spare:
                return (frame.getFirstBall() == 0 ? "-" : frame.getFirstBall()) + "  /";
            case Strike:
                return "   X";
        }
        return null;
    }

    private static String getFinalFrameString(Frame frame) {
        switch (frame.getFrameType()) {
            case NotCompleted:
                return getBallString(frame.getFirstBall()) + "  " + getBallString(frame.getSecondBall()) + "  " +
                        getBallString(((FinalFrame) frame).getFirstAdditionalBall());
            case OpenFrame:
                return getBallString(frame.getFirstBall()) + "  " + getBallString(frame.getSecondBall());
            case Spare:
                return getBallString(frame.getFirstBall()) + "  /" + "  " + getBallString(((FinalFrame) frame).getFirstAdditionalBall());
            case Strike:
                return "X  " + getBallString(((FinalFrame) frame).getFirstAdditionalBall()) + "  " + getBallString(((FinalFrame) frame).getSecondAdditionalBall());
        }
        return null;
    }

    private static String getBallString(int ball) {
        if (ball == -1) {
            return "";
        } else if (ball == 10) {
            return "X";
        } else if (ball == 0) {
            return "-";
        }
        return "" + ball;
    }
}
