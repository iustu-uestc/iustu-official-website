package cn.iustu.site.service.impl;

import cn.iustu.site.common.entity.Member;
import cn.iustu.site.common.model.Page;
import cn.iustu.site.dao.MemberRepository;
import cn.iustu.site.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public List<Member> list(Page page) {
        Pageable pageable = PageRequest.of(page.getPage() - 1, page.getRows());
        org.springframework.data.domain.Page<Member> members = memberRepository.findAll(pageable);
        return members.getContent();
    }

    @Override
    public List<Member> listByGrade(Integer grade, Page page) {
        Pageable pageable = PageRequest.of(page.getPage() - 1, page.getRows());
        return memberRepository.findByGrade(grade, pageable);
    }

    @Override
    public Member get(Integer id) {
        try {
            return memberRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            return null;
        }

    }

    @Override
    public Long count() {
        return memberRepository.count();
    }

    @Override
    public Long countByGrade(Integer grade) {
        return memberRepository.countByGrade(grade);
    }

    @Override
    @Transactional
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        memberRepository.deleteById(id);
    }
}
