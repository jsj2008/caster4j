package caster.open.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import saber.util.JMap;
import saber.util.ServletUtils;
import saber.util.Time;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class PlainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String req = ServletUtils.heavySummary(request).toString();
        response.setContentType("text/plain");
        response.getWriter().write(req);
    }

    @RequestMapping(value = "/time", method = RequestMethod.GET)
    public Object time(@RequestParam(name = "pattern", defaultValue = "yyyy-MM-dd HH:mm:ss SSS") String pattern) {
        return JMap.on(200).setData(Time.on().format(pattern));
    }

    @RequestMapping(value = "/timestamp", method = RequestMethod.GET)
    public Object timestamp() {
        return JMap.on(200).setData(Time.on().getTimestamp());
    }

    @RequestMapping(value = "/unix-timestamp", method = RequestMethod.GET)
    public Object unixTimestamp() {
        return JMap.on(200).setData(Time.on().getUnixTimestamp());
    }

}
