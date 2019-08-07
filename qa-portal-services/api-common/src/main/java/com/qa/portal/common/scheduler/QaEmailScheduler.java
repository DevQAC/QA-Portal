
import java.text.SimpleDateFormat;
import ;

public class QaEmailScheduler {


    public QaEmailScheduler(){

    }

    @Scheduled(cron = "0 1 1 * * *")
    public void sendScheduledEmails(){
        QaEmailClient.sendEmail("","","")
    }



    private String subtractDate(String date){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calender c = Calender.getInstance();
        c.setTime(sdf.parse(date));
        c.add(Calender.Date, -1);
        return sdf.format(c.getTime());
    }





}