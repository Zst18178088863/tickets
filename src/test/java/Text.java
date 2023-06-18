import com.zst.mapper.TicketMapper;
import com.zst.mapper.TripMapper;
import com.zst.pojo.Ticket;
import com.zst.pojo.Trip;
import com.zst.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Auther: 张胜通
 * @Date: 2023/6/14 20:36
 */

public class Text {


    @Test
    public void select() throws ParseException {
        SqlSession session = MybatisUtils.getSession();
        TripMapper ticketMapper = session.getMapper(TripMapper.class);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse("2023-6-15");
        //System.out.println(format.format(date));
        //System.out.println(date);

        Trip trip = ticketMapper.queryTripByClasses("004");
        int soldTickets = trip.getSoldTickets();
        session.commit();
        session.close();
        System.out.println(soldTickets);
    }



}
