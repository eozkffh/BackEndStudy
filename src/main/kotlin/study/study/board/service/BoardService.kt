package study.study.board.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import study.study.board.dto.BoardDtoRequest
import study.study.board.dto.DeleteDto
import study.study.board.entity.Post
import study.study.board.repository.BoardRepository

@Transactional
@Service
class BoardService(
    private val boardRepository: BoardRepository,
) {
    /**
     * 게시글 등록
     */
    fun upload(request: BoardDtoRequest): String {

        val post = Post(
            title = request.title,
            description = request.description
        )

        boardRepository.save(post)      // BoardRepository 오타때문에 save 오류뜸. 대소문자 구별.
        return "게시글 등록 완료"
    }

    /**
     * 게시글 삭제
     */
    fun delete(deleteDto: DeleteDto): String {
        val post = boardRepository.findById(deleteDto.postId)   // postId 검색했는데
            .orElseThrow{RuntimeException("게시글 없음")}    // 없으면 예외 던짐

        boardRepository.delete(post)    //postId가 있으면 삭제.
        return "게시글 삭제 완료"
    }
}