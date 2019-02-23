package cn.iustu.site.service.impl;

import cn.iustu.site.common.entity.RecruitLink;
import cn.iustu.site.common.model.Page;
import cn.iustu.site.dao.RecruitLinkRepository;
import cn.iustu.site.service.RecruitLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RecruitLinkServiceImpl implements RecruitLinkService {

    @Autowired
    private RecruitLinkRepository recruitLinkRepository;

    @Override
    public RecruitLink get() {
        try{
            return recruitLinkRepository.findById(1).get();
        }catch (NoSuchElementException e){
            return null;
        }
    }

    @Override
    @Transactional
    public void update(RecruitLink recruitLink) {
        recruitLink.setId(1);
        recruitLinkRepository.save(recruitLink);
    }
}
