package hu.nye.kovacssebestyen.szkd.tarszki.web.controller;

import hu.nye.kovacssebestyen.szkd.tarszki.data.model.TarskiData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/tarski")
@Controller
public class ShapesController {

    @PostMapping("/validate")
    public String createShapes(Model model,@RequestBody TarskiData tarskiData) {
        System.out.println(tarskiData.toString());
        return "edit";
    }
}
