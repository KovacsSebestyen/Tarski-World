package hu.nye.kovacssebestyen.szkd.tarszki.web.controller;

import hu.nye.kovacssebestyen.szkd.tarszki.data.model.TarskiData;
import hu.nye.kovacssebestyen.szkd.tarszki.service.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLOutput;

@RequestMapping("/tarski")
@Controller
public class ShapesController {

    @PostMapping("/validate")
    public ResponseEntity<String> createShapes(Model model, @RequestBody TarskiData tarskiData) {
        //System.out.println(tarskiData.toString());
        Validator validator = new Validator(tarskiData);
        String result = validator.check(validator.getTarskiData().getFormula());

        if(result.equals("(true)")) {
            System.out.println("true");
            return ResponseEntity.ok(result);
        } else if(result.equals("(false)")) {
            System.out.println("false");
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }
}
