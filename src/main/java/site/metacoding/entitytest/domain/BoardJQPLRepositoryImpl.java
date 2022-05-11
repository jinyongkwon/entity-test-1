package site.metacoding.entitytest.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import site.metacoding.entitytest.web.dto.BoardDetailRespDto;

@RequiredArgsConstructor
@Repository
public class BoardJQPLRepositoryImpl implements BoardJQPLRepository {

    private final EntityManager em;

    // createQuery -> 디비 모델과 타입이 같아야 한다. -> BoardRepository 사용하세요 차라리!!
    // createNativeQuery -> 진짜 내맘대로 쿼리 (PrepareStatement)
    public BoardDetailRespDto mFindDetail(Integer id) {
        String sql = "SELECT b.*, true FROM board b WHERE id = ?";
        Query query = em.createNativeQuery(sql)
                .setParameter(1, id);

        Object[] result = (Object[]) query.getSingleResult();
        Integer boardId = (Integer) result[0];
        String title = (String) result[1];
        String content = (String) result[2];
        boolean isLove = (Boolean) result[3];

        BoardDetailRespDto dto = new BoardDetailRespDto(boardId, title, content, isLove);

        return dto;
    }

    public List<BoardDetailRespDto> mFindAll() {
        List<BoardDetailRespDto> dtos = new ArrayList<>();

        String sql = "SELECT b.*, true FROM board b";
        Query query = em.createNativeQuery(sql);

        List<Object[]> results = (List<Object[]>) query.getResultList();

        for (Object[] result : results) {
            Integer boardId = (Integer) result[0];
            String title = (String) result[1];
            String content = (String) result[2];
            boolean isLove = (Boolean) result[3];
            BoardDetailRespDto dto = new BoardDetailRespDto(boardId, title, content, isLove);
            dtos.add(dto);
        }

        return dtos;
    }

    public List<BoardDetailRespDto> mFindAllQLRM() {
        String sql = "SELECT b.*, true FROM board b";
        Query query = em.createNativeQuery(sql);

        JpaResultMapper mapper = new JpaResultMapper();
        List<BoardDetailRespDto> dtos = mapper.list(query, BoardDetailRespDto.class);

        return dtos;
    }
}
