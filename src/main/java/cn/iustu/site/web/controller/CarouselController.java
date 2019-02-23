package cn.iustu.site.web.controller;

import cn.iustu.site.common.entity.Carousel;
import cn.iustu.site.common.model.Result;
import cn.iustu.site.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/carousel")
public class CarouselController {

    @Autowired
    private CarouselService carouselService;

    @GetMapping("/list")
    public Result list(){
        List<Carousel> list = carouselService.list();
        return Result.success().add("carousels", list);
    }
}
