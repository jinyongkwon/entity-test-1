package site.metacoding.entitytest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.entitytest.domain.Board;
import site.metacoding.entitytest.domain.BoardRepository;
import site.metacoding.entitytest.web.dto.BoardDetailRespDto;
import site.metacoding.entitytest.web.dto.BoardRespDto;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardRespDto 상세보기(Integer id) {
        Board boardEntity = boardRepository.findById(id).get();

        BoardRespDto dto = new BoardRespDto(
                boardEntity.getId(),
                boardEntity.getTitle(),
                boardEntity.getContent());

        return dto;
    }

    public BoardDetailRespDto 좋아요포함상세보기(Integer id) {
        return boardRepository.mFindDetail(id);
    }

    public List<BoardDetailRespDto> 전체보기() {
        return boardRepository.mFindAll();
        // return boardJPQLRepository.mFindAllQLRM();
    }
}
