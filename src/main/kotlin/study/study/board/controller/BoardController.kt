package study.study.board.controller

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import study.study.board.dto.BoardDtoRequest
import study.study.board.dto.DeleteDto
import study.study.board.service.BoardService
import study.study.common.dto.BaseResponse

@RequestMapping("/api/board")
@RestController
class BoardController(
    private val boardService: BoardService
) {
    /**
     * 게시글 작성
     */
    @PostMapping("/upload")
    fun upload(@RequestBody @Valid request: BoardDtoRequest ): BaseResponse<Unit> {
        val resultMsg = boardService.upload(request)
        return BaseResponse(message = resultMsg)
    }
    /**
     * 게시글 삭제
     */
    @PostMapping("/delete")
    fun delete(){

    }
}