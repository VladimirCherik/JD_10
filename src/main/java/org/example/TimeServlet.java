package org.example;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {
    public static final int DATA_FORMAT_LENGTH = 19;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TimeServlet timeServlet = new TimeServlet();

        String dataForResponse;
        if(req.getParameterMap().containsKey("timezone")){
            dataForResponse = timeServlet.setTime(req.getParameter("timezone"));
        } else {
            dataForResponse = timeServlet.setTime("UTC+0");
        }
        resp.getWriter().write(dataForResponse);
        resp.getWriter().close();
    }

    private String setTime (String timeZone){

        int timeZoneInt = Integer.parseInt(timeZone.substring(3).trim());
        timeZone = timeZoneInt == 0 ? "UTC" : timeZone;
        String time = LocalDateTime.now(ZoneOffset.ofHours(timeZoneInt)).toString();
        return time.replace("T", " ").substring(0, DATA_FORMAT_LENGTH) + " " + timeZone;
    }
}
