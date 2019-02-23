package cn.iustu.site.service;

import cn.iustu.site.common.entity.Carousel;

import java.util.List;

public interface CarouselService {

    List<Carousel> list();
    void update(Carousel carousel);
}
