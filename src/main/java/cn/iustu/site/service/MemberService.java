package cn.iustu.site.service;

import cn.iustu.site.common.entity.Member;
import cn.iustu.site.common.model.Page;

import java.util.List;

public interface MemberService {

    List<Member> list(Page page);
    List<Member> listByGrade(Integer grade, Page page);
    Member get(Integer id);
    Long count();
    Long countByGrade(Integer grade);
    Member save(Member member);
    void delete(Integer id);
}
