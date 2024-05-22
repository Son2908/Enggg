package model;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * tạo ra một ngày ngẫu nhiên trong khoảng thời gian từ sDate1 đến sDate2 và in ra ngày đó dưới định dạng "dd/MM/yyyy".
 * @author sodok
 */
public class data {

    public static void main(String[] args) throws ParseException {
        String sDate1 = "01/01/2023";
        String sDate2 = "31/12/2024";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        Date endDate = new SimpleDateFormat("dd/MM/yyyy").parse(sDate2);
        Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        Date randomDate = new Date(ThreadLocalRandom.current().nextLong(startDate.getTime(), endDate.getTime()));
        System.out.println(dateFormat.format(randomDate));       
    }
}
