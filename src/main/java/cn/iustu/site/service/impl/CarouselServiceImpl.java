package cn.iustu.site.service.impl;

import cn.iustu.site.common.entity.Carousel;
import cn.iustu.site.dao.CarouselRepository;
import cn.iustu.site.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselRepository carouselRepository;

    @Override
    public List<Carousel> list() {
        return carouselRepository.findAll();
    }

    @Transactional
    @Override
    public void update(Carousel carousel) {
        carouselRepository.save(carousel);
    }
}
