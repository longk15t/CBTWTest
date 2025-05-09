package dialer.interfaces;

public interface MobileActions extends DialerAppActions {
    String getMainTitle();

    boolean verifyMessageArrived(String msg);
}
