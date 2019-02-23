package cn.iustu.site.dao;


import cn.iustu.site.common.entity.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    List<Member> findByGrade(Integer grade, Pageable pageable);
    Long countByGrade(Integer grade);
}
