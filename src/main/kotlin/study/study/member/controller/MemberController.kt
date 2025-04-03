package study.study.member.controller

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import study.study.common.dto.BaseResponse
import study.study.member.entity.Member
import study.study.member.service.MemberService
import study.study.member.dto.MemberDtoRequest

@RequestMapping("/api/member")
@RestController
class MemberController(
    private val memberService: MemberService
) {
    @GetMapping("/")
    fun test() : BaseResponse<Unit> {
        return BaseResponse(message = "hello world")
    }
    /**
     * 회원가입
      */
    @PostMapping("/signup")
    fun signup(@RequestBody @Valid memberDtoRequest: MemberDtoRequest): BaseResponse<Unit> {
        val resultMsg: String = memberService.signUp(memberDtoRequest)
        return BaseResponse(message = resultMsg)

    }
}
