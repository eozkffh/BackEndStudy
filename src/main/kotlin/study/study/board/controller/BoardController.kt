package study.study.board.controller

import jakarta.validation.Valid
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import study.study.board.dto.BoardDtoRequest
import study.study.board.dto.BoardDtoResponse
import study.study.board.dto.DeleteDto
import study.study.board.repository.BoardRepository
import study.study.board.service.BoardService
import study.study.common.dto.BaseResponse
import study.study.common.dto.CustomUser

@RequestMapping("/api/board")
@RestController
class BoardController(
    private val boardService: BoardService,
    private val boardRepository: BoardRepository
) {
    /**
     * 게시글 작성
     */
    @PostMapping("/")
    fun upload(@RequestBody @Valid request: BoardDtoRequest ): BaseResponse<Unit> {
        // 현재 로그인된 id의 name을 token을 통해 가져옴.
        val currentUserName = (SecurityContextHolder.getContext().authentication.principal as CustomUser).username

        //upload에는 request와 token에서 가져온 name을 인수로 줌.
        val resultMsg = boardService.upload(request, currentUserName)
        return BaseResponse(message = resultMsg)
    }
    /**
     * 게시글 삭제
     */
    @PostMapping("/")
    fun delete(@RequestBody @Valid request: DeleteDto): BaseResponse<Unit> {
        val resultMsg = boardService.delete(request)
        return BaseResponse(message = resultMsg)
    }
    /**
     * 게시글 조회
     */
    @GetMapping("/")
    fun getAllPosts(): BaseResponse<List<BoardDtoResponse>> {
        val result = boardService.getAllPosts()
        return BaseResponse(data = result)
    }
    /**
     * 특정 게시글 조회
     */
    @GetMapping("/{id}")
    fun getPosts(@PathVariable id :Long): BaseResponse<BoardDtoResponse> {
        val result = boardService.getPosts(id)
        return BaseResponse(data = result)
    }
}