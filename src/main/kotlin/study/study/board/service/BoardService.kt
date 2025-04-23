package study.study.board.service

import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import study.study.board.dto.BoardDtoRequest
import study.study.board.dto.BoardDtoResponse
import study.study.board.dto.DeleteDto
import study.study.board.entity.Post
import study.study.board.repository.BoardRepository
import study.study.common.exception.InvalidInputException

@Transactional
@Service
class BoardService(
    private val boardRepository: BoardRepository,
) {
    /**
     * 게시글 등록
     */
    fun upload(request: BoardDtoRequest, currentUserName :String): String {

        // BoardDtos에 있는 toEntity()를 이용해도 됨. 아래 val post는 일일히 값을 넣어준 경우.
        val post = Post(
            title = request.title,
            description = request.description,
            writerName = currentUserName,
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
    /**
     * 게시글 조회
     */
    fun getAllPosts(): List<BoardDtoResponse>{
        val post = boardRepository.findAll()
        return post.map {it.toDto()}
    }
    /**
     * 특정 게시글 조회
     */
    fun getPosts(id : Long): BoardDtoResponse {
        val post = boardRepository.findByIdOrNull(id)
            ?: throw InvalidInputException("id","(게시글 ID:${id}) 존재하지 않는 게시글 입니다.")
        return post.toDto()
    }
}