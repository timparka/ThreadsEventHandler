public class EventListener extends Thread {

    private String messageToListenFor;
    private String messageToReplyWith;
    private Tracker eventTracker;

    public EventListener(String message, String reply) {
        this.messageToListenFor = message;
        this.messageToReplyWith = reply;
        this.eventTracker = EventTracker.getInstance();
    }

    public EventListener(String message, String reply, Tracker tracker) {
        this.messageToListenFor = message;
        this.messageToReplyWith = reply;
        this.eventTracker = tracker;
    }

    public void run() {
        // The run method should use a while loop that continues to run as long as
        // "readyToQuit" returns false
        while (!readyToQuit()) {
            if (shouldReply()) {
                reply();
            }
        }
    }

    public Boolean readyToQuit() {
        return eventTracker.has("quit");
    }

    public Boolean shouldReply() {
        return eventTracker.has(messageToListenFor);
    }

    public void reply() {
        eventTracker.handle(messageToListenFor, () -> {
            System.out.println(messageToReplyWith);
        });

    }
}