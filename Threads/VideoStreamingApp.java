package Threads;

class StreamModel {
    private final String videoTitle;
    public StreamModel(String videoTitle) { this.videoTitle = videoTitle; }
    public String getVideoTitle() { return videoTitle; }
}

class StreamService extends Thread {
    private final StreamModel model;

    public StreamService(StreamModel model) {
        this.model = model;
    }

    @Override
    public void run() {
        try {
            System.out.println("Streaming started: " + model.getVideoTitle());
            for (int i = 1; i <= 5; i++) {
                System.out.println(model.getVideoTitle() + " - frame " + i);
                Thread.sleep(500);
            }
            System.out.println("Streaming ended: " + model.getVideoTitle());
        } catch (InterruptedException e) {
            System.out.println(model.getVideoTitle() + " interrupted!");
        }
    }
}

class StreamController {
    public void startStream(String title) throws InterruptedException {
        StreamModel model = new StreamModel(title);
        StreamService service = new StreamService(model);

        service.start();
        Thread.sleep(1200); // let it play a bit
        service.interrupt();
        service.join();
    }
}

public class VideoStreamingApp {
    public static void main(String[] args) throws InterruptedException {
        StreamController controller = new StreamController();
        controller.startStream("Nature Documentary");
    }
}
