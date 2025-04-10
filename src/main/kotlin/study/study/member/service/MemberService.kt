package study.study.member.service

import jakarta.transaction.Transactional
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.stereotype.Service
import study.study.common.authority.JwtTokenProvider
import study.study.common.authority.TokenInfo
import study.study.common.exception.InvalidInputException
import study.study.common.status.ROLE
import study.study.member.dto.LoginDto
import study.study.member.dto.MemberDtoRequest
import study.study.member.entity.Member
import study.study.member.entity.MemberRole
import study.study.member.repository.MemberRepository
import study.study.member.repository.MemberRoleRepository

@Transactional
@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val memberRoleRepository: MemberRoleRepository,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val jwtTokenProvider: JwtTokenProvider
) {
    /**
     * 회원가입
     */
    fun signUp(memberDtoRequest: MemberDtoRequest):String {
        // ID 중복 검사
        var member: Member? = memberRepository.findByLoginId(memberDtoRequest.loginId)
        if (member != null) {
            throw InvalidInputException("loginId", "이미 등록된 ID 입니다.")
        }

        // 사용자 정보 저장
        member = memberDtoRequest.toEntity()
        memberRepository.save(member)

        // 권한 저장
        val memberRole: MemberRole = MemberRole(null, ROLE.MEMBER, member)
        memberRoleRepository.save(memberRole)

        return "회원가입이 완료되었습니다."

    }
    /**
     * 로그인 -> 토큰발행
     */
    fun login(loginDto: LoginDto): TokenInfo{
        val authenticationToken =
            UsernamePasswordAuthenticationToken(loginDto.loginId, loginDto.password)
        val authentication =
            authenticationManagerBuilder.`object`.authenticate(authenticationToken)
        return jwtTokenProvider.createToken(authentication)
    }
}