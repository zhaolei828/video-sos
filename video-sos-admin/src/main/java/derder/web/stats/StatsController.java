package derder.web.stats;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/12/20.
 */
@Controller
public class StatsController {

    @RequestMapping("/stats")
    public String stats() {
        return "stats";
    }
}
