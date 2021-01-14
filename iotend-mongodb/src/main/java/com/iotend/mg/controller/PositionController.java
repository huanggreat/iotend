package com.iotend.mg.controller;

import com.iotend.mg.db.PositionRepository;
import com.iotend.mg.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/position")
public class PositionController {
    @Autowired
    PositionRepository positionRepository;
    @RequestMapping("add")
    @ResponseBody
    public String insert(HttpServletResponse httpServletResponse){
        Position position = new Position();
        position.setLat(12.34);
        position.setLng(34.12);
        positionRepository.save(position);

        return "finish!";
    }
}
