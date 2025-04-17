package study.study.member.controller

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import study.study.common.authority.TokenInfo
import study.study.common.dto.BaseResponse
import study.study.common.dto.CustomUser
import study.study.member.dto.LoginDto
import study.study.member.entity.Member
import study.study.member.service.MemberService
import study.study.member.dto.MemberDtoRequest
import study.study.member.dto.MemberDtoResponse

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
    /**
     * 로그인
     */

    @PostMapping("/login")
    fun login(@RequestBody @Valid loginDto: LoginDto): BaseResponse<TokenInfo> {
        val tokenInfo = memberService.login(loginDto)
        return BaseResponse(data = tokenInfo)
    }
    /**
     * 내 정보 보기
     */
    @GetMapping("/info")
    fun searchInfo(): BaseResponse<MemberDtoResponse> {
        val userId = (SecurityContextHolder
            .getContext()
            .authentication
            .principal as CustomUser)
            .userId
        val response = memberService.searchMyInfo(userId)
        return BaseResponse(data = response)
    }
    /**
     * 내 정보 저장
     */
    @PutMapping("/info")
    fun saveMyInfo(@RequestBody @Valid memberDtoRequest: MemberDtoRequest):
            BaseResponse<Unit> {
        val userId = (SecurityContextHolder
            .getContext()
            .authentication
            .principal as CustomUser)
            .userId
        memberDtoRequest.id = userId
        val resultMsg: String = memberService.saveMyInfo(memberDtoRequest)
        return BaseResponse(message = resultMsg)
    }
}
