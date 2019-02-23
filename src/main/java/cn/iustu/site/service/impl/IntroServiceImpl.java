package cn.iustu.site.service.impl;

import cn.iustu.site.common.entity.Intro;
import cn.iustu.site.dao.IntroRepository;
import cn.iustu.site.service.IntroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class IntroServiceImpl implements IntroService {

    @Autowired
    private IntroRepository introRepository;

    @Override
    public Intro get() {
        try{
            return introRepository.findById(1).get();
        }catch(NoSuchElementException e){
            return null;
        }
    }

    @Transactional
    @Override
    public void save(Intro intro) {
        intro.setId(1);
        introRepository.save(intro);
    }
}
