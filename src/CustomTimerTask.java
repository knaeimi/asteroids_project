import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class CustomTimerTask extends TimerTask {

    private List<Meteor> list = new ArrayList<Meteor>();

    public CustomTimerTask (List<Meteor> mList) {
        this.list = new ArrayList<Meteor>(mList);
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
    }
}
